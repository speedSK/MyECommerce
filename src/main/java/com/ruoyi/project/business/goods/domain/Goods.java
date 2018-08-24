package com.ruoyi.project.business.goods.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 商品表 bus_goods
 * 
 * @author LiuNing
 * @date 2018-08-08
 */
public class Goods extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 分类id */
	private Long categoryId;
	/** 分类name */
	private String categoryName;
	/** 商户id */
	private Long merchantId;
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 价格 */
	private BigDecimal price;
	/** 显示顺序 */
	private Integer orderNum;
	/** 图片 */
	private String image;
	/** 分类状态 */
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
	
	/**
	 * 设置：分类id
	 */
	public void setCategoryId(Long categoryId) 
	{
		this.categoryId = categoryId;
	}
	
	/**
	 * 获取：分类id
	 */
	public Long getCategoryId() 
	{
		return categoryId;
	}
	
	/**
	 * 设置：商户id
	 */
	public void setMerchantId(Long merchantId) 
	{
		this.merchantId = merchantId;
	}
	
	/**
	 * 获取：商户id
	 */
	public Long getMerchantId() 
	{
		return merchantId;
	}
	
	/**
	 * 设置：编码
	 */
	public void setCode(String code) 
	{
		this.code = code;
	}
	
	/**
	 * 获取：编码
	 */
	public String getCode() 
	{
		return code;
	}
	
	/**
	 * 设置：名称
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：名称
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) 
	{
		this.price = price;
	}
	
	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() 
	{
		return price;
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
	 * 设置：图片
	 */
	public void setImage(String image) 
	{
		this.image = image;
	}
	
	/**
	 * 获取：图片
	 */
	public String getImage() 
	{
		return image;
	}
	
	/**
	 * 设置：分类状态
	 */
	public void setVisible(String visible) 
	{
		this.visible = visible;
	}
	
	/**
	 * 获取：分类状态
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
