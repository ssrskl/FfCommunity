package com.maoyan.ffcommunity.entity;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeCollection {
    /**
     * 收藏ID
     */
    private Long collectionId;

    /**
     * 发起收藏的用户的ID
     */
    private Long userId;

    /**
     * 被收藏的文章的ID
     */
    private Long articleId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}

