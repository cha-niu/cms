package com.briup.apps.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.extend.ArticleExtendMapper;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.CustomerException;

/**
 * @program: cms_jd1908
 * @description: 文章的业务实现类
 * @author: charles
 * @create: 2019-11-12 14:59
 **/
@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleExtendMapper articleExtendMapper;

    @Override
    public List<Article> findAll() {
        return articleMapper.selectAll();
    }

    @Override
    public List<ArticleExtend> cascadeFindAll() {
        return articleExtendMapper.selectAll();
    }

    @Override
    public ArticleExtend cascadeFindById(long id) {
        return articleExtendMapper.cascadeSelectById(id);
    }
    
    @Override
    public String saveOrUpdate(Article article) throws CustomerException{
    	System.out.println("ArticleServiceImpl" + article.getId());
    	Article selectResult = articleMapper.selectByPrimaryKey(article.getId());
    	if(selectResult != null) {
    		articleMapper.updateByPrimaryKey(article);   
    		return "更新成功";
    	} else {
    		/*
    		 * 文章标题不能重复
    		 */
    		List<Article> selectTitleResults = articleMapper.selectByTitle(article.getTitle());
    		
    		if(selectTitleResults.size() > 0) {
    			System.out.println("-------------------------");
    			throw new CustomerException("文章标题不能重复！");
    		}
    		//初始化
    		article.setStatus(ArticleExtend.STATUS_UNCHEAKED);
    		article.setThumpUp(0l);
    		article.setThumpDown(0l);
    		article.setPublishTime(new Date().getTime());
    		article.setReadTimes(0l);
			articleMapper.insert(article);
			return "发布成功";
    	}
    }

	@Override
	public void deleteById(long id) throws CustomerException {
		Article result = articleMapper.selectByPrimaryKey(id);
		if(result == null) {
			throw new  CustomerException("文章不存在！");
		}
		articleMapper.deleteByPrimaryKey(id);
	}
    
}
