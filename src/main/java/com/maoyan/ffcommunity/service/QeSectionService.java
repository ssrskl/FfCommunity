package com.maoyan.ffcommunity.service;

import com.maoyan.ffcommunity.entity.QeSection;
import com.maoyan.ffcommunity.entity.vo.qesection.QeSectionQueryVO;
import com.maoyan.ffcommunity.entity.vo.qesection.QeSectionVO;

import java.util.List;

public interface QeSectionService {
    QeSection queryQeSectionById(Long qeSectionId);//根据ID查询指定板块

    List<QeSectionQueryVO> commonQueryQeSectionQueryVOList(int pageNum, int pageSize, QeSectionVO qeSectionVO);
}
