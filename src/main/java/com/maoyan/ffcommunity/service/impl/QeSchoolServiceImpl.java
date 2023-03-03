package com.maoyan.ffcommunity.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.maoyan.ffcommunity.entity.QeSchool;
import com.maoyan.ffcommunity.exception.CustomException;
import com.maoyan.ffcommunity.mapper.QeSchoolMapper;
import com.maoyan.ffcommunity.service.QeSchoolService;
import com.maoyan.ffcommunity.utils.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QeSchoolServiceImpl implements QeSchoolService {

    @Autowired
    private QeSchoolMapper qeSchoolMapper;

    @Override
    public QeSchool queryQeSchoolById(Long qeSchoolId) {
        QeSchool qeSchool = qeSchoolMapper.selectQeSchoolById(qeSchoolId);
        if (ObjectUtil.isNull(qeSchool)) {
            throw new CustomException("此学校不存在", HttpStatus.NOT_FOUND);
        }
        return qeSchool;
    }

    @Override
    public List<QeSchool> commonQueryQeSchool(int pageNum, int pageSize, QeSchool qeSchool) {
        PageHelper.startPage(pageNum, pageSize);
        List<QeSchool> qeSchools = qeSchoolMapper.commonSelectQeSchool(qeSchool);
        return qeSchools;
    }
}
