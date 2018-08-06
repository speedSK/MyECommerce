package com.information.project.business.accountReport.domain;

import java.math.BigDecimal;

import com.information.framework.web.domain.BaseEntity;

/**
 * 系统账户报表 bus_account_report
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class AccountReport extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 报表日期 */
	private String reportDate;
	/** 账户id */
	private Long accountId;
	/** 收入笔数 */
	private Integer incomeNum;
	/** 收入金额 */
	private BigDecimal incomeSum;
	/** 支出笔数 */
	private Integer outcomeNum;
	/** 支出金额 */
	private BigDecimal outcomeSum;

	/**
	 * 设置：
	 */
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	/**
	 * 获取：
	 */
	public Long getId() 
	{
		return id;
	}
	
	/**
	 * 设置：报表日期
	 */
	public void setReportDate(String reportDate) 
	{
		this.reportDate = reportDate;
	}
	
	/**
	 * 获取：报表日期
	 */
	public String getReportDate() 
	{
		return reportDate;
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
	 * 设置：收入笔数
	 */
	public void setIncomeNum(Integer incomeNum) 
	{
		this.incomeNum = incomeNum;
	}
	
	/**
	 * 获取：收入笔数
	 */
	public Integer getIncomeNum() 
	{
		return incomeNum;
	}
	
	/**
	 * 设置：收入金额
	 */
	public void setIncomeSum(BigDecimal incomeSum) 
	{
		this.incomeSum = incomeSum;
	}
	
	/**
	 * 获取：收入金额
	 */
	public BigDecimal getIncomeSum() 
	{
		return incomeSum;
	}
	
	/**
	 * 设置：支出笔数
	 */
	public void setOutcomeNum(Integer outcomeNum) 
	{
		this.outcomeNum = outcomeNum;
	}
	
	/**
	 * 获取：支出笔数
	 */
	public Integer getOutcomeNum() 
	{
		return outcomeNum;
	}
	
	/**
	 * 设置：支出金额
	 */
	public void setOutcomeSum(BigDecimal outcomeSum) 
	{
		this.outcomeSum = outcomeSum;
	}
	
	/**
	 * 获取：支出金额
	 */
	public BigDecimal getOutcomeSum() 
	{
		return outcomeSum;
	}
	
}
