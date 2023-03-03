package com.maoyan.ffcommunity.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.pagehelper.PageInfo;
import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.vo.qearticle.QeArticleQueryVO;
import com.maoyan.ffcommunity.entity.vo.qearticle.QeArticleReceiveVO;
import com.maoyan.ffcommunity.service.QeArticleService;
import com.maoyan.ffcommunity.utils.AjaxResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class QeArticleController {

    @Autowired
    private QeArticleService qeArticleService;

    @GetMapping(value = "/{qeArticleId}")
    public AjaxResult queryQeArticleById(@PathVariable Long qeArticleId) {
        QeArticle qeArticle = qeArticleService.selectQeArticleById(qeArticleId);
        return AjaxResult.success("查询成功", qeArticle);
    }

    @SaCheckLogin
    @PostMapping(value = "/publish")
    public AjaxResult publishQeArticle(@RequestBody QeArticleReceiveVO qeArticleReceiveVO) {
        QeArticle qeArticle = new QeArticle();
        BeanUtils.copyProperties(qeArticleReceiveVO, qeArticle);
        int i = qeArticleService.publishQeArticle(qeArticle);
        return AjaxResult.success("发布成功", i);
    }

    @GetMapping(value = "/delete")
    public AjaxResult deleteQeArticleById(Long qeArticleId) {
        int i = qeArticleService.deleteQeArticleById(qeArticleId);
        return AjaxResult.success("删除成功", i);
    }

    @GetMapping(value = "/query")
    public AjaxResult commonQueryQeArticle(@RequestParam(defaultValue = "1") int pageNum,
                                           @RequestParam(defaultValue = "10") int pageSize,
                                           QeArticle qeArticle) {
        List<QeArticleQueryVO> qeArticleQueryVOS = qeArticleService.commonQueryQeArticleQueryList(pageNum, pageSize, qeArticle);
        return AjaxResult.success("查询成功", new PageInfo<>(qeArticleQueryVOS));
    }
}
