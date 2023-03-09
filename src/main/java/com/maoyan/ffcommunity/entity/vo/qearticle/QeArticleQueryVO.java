package com.maoyan.ffcommunity.entity.vo.qearticle;

import com.maoyan.ffcommunity.entity.QeSection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeArticleQueryVO {
    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章首页图片
     */
    private String articleImage;

    /**
     * 文章所属版块ID
     */
    private Long sectionId;

    /**
     * 文章所在版块中的分类的ID
     */
    private Long sectionTypeId;

    /**
     * 作者ID
     */
    private Long authorId;

    /**
     * 状态（1为正常，0为封禁）
     */
    private Boolean status;

    /**
     * 文章权重
     */
    private Long articleWeight;

    /**
     * 文章是否置顶(1为置顶)
     */
    private Boolean articleTop;

    /**
     * 删除标志(不为0则删除,否则等于id)
     */
    private Boolean deleteFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 文章所属板块
     */
    private QeSection qeSection;

    private Long likeCount; // 点赞数量
    private Long collectionCount;// 收藏数量
    /**
     * 文章评论数量
     */
    private Long commentCount;
}
