package com.maoyan.ffcommunity.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeSection implements Serializable {
    /**
     * 版块主键
     */
    private Long sectionId;

    /**
     * 版块名称
     */
    private String sectionName;

    /**
     * 版块介绍
     */
    private String sectionIntroduce;

    /**
     * 版块logo
     */
    private String sectionLogo;

    /**
     * 版块背景图
     */
    private String sectionBackground;

    /**
     * 版主用户ID
     */
    private Long sectionAdminUserId;

    /**
     * 版块权重
     */
    private Long sectionWeight;

    /**
     * 板块是否置顶(1为置顶，0为不置顶)
     */
    private Boolean sectionTop;

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

