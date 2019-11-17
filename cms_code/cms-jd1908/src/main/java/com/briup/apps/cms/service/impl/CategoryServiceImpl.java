package com.briup.apps.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.CustomerException;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Resource
	private CategoryMapper categoryMapper;
	
	@Override
	public List<Category> findAll() {
		return categoryMapper.selectAll();
	}

	@Override
	public String saveOrUpdate(Category category) throws CustomerException {
		System.out.println("CategoryServiceImpl" + category.getId());
		Category selectResult = categoryMapper.selectByPrimaryKey(category.getId());
    	if(selectResult != null) {
    		categoryMapper.updateByPrimaryKey(category);   
    		return "栏目修改成功";
    	} else {
  
    		categoryMapper.insert(category);
			return "栏目添加成功";
    	}
	}

	@Override
	public void deleteById(long id) throws CustomerException {
		Category result = categoryMapper.selectByPrimaryKey(id);
		if(result == null) {
			throw new  CustomerException("该栏目不存在！");
		}
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(Long[] ids) throws CustomerException {
		for (int i = 0; i < ids.length; i++) {
			deleteById(ids[i]);
		}
	}
	
	
	
	

}
