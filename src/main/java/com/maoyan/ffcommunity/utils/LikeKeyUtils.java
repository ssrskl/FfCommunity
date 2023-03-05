package com.maoyan.ffcommunity.utils;

/**
 * 点赞工具类
 */
public class LikeKeyUtils {

    //保存用户点赞数据的key
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";
    //保存文章被点赞数量的key
    public static final String MAP_KEY_ARTICLE_LIKED_COUNT = "MAP_ARTICLE_LIKED_COUNT";

    /**
     * 拼接被点赞的用户id和点赞的人的id作为key。格式 222222::333333
     * key这么设计的原因是key是唯一的，而不管使用用户id，或者文章id来做key，都会造成重复
     *
     * @param dqArticleId 被点赞的文章id
     * @param dqUserId    点赞的人的id
     * @return
     */
    public static String getLikedKey(Long dqArticleId, Long dqUserId) {
        StringBuilder builder = new StringBuilder();
        builder.append(dqArticleId);
        builder.append("::");
        builder.append(dqUserId);
        return builder.toString();
    }
}
