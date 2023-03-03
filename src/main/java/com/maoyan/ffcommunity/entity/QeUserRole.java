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
public class QeUserRole {
    private Long userRoleId;
    private String roleName;
    private Long userId;
    private String rolePermission;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
