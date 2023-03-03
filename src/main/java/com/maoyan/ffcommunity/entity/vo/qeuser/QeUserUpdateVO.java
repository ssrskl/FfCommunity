package com.maoyan.ffcommunity.entity.vo.qeuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeUserUpdateVO {
    private String userName;
    private String email;
    private String phoneNumber;
    private String sex;
    private String avatar;
    private String password;
    private String signature;
    private Long schoolId;
}
