package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @program: cms_jd1908
 * @description: 文章控制器类
 * @author: charlie
 * @create: 2019-11-12 15:00
 **/
@Api(tags = "文章相关内容")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    //查询所有文章（不包括文章的栏目、评论）
    @ApiOperation(value = "查询所有文章（不包括文章的栏目、评论）")
    @GetMapping("findAll")
    public Message findAll(){
    	List<Article> list = articleService.findAll();
    	return MessageUtil.success(list);
    }

    //查询所有文章（包括文章的栏目，不包括评论）
    @ApiOperation(value = "查询所有文章（包括文章的栏目，不包括评论）")
    @GetMapping("cascadeFindAll")
    public Message cascadeFindAll(){
    	List<ArticleExtend> resultDate = articleService.cascadeFindAll();
        return MessageUtil.success(resultDate);
    }

    //根据id查询某一篇文章（包括文章的栏目以及评论内容）
    @ApiOperation(value = "根据id查询某一篇文章（包括文章的栏目以及评论内容）")
    @GetMapping("cascadeFindById")
    public Message cascadeFindById(long id){
        ArticleExtend result = articleService.cascadeFindById(id);
        return MessageUtil.success(result);
    }

    //发布和修改文章
    @ApiOperation(value = "发布和修改文章")
    @PostMapping("saveOrUpdate")
    public Message saveOrUpdate(
    		@ApiParam(value = "编号") @RequestParam(value = "id", required = false) Long id, 
    		@ApiParam(value = "标题", required = true) @RequestParam(value="title") String title, 
    		@ApiParam(value = "文章内容", required = true) @RequestParam(value="content") String content, 
    		@ApiParam(value = "富文本内容", required = true) @RequestParam(value="source", required = false) String source, 
    		@ApiParam(value = "作者id", required = true) @RequestParam(value="authorId", required = false) Long authorId,
    		@ApiParam(value = "栏目id", required = true) @RequestParam(value="categoryId") Long categoryId ){ 
    	Article article = new Article();
    	article.setId(id);
    	article.setTitle(title);
    	article.setContent(content);
    	article.setSource(source);
    	article.setAuthorId(authorId);
    	article.setCategoryId(categoryId);
    	
    	System.out.println("ArticleController" + article.getId());
    	String msg = articleService.saveOrUpdate(article);
    	return MessageUtil.success(msg);
    }
    
    
    
    //删除文章
    @ApiOperation(value = "通过id来删除文章")
    @ApiImplicitParams(
	@ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query")
    		)
    @GetMapping("deleteById")
    public Message deleteById(@RequestParam(value = "id", required = false) Long id) {
    	articleService.deleteById(id);
    	return MessageUtil.success("删除成功");
    }
    
    //预览文章
    
    //审核文章
    
    
    
}
