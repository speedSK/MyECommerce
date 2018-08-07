package com.information.project.system.merchant.domain;

import com.information.framework.web.domain.BaseEntity;

import java.math.BigDecimal;


/**
 * 商户表 bus_merchant
 * 
 * @author LiuNing
 * @date 2018-08-07
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
	/** 联系方式 */
	private String mobile;
	/** 地址 */
	private String address;
	/** 描述 */
	private String des;
	/** 账户编号 */
	private String accountCode;
	/** 账号名称 */
	private String accountName;
	/** 账户余额 */
	private BigDecimal balance;
	/** 账户标识 */
	private String flag;
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
	 * 设置：账户编号
	 */
	public void setAccountCode(String accountCode) 
	{
		this.accountCode = accountCode;
	}
	
	/**
	 * 获取：账户编号
	 */
	public String getAccountCode() 
	{
		return accountCode;
	}
	
	/**
	 * 设置：账号名称
	 */
	public void setAccountName(String accountName) 
	{
		this.accountName = accountName;
	}
	
	/**
	 * 获取：账号名称
	 */
	public String getAccountName() 
	{
		return accountName;
	}
	
	/**
	 * 设置：账户余额
	 */
	public void setBalance(BigDecimal balance) 
	{
		this.balance = balance;
	}
	
	/**
	 * 获取：账户余额
	 */
	public BigDecimal getBalance() 
	{
		return balance;
	}
	
	/**
	 * 设置：账户标识
	 */
	public void setFlag(String flag) 
	{
		this.flag = flag;
	}
	
	/**
	 * 获取：账户标识
	 */
	public String getFlag() 
	{
		return flag;
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
	

}
