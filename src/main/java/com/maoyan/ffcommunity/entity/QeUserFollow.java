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
public class QeUserFollow {
    private Long followId;
    private Long giveFollowQeuserId;
    private Long getFollowQeuserId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
