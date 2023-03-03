package com.maoyan.ffcommunity.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QeUserServiceImpl implements QeUserService {
    @Autowired
    private QeUserMapper qeUserMapper;

    /**
     * 用户注册
     *
     * @param qeUser 注册的用户信息
     * @return
     */
    //TODO 未添加邮件验证
    @Override
    public int QeUserRegister(QeUser qeUser) {
        String md5Password = SecureUtil.md5(qeUser.getPassword());
        qeUser.setPassword(md5Password);
        int i = qeUserMapper.insertQeUser(qeUser);
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
}
