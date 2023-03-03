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
public class QeArticleReceiveVO {
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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
