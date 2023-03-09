package com.maoyan.ffcommunity.service;

import com.maoyan.ffcommunity.entity.vo.qearticlelike.QeArticleLikeQueryVO;

import java.util.List;

/**
 * 点赞接口
 */
public interface QeLikeService {
    int likeQeArticleById(Long qeArticleId);

    int cancelLikeQeArticleById(Long qeArticleId);

    int checkLikeStatusByArticleId(Long qeArticleId);

    List<QeArticleLikeQueryVO> queryQeArticleLikeByQeUserId(int pageNum, int pageSize, Long qeUserId); // 根据用户id查询点赞文章
}
