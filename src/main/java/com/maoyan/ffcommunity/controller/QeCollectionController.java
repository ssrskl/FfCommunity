package com.maoyan.ffcommunity.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.maoyan.ffcommunity.service.QeCollectionService;
import com.maoyan.ffcommunity.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
public class QeCollectionController {

    @Autowired
    private QeCollectionService qeCollectionService;

    @SaCheckLogin
    @GetMapping(value = "/collect")
    public AjaxResult collectionQeArticleById(Long qeArticleId) {
        int i = qeCollectionService.collectionQeArticleById(qeArticleId);
        return AjaxResult.success("收藏成功", i);
    }

    @SaCheckLogin
    @GetMapping(value = "/cancel")
    public AjaxResult cancelCollectionQeArticleById(Long qeArticleId) {
        int i = qeCollectionService.cancelCollectionQeArticleById(qeArticleId);
        return AjaxResult.success("取消收藏成功", i);
    }
}
