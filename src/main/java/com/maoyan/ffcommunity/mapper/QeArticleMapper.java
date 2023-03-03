package com.maoyan.ffcommunity.mapper;

import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.vo.qearticle.QeArticleQueryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QeArticleMapper {
    QeArticle selectQeArticleById(Long qeArticleId);// 根据ID查询文章[开发中]

    List<QeArticleQueryVO> commonSelectQeArticleQuery(QeArticle qeArticle);//通用查询文章[开发中]
}
