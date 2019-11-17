package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "栏目相关操作")
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	//查询所有栏目（不包括文章的栏目、评论）
    @ApiOperation(value = "查询所有栏目")
    @GetMapping("findAll")
    public Message findAll(){
    	List<Category> list = categoryService.findAll();
    	return MessageUtil.success(list);
    }


    //添加和修改栏目
    @ApiOperation(value = "添加和修改栏目")
    @PostMapping("saveOrUpdate")
    public Message saveOrUpdate(Category category){ 
  
    	System.out.println("CategoryController" + category.getId());
    	String msg = categoryService.saveOrUpdate(category);
    	return MessageUtil.success(msg);
    }
    
    
    //删除文章
    @ApiOperation(value = "通过id来删除栏目")
    @GetMapping("deleteById")
    public Message deleteById(@RequestParam(value = "id", required = false) Long id) {
    	categoryService.deleteById(id);
    	return MessageUtil.success("删除成功");
    }
    
    //批量删除
    @ApiOperation(value = "批量删除栏目")
    @PostMapping("batchDelete")
    public Message batchDelete(@RequestBody Long[] ids) {
    	System.out.println(ids);
    	categoryService.batchDelete(ids);
    	return MessageUtil.success("删除成功");
    }
	
}
