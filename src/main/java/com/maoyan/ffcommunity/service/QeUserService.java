package com.maoyan.ffcommunity.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.util.SaResult;
import com.maoyan.ffcommunity.entity.QeUser;
import com.maoyan.ffcommunity.entity.vo.qeuser.QeUserDetailVO;
import com.maoyan.ffcommunity.entity.vo.qeuser.QeUserLoginVO;

public interface QeUserService {
    int QeUserRegister(QeUser qeUser);

    int updateCurrentQeUserInfo(QeUser qeUser);

    QeUser queryQeUserById(Long qeUserId);

    QeUserDetailVO queryQeUserDetailById(Long qeUserId);

    SaTokenInfo qeUserLogin(QeUserLoginVO qeUserLoginVO);
}
