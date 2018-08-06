package com.information.project.business.order.domain;

import com.information.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表 bus_order
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class Order extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 订单号 */
	private String orderCode;
	/** 订单总金额 */
	private BigDecimal moneySum;
	/** 购买人 */
	private Long buyUser;
	/** 订单状态 */
	private String orderStatus;
	/** 状态 */
	private String status;
	/** 操作人 */
	private Long operUser;
	/** 完成时间 */
	private Date finishTime;
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
	 * 设置：订单号
	 */
	public void setOrderCode(String orderCode) 
	{
		this.orderCode = orderCode;
	}
	
	/**
	 * 获取：订单号
	 */
	public String getOrderCode() 
	{
		return orderCode;
	}
	
	/**
	 * 设置：订单总金额
	 */
	public void setMoneySum(BigDecimal moneySum) 
	{
		this.moneySum = moneySum;
	}
	
	/**
	 * 获取：订单总金额
	 */
	public BigDecimal getMoneySum() 
	{
		return moneySum;
	}
	
	/**
	 * 设置：购买人
	 */
	public void setBuyUser(Long buyUser) 
	{
		this.buyUser = buyUser;
	}
	
	/**
	 * 获取：购买人
	 */
	public Long getBuyUser() 
	{
		return buyUser;
	}
	
	/**
	 * 设置：订单状态
	 */
	public void setOrderStatus(String orderStatus) 
	{
		this.orderStatus = orderStatus;
	}
	
	/**
	 * 获取：订单状态
	 */
	public String getOrderStatus() 
	{
		return orderStatus;
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
	 * 设置：完成时间
	 */
	public void setFinishTime(Date finishTime) 
	{
		this.finishTime = finishTime;
	}
	
	/**
	 * 获取：完成时间
	 */
	public Date getFinishTime() 
	{
		return finishTime;
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
