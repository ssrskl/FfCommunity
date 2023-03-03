package com.maoyan.ffcommunity.entity.vo.qecomment;

import com.maoyan.ffcommunity.entity.QeUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeCommentDetailVO {
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

    /**
     * 评论者
     */
    private QeUser commonQeUser;
    /**
     * 被回复的人
     */
    private QeUser toQeUser;
}
