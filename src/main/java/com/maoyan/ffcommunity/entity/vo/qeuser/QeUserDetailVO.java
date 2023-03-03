package com.maoyan.ffcommunity.entity.vo.qeuser;

import com.maoyan.ffcommunity.entity.QeSchool;
import com.maoyan.ffcommunity.entity.QeUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeUserDetailVO {
    private Long userId;
    private String userName;
    private String email;
    private String phoneNumber;
    private String sex;
    private String avatar;
    private Boolean status;
    private String loginIp;
    private LocalDateTime loginDate;
    private String signature;
    private Long grade;
    private Long experience;
    private Boolean emailStatus;
    private Long schoolId;
    private Boolean deleteFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private QeUserRole qeUserRole;
    private QeSchool qeSchool;
}
