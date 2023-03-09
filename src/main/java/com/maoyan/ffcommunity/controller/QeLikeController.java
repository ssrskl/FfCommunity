package com.maoyan.ffcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.vo.qearticlelike.QeArticleLikeQueryVO;
import com.maoyan.ffcommunity.service.QeLikeService;
import com.maoyan.ffcommunity.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QeLikeController {

    @Autowired
    private QeLikeService qeLikeService;

    @GetMapping("/like/article")
    public AjaxResult likeQeArticleById(Long qeArticleId) {
        int i = qeLikeService.likeQeArticleById(qeArticleId);
        if (i == 1) {
            return AjaxResult.success("点赞成功");
        } else {
            return AjaxResult.error("点赞失败");
        }
    }

    @GetMapping("/cancellike/article")
    public AjaxResult cancelLikeQeArticleById(Long qeArticleId) {
        int i = qeLikeService.cancelLikeQeArticleById(qeArticleId);
        if (i == 1) {
            return AjaxResult.success("取消点赞成功");
        } else {
            return AjaxResult.error("取消点赞失败");
        }
    }

    @GetMapping("/checklike/article")
    public AjaxResult checkLikeStatusByArticleId(Long qeArticleId) {
        int i = qeLikeService.checkLikeStatusByArticleId(qeArticleId);
        if (i == 1) {
            return AjaxResult.success("已点赞");
        } else {
            return AjaxResult.success("未点赞");
        }
    }

    /**
     * 根据用户id查询点赞文章
     *
     * @param pageNum
     * @param pageSize
     * @param qeUserId
     * @return
     */
    @GetMapping("/like/article/query")
    public AjaxResult queryQeArticleLikeByQeUserId(@RequestParam(defaultValue = "1") int pageNum,
                                                   @RequestParam(defaultValue = "10") int pageSize,
                                                   Long qeUserId) {
        List<QeArticleLikeQueryVO> qeArticleLikeQueryVOS = qeLikeService.queryQeArticleLikeByQeUserId(pageNum, pageSize, qeUserId);
        return AjaxResult.success("查询成功", new PageInfo<>(qeArticleLikeQueryVOS));
    }
}
