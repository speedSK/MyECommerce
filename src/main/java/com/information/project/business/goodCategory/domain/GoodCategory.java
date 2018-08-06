package com.information.project.business.goodCategory.domain;

import com.information.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 商品分类表 bus_good_category
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class GoodCategory extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 分类编码 */
	private String categoryCode;
	/** 分类名称 */
	private String categoryName;
	/** 父级id */
	private Long parentId;
	/** 状态 */
	private String status;
	/** 描述 */
	private String des;
	/** 操作人 */
	private Long operUser;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;

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
	
	/**
	 * 设置：分类编码
	 */
	public void setCategoryCode(String categoryCode) 
	{
		this.categoryCode = categoryCode;
	}
	
	/**
	 * 获取：分类编码
	 */
	public String getCategoryCode() 
	{
		return categoryCode;
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
	
	/**
	 * 设置：描述
	 */
	public void setDes(String des) 
	{
		this.des = des;
	}
	
	/**
	 * 获取：描述
	 */
	public String getDes() 
	{
		return des;
	}
	
	/**
	 * 设置：操作人
	 */
	public void setOperUser(Long operUser) 
	{
		this.operUser = operUser;
	}
	
	/**
	 * 获取：操作人
	 */
	public Long getOperUser() 
	{
		return operUser;
	}
	
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}
	
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() 
	{
		return createTime;
	}
	
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}
	
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() 
	{
		return updateTime;
	}
	
}
