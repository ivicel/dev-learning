package info.ivicel.tumoblog.admin.mapper;

import info.ivicel.tumoblog.admin.entity.Link;

public interface LinkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Link record);

    int insertSelective(Link record);

    Link selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Link record);

    int updateByPrimaryKey(Link record);

    Long count();
}