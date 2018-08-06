package com.information.project.business.goods.domain;

import com.information.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表 bus_goods
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class Goods extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 价格 */
	private BigDecimal price;
	/** 图片 */
	private String image;
	/** 商户id */
	private Long merchantId;
	/** 描述 */
	private String des;
	/** 商品上架状态 */
	private String gooodsStatus;
	/** 状态 */
	private String status;
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
	 * 设置：商品上架状态
	 */
	public void setGooodsStatus(String gooodsStatus) 
	{
		this.gooodsStatus = gooodsStatus;
	}
	
	/**
	 * 获取：商品上架状态
	 */
	public String getGooodsStatus() 
	{
		return gooodsStatus;
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
