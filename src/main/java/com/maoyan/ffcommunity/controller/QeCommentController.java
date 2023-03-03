package com.maoyan.ffcommunity.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.pagehelper.PageInfo;
import com.maoyan.ffcommunity.entity.QeComment;
import com.maoyan.ffcommunity.entity.vo.qecomment.QeCommentDetailVO;
import com.maoyan.ffcommunity.entity.vo.qecomment.QeCommentReceiveVO;
import com.maoyan.ffcommunity.service.QeCommentService;
import com.maoyan.ffcommunity.utils.AjaxResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class QeCommentController {

    @Autowired
    private QeCommentService qeCommentService;

    @SaCheckLogin
    @PostMapping(value = "/publish")
    public AjaxResult publishQeComment(@RequestBody QeCommentReceiveVO qeCommentReceiveVO) {
        QeComment qeComment = new QeComment();
        BeanUtils.copyProperties(qeCommentReceiveVO, qeComment);
        int i = qeCommentService.publishQeComment(qeComment);
        return AjaxResult.success("发布成功", i);
    }

    @GetMapping(value = "/query")
    public AjaxResult commonQueryQeCommentDetailList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                     QeComment qeComment) {
        List<QeCommentDetailVO> qeCommentDetailVOS = qeCommentService.commonQueryQeCommentDetailList(pageNum, pageSize, qeComment);
        return AjaxResult.success("查询成功", new PageInfo<>(qeCommentDetailVOS));
    }
}
