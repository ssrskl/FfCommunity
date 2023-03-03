package com.maoyan.ffcommunity.mapper;

import com.maoyan.ffcommunity.entity.QeSection;
import com.maoyan.ffcommunity.entity.vo.qesection.QeSectionQueryVO;
import com.maoyan.ffcommunity.entity.vo.qesection.QeSectionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QeSectionMapper {
    List<QeSectionQueryVO> selectQeSectionQuery(QeSectionVO qeSectionVO);

    QeSection selectQeSectionById(Long qeSectionId);
}
