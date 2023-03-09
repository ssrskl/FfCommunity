package com.maoyan.ffcommunity.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.QeUser;
import com.maoyan.ffcommunity.entity.vo.qearticlelike.QeArticleLikeQueryVO;
import com.maoyan.ffcommunity.enums.LikeStatusEnum;
import com.maoyan.ffcommunity.exception.CustomException;
import com.maoyan.ffcommunity.mapper.QeArticleLikeMapper;
import com.maoyan.ffcommunity.mapper.QeArticleMapper;
import com.maoyan.ffcommunity.mapper.QeUserMapper;
import com.maoyan.ffcommunity.service.QeLikeService;
import com.maoyan.ffcommunity.utils.HttpStatus;
import com.maoyan.ffcommunity.utils.LikeKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QeLikeServiceImpl implements QeLikeService {

    @Autowired
    private QeArticleMapper qeArticleMapper;
    @Autowired
    private QeUserMapper qeUserMapper;
    @Autowired
    private QeArticleLikeMapper qeArticleLikeMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 点赞指定文章
     *
     * @param qeArticleId
     * @return
     * @descrption 1.判断文章是否存在 2.判断是否已经点赞 3.执行点赞
     */
    @Override
    public int likeQeArticleById(Long qeArticleId) {
        QeArticle qeArticle = qeArticleMapper.selectQeArticleById(qeArticleId);
        if (ObjectUtil.isNull(qeArticle)) {
            throw new CustomException("文章不存在", HttpStatus.NOT_FOUND);
        }
        long currentQeUserId = StpUtil.getLoginIdAsLong();
        String likedKey = LikeKeyUtils.getLikedKey(qeArticleId, currentQeUserId);
        Object object = redisTemplate.opsForHash().get(LikeKeyUtils.MAP_KEY_USER_LIKED, likedKey);
        if (ObjectUtil.isNotNull(object)) {
            throw new CustomException("不能重复点赞", HttpStatus.BAD_REQUEST);
        }
        // 给文章点赞
        redisTemplate.opsForHash().put(LikeKeyUtils.MAP_KEY_USER_LIKED, likedKey, LikeStatusEnum.LIKE);
        // 使文章点赞数增加
        Object o = redisTemplate.opsForHash().get(LikeKeyUtils.MAP_KEY_ARTICLE_LIKED_COUNT, qeArticleId);
        if (ObjectUtil.isNull(o)) {
            redisTemplate.opsForHash().put(LikeKeyUtils.MAP_KEY_ARTICLE_LIKED_COUNT, qeArticleId, 1);
        } else {
            redisTemplate.opsForHash().increment(LikeKeyUtils.MAP_KEY_ARTICLE_LIKED_COUNT, qeArticleId, 1);
        }
        return 1;
    }

    /**
     * 取消点赞指定文章
     *
     * @param qeArticleId
     * @return
     * @descrption 1.判断文章是否存在 2.判断是否已经点赞 3.执行取消点赞
     */
    @Override
    public int cancelLikeQeArticleById(Long qeArticleId) {
        QeArticle qeArticle = qeArticleMapper.selectQeArticleById(qeArticleId);
        if (ObjectUtil.isNull(qeArticle)) {
            throw new CustomException("文章不存在", HttpStatus.NOT_FOUND);
        }
        long currentQeUserId = StpUtil.getLoginIdAsLong();
        String likedKey = LikeKeyUtils.getLikedKey(qeArticleId, currentQeUserId);
        Object object = redisTemplate.opsForHash().get(LikeKeyUtils.MAP_KEY_USER_LIKED, likedKey);
        if (ObjectUtil.isNull(object)) {
            throw new CustomException("未点赞，不能取消点赞", HttpStatus.BAD_REQUEST);
        }
        // 取消点赞
        redisTemplate.opsForHash().delete(LikeKeyUtils.MAP_KEY_USER_LIKED, likedKey);
        // 使文章点赞数减少
        Object o = redisTemplate.opsForHash().get(LikeKeyUtils.MAP_KEY_ARTICLE_LIKED_COUNT, qeArticleId);
        if (ObjectUtil.isNull(o)) {
            redisTemplate.opsForHash().put(LikeKeyUtils.MAP_KEY_ARTICLE_LIKED_COUNT, qeArticleId, 0);
        } else {
            redisTemplate.opsForHash().increment(LikeKeyUtils.MAP_KEY_ARTICLE_LIKED_COUNT, qeArticleId, -1);
        }
        return 1;
    }

    /**
     * 查看当前用户是否点赞指定文章(1:已点赞,0:未点赞)
     *
     * @param qeArticleId
     * @return
     * @descrption 1.判断文章是否存在 2.判断是否已经点赞 3.返回点赞状态
     */
    @Override
    public int checkLikeStatusByArticleId(Long qeArticleId) {
        QeArticle qeArticle = qeArticleMapper.selectQeArticleById(qeArticleId);
        if (ObjectUtil.isNull(qeArticle)) {
            throw new CustomException("文章不存在", HttpStatus.NOT_FOUND);
        }
        long currentQeUserId = StpUtil.getLoginIdAsLong();
        String likedKey = LikeKeyUtils.getLikedKey(qeArticleId, currentQeUserId);
        Object object = redisTemplate.opsForHash().get(LikeKeyUtils.MAP_KEY_USER_LIKED, likedKey);
        if (ObjectUtil.isNotNull(object)) {
            return 1;
        }
        return 0;
    }

    /**
     * 查询指定用户点赞的文章
     *
     * @param qeUserId
     * @return
     * @descrption 1.判断用户是否存在 2.查询用户点赞的文章
     */
    @Override
    public List<QeArticleLikeQueryVO> queryQeArticleLikeByQeUserId(int pageNum, int pageSize, Long qeUserId) {
        QeUser qeUser = qeUserMapper.selectQeUserById(qeUserId);
        if (ObjectUtil.isNull(qeUser)) {
            throw new CustomException("用户不存在", HttpStatus.NOT_FOUND);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<QeArticleLikeQueryVO> qeArticleLikeQueryVOS = qeArticleLikeMapper.selectQeArticleByQeUserId(qeUserId);
        return qeArticleLikeQueryVOS;
    }

}
