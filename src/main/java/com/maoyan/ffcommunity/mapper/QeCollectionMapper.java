package com.maoyan.ffcommunity.mapper;

import com.maoyan.ffcommunity.entity.QeCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 文章收藏Mapper
 */
@Mapper
public interface QeCollectionMapper {
    QeCollection selectQeCollectionById(@Param("qeArticleId") Long qeArticleId, @Param("qeUserId") Long qeUserId);//查询是否收藏

    int insertQeCollectionById(@Param("qeArticleId") Long qeArticleId, @Param("qeUserId") Long qeUserId);//收藏

    int deleteQeCollectionById(@Param("qeArticleId") Long qeArticleId, @Param("qeUserId") Long qeUserId);//取消收藏
}
