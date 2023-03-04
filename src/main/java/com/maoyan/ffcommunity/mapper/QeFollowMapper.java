package com.maoyan.ffcommunity.mapper;

import com.maoyan.ffcommunity.entity.QeSection;
import com.maoyan.ffcommunity.entity.QeSectionFollow;
import com.maoyan.ffcommunity.entity.QeUserFollow;
import com.maoyan.ffcommunity.entity.vo.qeuserfollow.QeUserFollowFanVO;
import com.maoyan.ffcommunity.entity.vo.qeuserfollow.QeUserFollowedVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QeFollowMapper {
    int insertQeUserFollow(QeUserFollow qeUserFollow); // 插入用户关注数据

    int insertQeSectionFollow(QeSectionFollow qeSectionFollow);// 插入用户关注板块的数据

    int deleteQeUserFollow(@Param("giveFollowQeUserId") Long giveFollowQeUserId, @Param("getFollowQeUserId") Long getFollowQeUserId);// 删除关注记录

    int deleteQeSectionFollow(@Param("giveFollowQeUserId") Long giveFollowQeUserId, @Param("getFollowQeSectionId") Long getFollowQeSectionId);// 删除板块关注记录

    QeUserFollow selectQeUserFollow(@Param("giveFollowQeUserId") Long giveFollowQeUserId, @Param("getFollowQeUserId") Long getFollowQeUserId);// 查询关注数据

    QeSectionFollow selectQeSectionFollow(@Param("giveFollowQeUserId") Long giveFollowQeUserId, @Param("getFollowQeSectionId") Long getFollowQeSectionId); // 查询板块关注数据

    List<QeUserFollowFanVO> selectGiveFollowersById(Long qeFollowedUserId); // 通过被关注人的ID，获得关注他的人

    List<QeUserFollowedVO> selectGetFollowersById(Long qeFollowUserId); // 获得被关注的人
}
