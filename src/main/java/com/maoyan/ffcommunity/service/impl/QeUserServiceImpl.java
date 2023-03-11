package com.maoyan.ffcommunity.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.maoyan.ffcommunity.entity.QeUser;
import com.maoyan.ffcommunity.entity.vo.qeuser.QeUserDetailVO;
import com.maoyan.ffcommunity.entity.vo.qeuser.QeUserLoginVO;
import com.maoyan.ffcommunity.exception.CustomException;
import com.maoyan.ffcommunity.mapper.QeUserMapper;
import com.maoyan.ffcommunity.service.QeUserService;
import com.maoyan.ffcommunity.utils.HttpStatus;
import com.maoyan.ffcommunity.utils.QeEmailUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class QeUserServiceImpl implements QeUserService {
    @Autowired
    private QeUserMapper qeUserMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private QeEmailUtil qeEmailUtil;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 用户注册
     *
     * @param qeUser 注册的用户信息
     * @return
     */
    //TODO 未添加邮件验证
    @Override
    @Transactional
    public int QeUserRegister(QeUser qeUser, String qeEmailCode) {
        String md5Password = SecureUtil.md5(qeUser.getPassword());
        qeUser.setPassword(md5Password);
        // 验证邮箱验证码
        String redisEmailCode = (String) redisTemplate.opsForValue().get(qeUser.getEmail());
        if (StrUtil.isBlank(redisEmailCode)) {
            throw new CustomException("验证码已过期", HttpStatus.FORBIDDEN);
        }
        if (!redisEmailCode.equals(qeEmailCode)) {
            throw new CustomException("验证码错误", HttpStatus.BAD_REQUEST);
        }
        // 设置用户状态为已激活
        qeUser.setEmailStatus(true);
        int i = qeUserMapper.insertQeUser(qeUser);
        if (i <= 0) {
            throw new CustomException("注册失败", HttpStatus.ERROR);
        }
        return i;
    }

    /**
     * 更新当前用户
     *
     * @param qeUser 更新的用户信息
     * @return
     */
    @Override
    public int updateCurrentQeUserInfo(QeUser qeUser) {
        Long loginId = StpUtil.getLoginIdAsLong();
        qeUser.setUserId(loginId);
        int i = qeUserMapper.updateQeUser(qeUser);
        if (i <= 0) {
            throw new CustomException("更新失败", HttpStatus.ERROR);
        }
        return i;
    }

    /**
     * 根据id查询用户信息
     *
     * @param qeUserId 用户ID
     * @return
     */
    @Override
    public QeUser queryQeUserById(Long qeUserId) {
        QeUser qeUser = qeUserMapper.selectQeUserById(qeUserId);
        if (ObjectUtil.isNull(qeUser)) {
            throw new CustomException("用户不存在", HttpStatus.NOT_FOUND);
        }
        return qeUser;
    }

    @Override
    public QeUserDetailVO queryQeUserDetailById(Long qeUserId) {
        QeUserDetailVO qeUserDetailVO = qeUserMapper.selectQeUserDetailById(qeUserId);
        if (ObjectUtil.isNull(qeUserDetailVO)) {
            throw new CustomException("用户不存在", HttpStatus.NOT_FOUND);
        }
        return qeUserDetailVO;
    }

    /**
     * 用户登录
     *
     * @param qeUserLoginVO 用户登录信息
     * @return
     */
    @Override
    public SaTokenInfo qeUserLogin(QeUserLoginVO qeUserLoginVO) {
        QeUser qeUser = qeUserMapper.selectQeUserByEmail(qeUserLoginVO.getEmail());
        // 用户不存在
        if (ObjectUtil.isNull(qeUser)) {
            throw new CustomException("用户不存在", HttpStatus.NOT_FOUND);
        }
        // 验证封禁状态
        if (!qeUser.getStatus()) {
            throw new CustomException("用户被封禁", HttpStatus.FORBIDDEN);
        }
        // 验证邮箱验证状态
        if (!qeUser.getEmailStatus()) {
            throw new CustomException("邮箱未验证通过", HttpStatus.FORBIDDEN);
        }
        if (StrUtil.equals(qeUser.getPassword(), SecureUtil.md5(qeUserLoginVO.getPassword()))) {
            StpUtil.login(qeUser.getUserId(), qeUserLoginVO.isRememberMe());
        } else {
            throw new CustomException("账号或密码错误", HttpStatus.BAD_REQUEST);
        }
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
        return saTokenInfo;
    }

    /**
     * 测试邮件发送
     *
     * @return
     */
    @Override
    public int TestEmail() throws MessagingException {
        Context emailContext = new Context();
        emailContext.setVariable("username", "测试");
        emailContext.setVariable("url", "http://www.baidu.com");
        String process = templateEngine.process("emailCheckTemplate_English.html", emailContext);
        qeEmailUtil.sendMailByThymeleaf("测试", "oimaoyanio@163.com", process);
        return 0;
    }

    /**
     * 发送邮箱验证码
     *
     * @param qeEmail
     * @return
     */
    @Override
    public int SendEmailCode(String qeEmail) {
        Context emailContext = new Context();
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
        String generateCode = randomGenerator.generate();
        emailContext.setVariable("username", qeEmail);
        emailContext.setVariable("checkCode", generateCode);
        String process = templateEngine.process("emailCheckTemplate_English.html", emailContext);
        try {
            qeEmailUtil.sendMailByThymeleaf("扶风社区注册验证码", qeEmail, process);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        // 将验证码存入缓存
        redisTemplate.opsForHash().put("emailCheckCode", qeEmail, generateCode);
        //设置验证码过期时间
        redisTemplate.expire(qeEmail, 3 * 60, java.util.concurrent.TimeUnit.SECONDS);
        return 1;
    }

}
