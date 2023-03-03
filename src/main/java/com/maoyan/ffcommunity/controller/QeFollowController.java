package com.maoyan.ffcommunity.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.pagehelper.PageInfo;
import com.maoyan.ffcommunity.entity.vo.qeuserfollow.QeUserFollowFanVO;
import com.maoyan.ffcommunity.entity.vo.qeuserfollow.QeUserFollowedVO;
import com.maoyan.ffcommunity.service.QeFollowService;
import com.maoyan.ffcommunity.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class QeFollowController {

    @Autowired
    private QeFollowService qeFollowService;

    /**
     * 关注指定用户【开发完成】
     *
     * @param qeUserId
     * @return
     */
    @SaCheckLogin
    @GetMapping("/user")
    public AjaxResult followQeUser(Long qeUserId) {
        int i = qeFollowService.followOtherQeUser(qeUserId);
        return AjaxResult.success("关注成功", i);
    }

    /**
     * 取消关注指定的用户【开发完毕】
     *
     * @param qeUserId
     * @return
     */
    @SaCheckLogin
    @GetMapping(value = "/cancel")
    public AjaxResult cancelFollowQeUser(Long qeUserId) {
        int i = qeFollowService.cancelFollowQeUserById(qeUserId);
        return AjaxResult.success("取消关注成功", i);
    }

    /**
     * 查找指定用户的粉丝【开发完成】
     *
     * @param pageNum
     * @param pageSize
     * @param qeUserId
     * @return
     */
    @GetMapping(value = "/fans")
    public AjaxResult queryFansById(@RequestParam(defaultValue = "1") int pageNum,
                                    @RequestParam(defaultValue = "10") int pageSize,
                                    Long qeUserId) {
        List<QeUserFollowFanVO> qeUserFollowFanVOS = qeFollowService.queryFansById(pageNum, pageSize, qeUserId);
        return AjaxResult.success(new PageInfo<>(qeUserFollowFanVOS));
    }

    /**
     * 查找指定用户关注的用户【开发完成】
     *
     * @param pageNum
     * @param pageSize
     * @param qeUserId
     * @return
     */
    @GetMapping(value = "/followed")
    public AjaxResult queryFollowedById(@RequestParam(defaultValue = "1") int pageNum,
                                        @RequestParam(defaultValue = "10") int pageSize,
                                        Long qeUserId) {
        List<QeUserFollowedVO> qeUserFollowedVOS = qeFollowService.queryFollowedById(pageNum, pageSize, qeUserId);
        return AjaxResult.success(new PageInfo<>(qeUserFollowedVOS));
    }
}
