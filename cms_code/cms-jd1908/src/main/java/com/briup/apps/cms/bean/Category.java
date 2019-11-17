package com.briup.apps.cms.bean;
/**
 * 栏目实体类
 * @author Charlie
 * @Date 2019年11月13日
 */
public class Category {
    private Long id;				//栏目id
    private String name;			//栏目名
    private String description;		//栏目描述
    private Integer no;				//栏目序号
    private Long parentId;			//父栏目id，关联Category实体类的id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}