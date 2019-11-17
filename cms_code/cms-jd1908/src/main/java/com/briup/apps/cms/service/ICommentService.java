package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Comment;

public interface ICommentService {

	//查询所有	
	public List<Comment> findAll();
	
	//根据author_id级联查看所有评论
	public List<Comment> findAllWithArticleId(Long articleid);
		
	//添加评论
	public void addComment(Comment comment);
	
	//级联删除评论
	public void cascadeDeleteById(Long id);
	
}
