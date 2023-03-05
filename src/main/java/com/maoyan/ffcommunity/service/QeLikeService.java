package com.maoyan.ffcommunity.service;

/**
 * 点赞接口
 */
public interface QeLikeService {
    int likeQeArticleById(Long qeArticleId);

    int cancelLikeQeArticleById(Long qeArticleId);

    int checkLikeStatusByArticleId(Long qeArticleId);
}
