package com.maoyan.ffcommunity.mapper;

import com.maoyan.ffcommunity.entity.QeSchool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QeSchoolMapper {
    QeSchool selectQeSchoolById(Long qeSchoolId);//根据ID查询学校[开发完成]

    List<QeSchool> commonSelectQeSchool(QeSchool qeSchool);// 通用查询学校[开发完成]
}
