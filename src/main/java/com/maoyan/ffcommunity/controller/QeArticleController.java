package com.maoyan.ffcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.QeSchool;
import com.maoyan.ffcommunity.entity.vo.qearticle.QeArticleQueryVO;
import com.maoyan.ffcommunity.service.QeArticleService;
import com.maoyan.ffcommunity.utils.AjaxResult;
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

    @GetMapping(value = "/query")
    public AjaxResult commonQueryQeArticle(@RequestParam(defaultValue = "1") int pageNum,
                                           @RequestParam(defaultValue = "10") int pageSize,
                                           QeArticle qeArticle) {
        List<QeArticleQueryVO> qeArticleQueryVOS = qeArticleService.commonQueryQeArticleQueryList(pageNum, pageSize, qeArticle);
        return AjaxResult.success("查询成功", new PageInfo<>(qeArticleQueryVOS));
    }
}
