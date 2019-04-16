package info.ivicel.tumoblog.admin.mapper;

import info.ivicel.tumoblog.admin.entity.Comment;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    Long count();

    List<Comment> findAllDesc(int offset, int num);
}