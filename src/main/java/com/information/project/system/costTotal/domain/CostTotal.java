package com.information.project.system.costTotal.domain;

import com.information.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 消费限额表 bus_cost_total
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class CostTotal extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 身份id */
	private Long pId;
	/** 消费上限 */
	private BigDecimal costTotal;
	/** 操作人 */
	private Long opserUser;
	/** 状态 */
	private String status;
	/** 创建时间 */
	private Date createTime;
	/** 时间戳 */
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
	 * 设置：身份id
	 */
	public void setPId(Long pId) 
	{
		this.pId = pId;
	}
	
	/**
	 * 获取：身份id
	 */
	public Long getPId() 
	{
		return pId;
	}
	
	/**
	 * 设置：消费上限
	 */
	public void setCostTotal(BigDecimal costTotal) 
	{
		this.costTotal = costTotal;
	}
	
	/**
	 * 获取：消费上限
	 */
	public BigDecimal getCostTotal() 
	{
		return costTotal;
	}
	
	/**
	 * 设置：操作人
	 */
	public void setOpserUser(Long opserUser) 
	{
		this.opserUser = opserUser;
	}
	
	/**
	 * 获取：操作人
	 */
	public Long getOpserUser() 
	{
		return opserUser;
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
	 * 设置：时间戳
	 */
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}
	
	/**
	 * 获取：时间戳
	 */
	public Date getUpdateTime() 
	{
		return updateTime;
	}
	
}
