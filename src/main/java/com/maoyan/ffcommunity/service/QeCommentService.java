package com.maoyan.ffcommunity.service;

import com.maoyan.ffcommunity.entity.QeComment;
import com.maoyan.ffcommunity.entity.vo.qecomment.QeCommentDetailVO;

import java.util.List;

public interface QeCommentService {
    int publishQeComment(QeComment qeComment);//发布一条评论

    List<QeCommentDetailVO> commonQueryQeCommentDetailList(int pageNum, int pageSize, QeComment qeComment);//通用查询评论列表
}
