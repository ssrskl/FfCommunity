package com.maoyan.ffcommunity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeSchool {

    /**
     * 学校主键ID
     */
    private Long schoolId;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 学校介绍
     */
    private String schoolIntroduce;
    /**
     * 学校校徽
     */
    private String schoolBadge;
    /**
     * 学校校训
     */
    private String schoolMotto;
    /**
     * 学校背景图
     */
    private String schoolBackground;
    /**
     * 建校时间
     */
    private LocalDateTime schoolBuildDate;
    /**
     * 学校位置
     */
    private String schoolLocation;
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
