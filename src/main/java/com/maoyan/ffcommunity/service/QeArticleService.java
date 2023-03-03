package com.maoyan.ffcommunity.service;

import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.vo.qearticle.QeArticleQueryVO;

import java.util.List;

public interface QeArticleService {
    QeArticle selectQeArticleById(Long qeArticleId);//根据ID查询文章

    List<QeArticleQueryVO> commonQueryQeArticleQueryList(int pageNum, int pageSize, QeArticle qeArticle);// 通用查询文章
}
