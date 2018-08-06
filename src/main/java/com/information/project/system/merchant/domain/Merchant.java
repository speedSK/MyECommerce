package com.information.project.system.merchant.domain;

import com.information.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 商户表 bus_merchant
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class Merchant extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 商户号 */
	private String merchantCode;
	/** 商户名称 */
	private String merchantName;
	/** 账户id */
	private Long accountId;
	/** 描述 */
	private String des;
	/** 联系方式 */
	private String mobile;
	/** 地址 */
	private String address;
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
	 * 设置：商户号
	 */
	public void setMerchantCode(String merchantCode) 
	{
		this.merchantCode = merchantCode;
	}
	
	/**
	 * 获取：商户号
	 */
	public String getMerchantCode() 
	{
		return merchantCode;
	}
	
	/**
	 * 设置：商户名称
	 */
	public void setMerchantName(String merchantName) 
	{
		this.merchantName = merchantName;
	}
	
	/**
	 * 获取：商户名称
	 */
	public String getMerchantName() 
	{
		return merchantName;
	}
	
	/**
	 * 设置：账户id
	 */
	public void setAccountId(Long accountId) 
	{
		this.accountId = accountId;
	}
	
	/**
	 * 获取：账户id
	 */
	public Long getAccountId() 
	{
		return accountId;
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
	 * 设置：联系方式
	 */
	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}
	
	/**
	 * 获取：联系方式
	 */
	public String getMobile() 
	{
		return mobile;
	}
	
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	/**
	 * 获取：地址
	 */
	public String getAddress() 
	{
		return address;
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
