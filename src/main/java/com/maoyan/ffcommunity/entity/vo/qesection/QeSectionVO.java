package com.maoyan.ffcommunity.entity.vo.qesection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeSectionVO {
    /**
     * 版块主键
     */
    private Long sectionId;

    /**
     * 版块名称
     */
    private String sectionName;

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
}
