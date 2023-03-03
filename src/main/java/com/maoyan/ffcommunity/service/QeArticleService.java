package com.maoyan.ffcommunity.service;

import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.vo.qearticle.QeArticleQueryVO;

import java.util.List;

public interface QeArticleService {
    int publishQeArticle(QeArticle qeArticle);//发布一篇文章

    int deleteQeArticleById(Long qeArticleId);//删除一篇文章

    QeArticle selectQeArticleById(Long qeArticleId);//根据ID查询文章

    List<QeArticleQueryVO> commonQueryQeArticleQueryList(int pageNum, int pageSize, QeArticle qeArticle);// 通用查询文章
}
