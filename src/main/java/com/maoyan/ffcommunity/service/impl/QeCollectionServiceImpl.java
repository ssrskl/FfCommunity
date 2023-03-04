package com.maoyan.ffcommunity.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.QeCollection;
import com.maoyan.ffcommunity.exception.CustomException;
import com.maoyan.ffcommunity.mapper.QeArticleMapper;
import com.maoyan.ffcommunity.mapper.QeCollectionMapper;
import com.maoyan.ffcommunity.service.QeCollectionService;
import com.maoyan.ffcommunity.utils.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QeCollectionServiceImpl implements QeCollectionService {

    @Autowired
    private QeCollectionMapper qeCollectionMapper;

    @Autowired
    private QeArticleMapper qeArticleMapper;

    /**
     * 收藏文章
     *
     * @param qeArticleId
     * @return
     * @description: 1.判断文章是否存在 2.判断文章是否已经收藏 3.收藏文章
     */
    @Override
    public int collectionQeArticleById(Long qeArticleId) {
        QeArticle qeArticle = qeArticleMapper.selectQeArticleById(qeArticleId);
        if (ObjectUtil.isNull(qeArticle)) {
            throw new CustomException("文章不存在", HttpStatus.NOT_FOUND);
        }
        //判断文章是否已经收藏，通过全局异常处理来处理
        int i = qeCollectionMapper.insertQeCollectionById(qeArticleId, StpUtil.getLoginIdAsLong());
        if (i <= 0) {
            throw new CustomException("收藏失败", HttpStatus.ERROR);
        }
        return i;
    }

    /**
     * 取消收藏文章
     *
     * @param qeArticleId
     * @return
     * @description: 1.判断文章是否存在 2.判断文章是否已经收藏 3.取消收藏文章
     */
    @Override
    public int cancelCollectionQeArticleById(Long qeArticleId) {
        QeArticle qeArticle = qeArticleMapper.selectQeArticleById(qeArticleId);
        if (ObjectUtil.isNull(qeArticle)) {
            throw new CustomException("文章不存在", HttpStatus.NOT_FOUND);
        }
        QeCollection qeCollection = qeCollectionMapper.selectQeCollectionById(qeArticleId, StpUtil.getLoginIdAsLong());
        if (ObjectUtil.isNull(qeCollection)) {
            throw new CustomException("文章未收藏", HttpStatus.NOT_FOUND);
        }
        int i = qeCollectionMapper.deleteQeCollectionById(qeArticleId, StpUtil.getLoginIdAsLong());
        if (i <= 0) {
            throw new CustomException("取消收藏失败", HttpStatus.ERROR);
        }
        return i;
    }
}
