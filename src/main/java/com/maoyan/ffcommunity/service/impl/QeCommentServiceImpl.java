package com.maoyan.ffcommunity.service.impl;

import com.github.pagehelper.PageHelper;
import com.maoyan.ffcommunity.entity.QeComment;
import com.maoyan.ffcommunity.entity.vo.qecomment.QeCommentDetailVO;
import com.maoyan.ffcommunity.exception.CustomException;
import com.maoyan.ffcommunity.mapper.QeCommentMapper;
import com.maoyan.ffcommunity.service.QeCommentService;
import com.maoyan.ffcommunity.utils.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QeCommentServiceImpl implements QeCommentService {

    @Autowired
    private QeCommentMapper qeCommentMapper;

    /**
     * @param qeComment
     * @return
     */
    @Override
    public int publishQeComment(QeComment qeComment) {
        int i = qeCommentMapper.insertQeComment(qeComment);
        if (i <= 0) {
            throw new CustomException("发布失败", HttpStatus.ERROR);
        }
        return i;
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param qeComment
     * @return
     */
    @Override
    public List<QeCommentDetailVO> commonQueryQeCommentDetailList(int pageNum, int pageSize, QeComment qeComment) {
        PageHelper.startPage(pageNum, pageSize);
        List<QeCommentDetailVO> qeCommentDetailVOS = qeCommentMapper.commonSelectQeCommentDetailList(qeComment);
        if (qeCommentDetailVOS != null && qeCommentDetailVOS.size() > 0) {
            return qeCommentDetailVOS;
        } else {
            throw new CustomException("查询失败", HttpStatus.ERROR);
        }
    }
}
