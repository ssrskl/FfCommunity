package com.maoyan.ffcommunity.entity.vo.qeuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeUserLoginVO {
    private String email;
    private String password;

    private boolean isRememberMe;
}
