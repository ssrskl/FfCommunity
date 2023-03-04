package com.maoyan.ffcommunity.service;

import com.maoyan.ffcommunity.entity.vo.qeuserfollow.QeUserFollowFanVO;
import com.maoyan.ffcommunity.entity.vo.qeuserfollow.QeUserFollowedVO;

import java.util.List;

public interface QeFollowService {

    int followOtherQeUser(Long qeUserId); //关注指定用户

    int followQeSectionById(Long qeSectionId);//关注指定板块

    int cancelFollowQeUserById(Long qeUserId); //取消关注指定用户

    int cancelFollowQeSectionById(Long qeSectionId); //取消关注指定板块

    List<QeUserFollowFanVO> queryFansById(int pageNum, int pageSize, Long qeUserId); // 查询指定用户的粉丝

    List<QeUserFollowedVO> queryFollowedById(int pageNum, int pageSize, Long qeUserId); // 查询指定用户关注的用户
}
