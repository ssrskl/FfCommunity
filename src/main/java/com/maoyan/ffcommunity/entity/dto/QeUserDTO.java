package com.maoyan.ffcommunity.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeUserDTO {
    private String userName;
    private String email;
    private String phoneNumber;
    private String sex;
    private String avatar;
    private String password;
    private String loginIp;
    private LocalDateTime loginDate;
    private String signature;
    private Long grade;
    private Long experience;
    private Boolean emailStatus;
    private Long schoolId;
    private LocalDateTime updateTime;
}
