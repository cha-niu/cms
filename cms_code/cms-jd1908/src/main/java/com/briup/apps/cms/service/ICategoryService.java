package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.utils.CustomerException;

public interface ICategoryService {
	
	List<Category> findAll();

    String saveOrUpdate(Category category) throws CustomerException;
    
    void deleteById(long id) throws CustomerException;
    
    void batchDelete(Long[] ids) throws CustomerException;
    
}
