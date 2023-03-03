package com.maoyan.ffcommunity.service;

import com.maoyan.ffcommunity.entity.QeSchool;

import java.util.List;

public interface QeSchoolService {
    QeSchool queryQeSchoolById(Long qeSchoolId);//通过id查询学校[开发完成]

    List<QeSchool> commonQueryQeSchool(int pageNum, int pageSize, QeSchool qeSchool);// 通用查询学校【开发完成】
}
