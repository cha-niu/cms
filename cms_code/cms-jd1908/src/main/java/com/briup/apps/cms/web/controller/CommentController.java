package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.service.ICommentService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	ICommentService commentService;
	
	//查看所有评论
	@GetMapping("findAll")
	public Message findAll() {
		List<Comment> data = commentService.findAll();
		return MessageUtil.success(data);
	}
	
	//根据author_id级联查看所有评论
	@GetMapping("findAllWithArticleId")
	public Message findAllWithArticleId(Long articleid){
		List<Comment> data = commentService.findAllWithArticleId(articleid);
		return MessageUtil.success(data);
	}
	
	//添加评论
	@PostMapping("addComment")
	public Message addComment(Comment comment) {
		commentService.addComment(comment);
		return MessageUtil.success("评论成功");
	}
	
	//级联删除评论
	@PostMapping("cascadeDeleteById")
	public Message cascadeDeleteById(Long id) {
		commentService.cascadeDeleteById(id);
		return MessageUtil.success("删除成功");
	}
	
}
