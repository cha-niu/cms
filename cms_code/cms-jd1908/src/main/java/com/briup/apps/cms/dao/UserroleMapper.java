package com.briup.apps.cms.dao;

import com.briup.apps.cms.bean.Userrole;

public interface UserroleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Userrole record);

    int insertSelective(Userrole record);

    Userrole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Userrole record);

    int updateByPrimaryKey(Userrole record);
}