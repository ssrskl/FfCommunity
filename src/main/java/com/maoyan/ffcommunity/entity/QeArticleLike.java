package com.maoyan.ffcommunity.entity;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QeArticleLike {
    /**
     * 主键ID
     */
    private Long likeId;

    /**
     * 发起点赞的用户的ID
     */
    private Long giveLikeQeuserId;

    /**
     * 被点赞的文章的ID
     */
    private Long getLikeQearticleId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}

