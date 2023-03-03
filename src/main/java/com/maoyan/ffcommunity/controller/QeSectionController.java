package com.maoyan.ffcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.maoyan.ffcommunity.entity.QeSection;
import com.maoyan.ffcommunity.entity.vo.qesection.QeSectionQueryVO;
import com.maoyan.ffcommunity.entity.vo.qesection.QeSectionVO;
import com.maoyan.ffcommunity.service.QeSectionService;
import com.maoyan.ffcommunity.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/section")
public class QeSectionController {

    @Autowired
    private QeSectionService qeSectionService;

    /**
     * 查询指定板块
     *
     * @param qeSectionId
     * @return
     */
    @GetMapping(value = "/query/{qeSectionId}")
    public AjaxResult queryQeSectionById(@PathVariable Long qeSectionId) {
        QeSection qeSection = qeSectionService.queryQeSectionById(qeSectionId);
        return AjaxResult.success("查询成功", qeSection);
    }

    /**
     * 板块通用查询
     *
     * @param pageNum
     * @param pageSize
     * @param qeSectionVO
     * @return
     */
    @GetMapping("/query")
    public AjaxResult commonQueryueryQeSection(@RequestParam(defaultValue = "1") int pageNum,
                                               @RequestParam(defaultValue = "10") int pageSize,
                                               QeSectionVO qeSectionVO) {
        List<QeSectionQueryVO> qeSectionQueryVOS = qeSectionService.commonQueryQeSectionQueryVOList(pageNum, pageSize, qeSectionVO);
        return AjaxResult.success("查询成功", new PageInfo<>(qeSectionQueryVOS));
    }
}
