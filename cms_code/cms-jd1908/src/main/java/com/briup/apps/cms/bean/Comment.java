package com.briup.apps.cms.bean;
/**
 * 评论 实体类
 * @author Charlie
 * @Date 2019年11月13日
 */
public class Comment {
    private Long id;			//评论id
    private Long commentTime;	//评论时间
    private String status;		//评论状态
    private Long userId;		//评论者id，关联用户的id
    private Long articleId;		//文章id，表明这个评论是哪个文章下的
    private Long parentId;		//父评论id，关联本类的id
    private String content;		//评论内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Long commentTime) {
        this.commentTime = commentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}