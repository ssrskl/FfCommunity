package com.maoyan.ffcommunity.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.maoyan.ffcommunity.entity.QeSection;
import com.maoyan.ffcommunity.entity.vo.qesection.QeSectionQueryVO;
import com.maoyan.ffcommunity.entity.vo.qesection.QeSectionVO;
import com.maoyan.ffcommunity.exception.CustomException;
import com.maoyan.ffcommunity.mapper.QeSectionMapper;
import com.maoyan.ffcommunity.service.QeSectionService;
import com.maoyan.ffcommunity.utils.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QeSectionServiceImpl implements QeSectionService {

    @Autowired
    private QeSectionMapper qeSectionMapper;

    @Override
    public QeSection queryQeSectionById(Long qeSectionId) {
        QeSection qeSection = qeSectionMapper.selectQeSectionById(qeSectionId);
        if (ObjectUtil.isNull(qeSection)) {
            throw new CustomException("此板块不存在", HttpStatus.NOT_FOUND);
        }
        return qeSection;
    }

    @Override
    public List<QeSectionQueryVO> commonQueryQeSectionQueryVOList(int pageNum, int pageSize, QeSectionVO qeSectionVO) {
        PageHelper.startPage(pageNum, pageSize);
        List<QeSectionQueryVO> qeSectionQueryVOS = qeSectionMapper.selectQeSectionQuery(qeSectionVO);
        return qeSectionQueryVOS;
    }
}
