package com.maoyan.ffcommunity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeSectionType {
    /**
     * 版块分类主键
     */
    private Long sectionTypeId;

    /**
     * 版块分类名称
     */
    private String sectionTypeName;

    /**
     * 分类所属的版块ID
     */
    private Long sectionId;

    /**
     * 分类权重
     */
    private Long sectionTypeWeight;

    /**
     * 分类类型（0-普通分类，1-特殊分类）
     */
    private Boolean sectionTypeMold;

    /**
     * 分类网址
     */
    private String sectionTypeNetwork;

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

}

