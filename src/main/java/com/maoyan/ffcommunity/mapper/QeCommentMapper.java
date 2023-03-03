package com.maoyan.ffcommunity.mapper;

import com.maoyan.ffcommunity.entity.QeComment;
import com.maoyan.ffcommunity.entity.vo.qecomment.QeCommentDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QeCommentMapper {
    int insertQeComment(QeComment qeComment);//添加评论

    int deleteQeComment(Long qeCommentId, Long authorId);//删除评论

    List<QeCommentDetailVO> commonSelectQeCommentDetailList(QeComment qeComment);//通用查询评论列表
}
