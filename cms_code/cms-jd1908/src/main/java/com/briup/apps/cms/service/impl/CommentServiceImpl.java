package com.briup.apps.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.dao.CommentMapper;
import com.briup.apps.cms.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Resource
	CommentMapper commentMapper;
	
	@Override
	public List<Comment> findAll() {
		return null;
	}

	@Override
	public List<Comment> findAllWithArticleId(Long articleid) {
		return null;
	}

	@Override
	public void addComment(Comment comment) {
		commentMapper.insert(comment);
	}

	@Override
	public void cascadeDeleteById(Long id) {
		commentMapper.deleteByPrimaryKey(id);
	}


}
