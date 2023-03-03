package com.maoyan.ffcommunity.entity.vo.qecomment;


public class QeCommentReceiveVO {
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
}
