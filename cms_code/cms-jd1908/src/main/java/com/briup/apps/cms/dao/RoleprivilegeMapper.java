package com.briup.apps.cms.dao;

import com.briup.apps.cms.bean.Roleprivilege;

public interface RoleprivilegeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Roleprivilege record);

    int insertSelective(Roleprivilege record);

    Roleprivilege selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Roleprivilege record);

    int updateByPrimaryKey(Roleprivilege record);
}