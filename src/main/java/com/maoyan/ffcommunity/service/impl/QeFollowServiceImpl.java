package com.maoyan.ffcommunity.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.maoyan.ffcommunity.entity.QeSection;
import com.maoyan.ffcommunity.entity.QeSectionFollow;
import com.maoyan.ffcommunity.entity.QeUser;
import com.maoyan.ffcommunity.entity.QeUserFollow;
import com.maoyan.ffcommunity.entity.vo.qeuserfollow.QeUserFollowFanVO;
import com.maoyan.ffcommunity.entity.vo.qeuserfollow.QeUserFollowedVO;
import com.maoyan.ffcommunity.exception.CustomException;
import com.maoyan.ffcommunity.mapper.QeFollowMapper;
import com.maoyan.ffcommunity.mapper.QeSectionMapper;
import com.maoyan.ffcommunity.mapper.QeUserMapper;
import com.maoyan.ffcommunity.service.QeFollowService;
import com.maoyan.ffcommunity.utils.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QeFollowServiceImpl implements QeFollowService {

    @Autowired
    private QeFollowMapper qeFollowMapper;
    @Autowired
    private QeUserMapper qeUserMapper;
    @Autowired
    private QeSectionMapper qeSectionMapper;

    /**
     * 关注指定用户
     *
     * @param qeUserId
     * @return
     */
    @Override
    public int followOtherQeUser(Long qeUserId) {
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        if (qeUserId == loginIdAsLong) {
            throw new CustomException("不能关注自己", HttpStatus.BAD_REQUEST);
        }
        QeUser getFollowQeUser = qeUserMapper.selectQeUserById(qeUserId);
        if (ObjectUtil.isNull(getFollowQeUser)) {
            throw new CustomException("关注的用户不存在", HttpStatus.NOT_FOUND);
        }
        QeUserFollow qeUserFollow = new QeUserFollow();
        qeUserFollow.setGiveFollowQeuserId(loginIdAsLong);
        qeUserFollow.setGetFollowQeuserId(qeUserId);
        int i = qeFollowMapper.insertQeUserFollow(qeUserFollow);
        if (i <= 0) {
            throw new CustomException("关注失败", HttpStatus.ERROR);
        }
        return i;
    }

    /**
     * 关注指定板块
     *
     * @param qeSectionId
     * @return
     * @Description 先判断板块是否存在，再判断是否已经关注了
     */
    @Override
    public int followQeSectionById(Long qeSectionId) {
        QeSection qeSection = qeSectionMapper.selectQeSectionById(qeSectionId);
        if (ObjectUtil.isNull(qeSection)) {
            throw new CustomException("关注的板块不存在", HttpStatus.NOT_FOUND);
        }
        return 0;
    }

    /**
     * 取消关注指定用户
     *
     * @param qeUserId
     * @return
     * @Description 先查询这个用户是否存在，再查询是否关注了这个用户
     */
    @Override
    public int cancelFollowQeUserById(Long qeUserId) {
        QeUser qeUser = qeUserMapper.selectQeUserById(qeUserId);
        if (ObjectUtil.isNull(qeUser)) {
            throw new CustomException("该用户不存在", HttpStatus.NOT_FOUND);
        }
        QeUserFollow qeUserFollow = qeFollowMapper.selectQeUserFollow(StpUtil.getLoginIdAsLong(), qeUserId);
        if (ObjectUtil.isNull(qeUserFollow)) {
            throw new CustomException("您没有关注此用户", HttpStatus.NOT_FOUND);
        }
        int i = qeFollowMapper.deleteQeUserFollow(StpUtil.getLoginIdAsLong(), qeUserId);
        if (i <= 0) {
            throw new CustomException("取消关注失败", HttpStatus.ERROR);
        }
        return i;
    }

    /**
     * 取消关注指定板块
     *
     * @param qeSectionId
     * @return
     * @Description 先查询这个板块是否存在，再查询是否关注了这个板块
     */
    @Override
    public int cancelFollowQeSectionById(Long qeSectionId) {
        QeSection qeSection = qeSectionMapper.selectQeSectionById(qeSectionId);
        if (ObjectUtil.isNull(qeSection)) {
            throw new CustomException("该板块不存在", HttpStatus.NOT_FOUND);
        }
        QeSectionFollow qeSectionFollow = qeFollowMapper.selectQeSectionFollow(StpUtil.getLoginIdAsLong(), qeSectionId);
        if (ObjectUtil.isNull(qeSectionFollow)) {
            throw new CustomException("您没有关注此板块", HttpStatus.NOT_FOUND);
        }
        int i = qeFollowMapper.deleteQeSectionFollow(StpUtil.getLoginIdAsLong(), qeSectionId);
        if (i <= 0) {
            throw new CustomException("取消关注失败", HttpStatus.ERROR);
        }
        return i;
    }

    /**
     * 查找指定ID用户的粉丝
     *
     * @param qeUserId
     * @return
     */
    @Override
    public List<QeUserFollowFanVO> queryFansById(int pageNum, int pageSize, Long qeUserId) {
        PageHelper.startPage(pageNum, pageSize);
        List<QeUserFollowFanVO> qeUserFollowFanVOS = qeFollowMapper.selectGiveFollowersById(qeUserId);
        return qeUserFollowFanVOS;
    }

    /**
     * 查询指定用户关注的人
     *
     * @param pageNum
     * @param pageSize
     * @param qeUserId
     * @return
     */
    @Override
    public List<QeUserFollowedVO> queryFollowedById(int pageNum, int pageSize, Long qeUserId) {
        PageHelper.startPage(pageNum, pageSize);
        List<QeUserFollowedVO> qeUserFollowedVOS = qeFollowMapper.selectGetFollowersById(qeUserId);
        return qeUserFollowedVOS;
    }
}
