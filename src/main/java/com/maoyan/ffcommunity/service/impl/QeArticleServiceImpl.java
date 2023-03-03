package com.maoyan.ffcommunity.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.vo.qearticle.QeArticleQueryVO;
import com.maoyan.ffcommunity.exception.CustomException;
import com.maoyan.ffcommunity.mapper.QeArticleMapper;
import com.maoyan.ffcommunity.service.QeArticleService;
import com.maoyan.ffcommunity.utils.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QeArticleServiceImpl implements QeArticleService {

    @Autowired
    private QeArticleMapper qeArticleMapper;

    @Override
    public int publishQeArticle(QeArticle qeArticle) {
        int i = qeArticleMapper.insertQeArticle(qeArticle);
        if (i <= 0) {
            throw new CustomException("发布失败", HttpStatus.ERROR);
        }
        return i;
    }

    /**
     * 根据ID删除一篇文章
     *
     * @param qeArticleId
     * @return
     * @description 先判定这篇文章是否存在，再判定是不是这个用户的文章
     */
    @Override
    public int deleteQeArticleById(Long qeArticleId) {
        QeArticle qeArticle = qeArticleMapper.selectQeArticleById(qeArticleId);
        if (ObjectUtil.isNull(qeArticle)) {
            throw new CustomException("此文章不存在", HttpStatus.NOT_FOUND);
        }
        Long currentQeUserId = StpUtil.getLoginIdAsLong();
        if (qeArticle.getAuthorId() != currentQeUserId) {
            throw new CustomException("不可以删除其他用户的文章", HttpStatus.FORBIDDEN);
        }
        int i = qeArticleMapper.deleteQeArticle(qeArticle.getArticleId(), currentQeUserId);
        if (i <= 0) {
            throw new CustomException("删除失败", HttpStatus.ERROR);
        }
        return i;
    }

    @Override
    public QeArticle selectQeArticleById(Long qeArticleId) {
        QeArticle qeArticle = qeArticleMapper.selectQeArticleById(qeArticleId);
        if (ObjectUtil.isNull(qeArticle)) {
            throw new CustomException("文章不存在", HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    public List<QeArticleQueryVO> commonQueryQeArticleQueryList(int pageNum, int pageSize, QeArticle qeArticle) {
        PageHelper.startPage(pageNum, pageSize);
        List<QeArticleQueryVO> qeArticleQueryVOS = qeArticleMapper.commonSelectQeArticleQuery(qeArticle);
        return qeArticleQueryVOS;
    }
}
