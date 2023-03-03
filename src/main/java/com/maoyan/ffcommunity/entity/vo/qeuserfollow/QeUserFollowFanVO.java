package com.maoyan.ffcommunity.entity.vo.qeuserfollow;

import com.maoyan.ffcommunity.entity.QeUser;
import com.maoyan.ffcommunity.entity.vo.qeuser.QeUserDetailVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QeUserFollowFanVO {
    private Long followId;
    private Long giveFollowQeuserId;
    private Long getFollowQeuserId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private QeUser giveFollowQeuser;
}
