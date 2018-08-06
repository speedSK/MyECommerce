package com.information.project.business.orderDetail.domain;

import com.information.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 订单详情表 bus_order_detail
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class OrderDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 商品id */
	private String goodsId;
	/** 订单id */
	private Long orderId;
	/** 数量 */
	private Integer num;
	/** 金额 */
	private BigDecimal sum;
	/** 详情状态 */
	private String detailStatus;

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
	 * 设置：商品id
	 */
	public void setGoodsId(String goodsId) 
	{
		this.goodsId = goodsId;
	}
	
	/**
	 * 获取：商品id
	 */
	public String getGoodsId() 
	{
		return goodsId;
	}
	
	/**
	 * 设置：订单id
	 */
	public void setOrderId(Long orderId) 
	{
		this.orderId = orderId;
	}
	
	/**
	 * 获取：订单id
	 */
	public Long getOrderId() 
	{
		return orderId;
	}
	
	/**
	 * 设置：数量
	 */
	public void setNum(Integer num) 
	{
		this.num = num;
	}
	
	/**
	 * 获取：数量
	 */
	public Integer getNum() 
	{
		return num;
	}
	
	/**
	 * 设置：金额
	 */
	public void setSum(BigDecimal sum) 
	{
		this.sum = sum;
	}
	
	/**
	 * 获取：金额
	 */
	public BigDecimal getSum() 
	{
		return sum;
	}
	
	/**
	 * 设置：详情状态
	 */
	public void setDetailStatus(String detailStatus) 
	{
		this.detailStatus = detailStatus;
	}
	
	/**
	 * 获取：详情状态
	 */
	public String getDetailStatus() 
	{
		return detailStatus;
	}
	
}
