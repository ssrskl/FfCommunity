package com.maoyan.ffcommunity.mapper;

import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.vo.qearticle.QeArticleQueryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QeArticleMapper {
    int insertQeArticle(QeArticle qeArticle);//添加文章

    int updateQeArticle(QeArticle qeArticle);//更新文章

    int deleteQeArticle(Long qeArticleId, Long authorId);// 删除文章

    QeArticle selectQeArticleById(Long qeArticleId);// 根据ID查询文章[开发完成]

    List<QeArticleQueryVO> commonSelectQeArticleQuery(QeArticle qeArticle);//通用查询文章[开发完成]
}
