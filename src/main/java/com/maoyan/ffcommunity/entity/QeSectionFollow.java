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
public class QeSectionFollow {
    /**
     * 主键ID
     */
    private Long followId;

    /**
     * 发起关注的用户的ID
     */
    private Long giveFollowQeuserId;

    /**
     * 被关注的版块的ID
     */
    private Long getFollowQesectionId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}

