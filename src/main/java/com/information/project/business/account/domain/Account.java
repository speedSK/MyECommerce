package com.information.project.business.account.domain;

import java.math.BigDecimal;

import com.information.framework.web.domain.BaseEntity;

/**
 * 系统账户表 bus_account
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class Account extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 账户号 */
	private String accountCode;
	/** 账户名称 */
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
	 * 设置：账户号
	 */
	public void setAccountCode(String accountCode) 
	{
		this.accountCode = accountCode;
	}
	
	/**
	 * 获取：账户号
	 */
	public String getAccountCode() 
	{
		return accountCode;
	}
	
	/**
	 * 设置：账户名称
	 */
	public void setAccountName(String accountName) 
	{
		this.accountName = accountName;
	}
	
	/**
	 * 获取：账户名称
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
