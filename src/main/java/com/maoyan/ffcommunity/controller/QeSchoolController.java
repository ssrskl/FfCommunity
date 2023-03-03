package com.maoyan.ffcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.maoyan.ffcommunity.entity.QeSchool;
import com.maoyan.ffcommunity.service.QeSchoolService;
import com.maoyan.ffcommunity.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/school")
public class QeSchoolController {
    @Autowired
    private QeSchoolService qeSchoolService;

    @GetMapping(value = "/{qeSchoolId}")
    public AjaxResult queryQeSchoolById(@PathVariable Long qeSchoolId) {
        QeSchool qeSchool = qeSchoolService.queryQeSchoolById(qeSchoolId);
        return AjaxResult.success("查询成功", qeSchool);
    }

    @GetMapping(value = "/query")
    public AjaxResult commonQueryQeSchool(@RequestParam(defaultValue = "1") int pageNum,
                                          @RequestParam(defaultValue = "10") int pageSize,
                                          QeSchool qeSchool) {
        List<QeSchool> qeSchools = qeSchoolService.commonQueryQeSchool(pageNum, pageSize, qeSchool);
        return AjaxResult.success("查询成功", new PageInfo<>(qeSchools));
    }
}
