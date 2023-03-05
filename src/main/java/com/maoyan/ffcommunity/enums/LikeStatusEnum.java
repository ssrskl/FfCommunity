package com.maoyan.ffcommunity.enums;

import lombok.Getter;

/**
 * 点赞状态
 */
@Getter
public enum LikeStatusEnum {
    LIKE(1, "点赞"),

    UNLIKE(0, "取消点赞");

    private Integer code;

    private String msg;

    LikeStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
