package com.maoyan.ffcommunity.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import com.maoyan.ffcommunity.entity.QeUser;
import com.maoyan.ffcommunity.entity.vo.qeuser.QeUserDetailVO;
import com.maoyan.ffcommunity.entity.vo.qeuser.QeUserLoginVO;
import com.maoyan.ffcommunity.entity.vo.qeuser.QeUserUpdateVO;
import com.maoyan.ffcommunity.entity.vo.qeuser.QeUserVO;
import com.maoyan.ffcommunity.service.QeUserService;
import com.maoyan.ffcommunity.utils.AjaxResult;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class QeUserController {

    @Autowired
    private QeUserService qeUserService;

    @PostMapping("/login")
    public AjaxResult qeUserLogin(@RequestBody QeUserLoginVO qeUserLoginVO) {
        SaTokenInfo saTokenInfo = qeUserService.qeUserLogin(qeUserLoginVO);
        return AjaxResult.success("登录成功", saTokenInfo);
    }

    @PostMapping("/register")
    public AjaxResult qeUserRegister(@RequestBody QeUserVO qeUserVO) {
        QeUser qeUser = new QeUser();
        BeanUtils.copyProperties(qeUserVO, qeUser);
        int i = qeUserService.QeUserRegister(qeUser,qeUserVO.getQeEmailCode());
        return AjaxResult.success(i);
    }


    @GetMapping(value = "/{qeUserId}")
    public AjaxResult queryQeUserDetail(@PathVariable Long qeUserId) {
        QeUserDetailVO qeUserDetailVO = qeUserService.queryQeUserDetailById(qeUserId);
        return AjaxResult.success(qeUserDetailVO);
    }

    @SaCheckLogin
    @PostMapping("/update")
    public AjaxResult updateCurrentQeUserInfo(@RequestBody QeUserUpdateVO qeUserUpdateVO) {
        QeUser qeUser = new QeUser();
        BeanUtils.copyProperties(qeUserUpdateVO, qeUser);
        int i = qeUserService.updateCurrentQeUserInfo(qeUser);
        return AjaxResult.success("更新成功", i);
    }

    @GetMapping("/email")
    public AjaxResult SendEmailCode(String qeEmail) {
        int i = qeUserService.SendEmailCode(qeEmail);
        return AjaxResult.success(i);
    }

    @GetMapping("/test/email")
    public AjaxResult TestEmail() throws MessagingException {
        int i = qeUserService.TestEmail();
        return AjaxResult.success(i);
    }


}
