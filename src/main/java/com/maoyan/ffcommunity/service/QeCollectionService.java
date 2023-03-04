package com.maoyan.ffcommunity.service;

public interface QeCollectionService {
    int collectionQeArticleById(Long qeArticleId);//收藏

    int cancelCollectionQeArticleById(Long qeArticleId);//取消收藏
}
