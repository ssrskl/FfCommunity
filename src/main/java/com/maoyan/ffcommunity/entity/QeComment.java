package com.maoyan.ffcommunity.entity;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class QeComment {
    /**
     * 评论ID
     */
    private Long commentId;

    /**
     * 评论所在的文章ID
     */
    private Long articleId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论者ID
     */
    private Long commentUserId;

    /**
     * 状态（1为正常，0为封禁）
     */
    private Boolean status;

    /**
     * 被回复的人的ID
     */
    private Long toUserId;

    /**
     * 父评论ID,回复的评论的ID(0则为是评论而不是回复)
     */
    private Long replyId;

    /**
     * 根评论ID(为0则为根评论)
     */
    private Long rootId;

    /**
     * 删除标志(不为0则删除,否则等于id)
     */
    private Boolean deleteFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}

