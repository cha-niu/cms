package com.briup.apps.cms.dao;

import java.util.List;

import com.briup.apps.cms.bean.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

	List<Article> selectAll();

	List<Article> selectByTitle(String title);
}