package com.maoyan.ffcommunity.mapper;

import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.QeArticleLike;
import com.maoyan.ffcommunity.entity.vo.qearticlelike.QeArticleLikeQueryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章点赞Mapper
 */
@Mapper
public interface QeArticleLikeMapper {
    int deleteByPrimaryKey(Long likeId); //删除点赞数据

    int insertQeArticleLike(QeArticleLike qeArticleLike);//插入点赞数据

    int countQeArticleLikeByQeArticleId(Long qeArticleId);//根据文章id查询点赞数量

    List<QeArticleLikeQueryVO> selectQeArticleByQeUserId(Long qeUserId);//根据用户id查询点赞文章
}
