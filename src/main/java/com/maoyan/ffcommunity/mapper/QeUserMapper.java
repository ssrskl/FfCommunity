package com.maoyan.ffcommunity.mapper;

import com.maoyan.ffcommunity.entity.QeUser;
import com.maoyan.ffcommunity.entity.vo.qeuser.QeUserDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QeUserMapper {
    int insertQeUser(QeUser qeUser);

    int updateQeUser(QeUser qeUser);

    QeUser selectQeUserById(Long qeUserId);

    QeUserDetailVO selectQeUserDetailById(Long qeUserId);

    QeUser selectQeUserByEmail(String qeUserEmail);

    List<QeUser> commonSelectQeUser(QeUser qeUser);
}
