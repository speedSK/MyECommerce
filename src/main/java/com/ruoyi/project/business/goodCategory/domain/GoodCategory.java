package com.ruoyi.project.business.goodCategory.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 商品分类表 bus_good_category
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
public class GoodCategory extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 分类编码 */
	private String ancestors;
	/** 分类名称 */
	private String categoryName;
	/** 父级id */
	private Long parentId;
	/** 父级name */
	private String parentName;
	/** 显示顺序 */
	private Integer orderNum;
	/** 菜单图标 */
	private String icon;
	/** 菜单状态 */
	private String visible;
	/** 状态 */
	private String status;

	/**
	 * 设置：主键
	 */
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	/**
	 * 获取：主键
	 */
	public Long getId() 
	{
		return id;
	}

	public String getAncestors() {
		return ancestors;
	}

	public void setAncestors(String ancestors) {
		this.ancestors = ancestors;
	}

	/**
	 * 设置：分类名称
	 */
	public void setCategoryName(String categoryName) 
	{
		this.categoryName = categoryName;
	}
	
	/**
	 * 获取：分类名称
	 */
	public String getCategoryName() 
	{
		return categoryName;
	}
	
	/**
	 * 设置：父级id
	 */
	public void setParentId(Long parentId) 
	{
		this.parentId = parentId;
	}
	
	/**
	 * 获取：父级id
	 */
	public Long getParentId() 
	{
		return parentId;
	}
	
	/**
	 * 设置：显示顺序
	 */
	public void setOrderNum(Integer orderNum) 
	{
		this.orderNum = orderNum;
	}
	
	/**
	 * 获取：显示顺序
	 */
	public Integer getOrderNum() 
	{
		return orderNum;
	}
	
	/**
	 * 设置：菜单图标
	 */
	public void setIcon(String icon) 
	{
		this.icon = icon;
	}
	
	/**
	 * 获取：菜单图标
	 */
	public String getIcon() 
	{
		return icon;
	}
	
	/**
	 * 设置：菜单状态
	 */
	public void setVisible(String visible) 
	{
		this.visible = visible;
	}
	
	/**
	 * 获取：菜单状态
	 */
	public String getVisible() 
	{
		return visible;
	}
	
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	/**
	 * 获取：状态
	 */
	public String getStatus() 
	{
		return status;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
