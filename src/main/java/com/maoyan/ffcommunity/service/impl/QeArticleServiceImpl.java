package com.maoyan.ffcommunity.service.impl;

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
