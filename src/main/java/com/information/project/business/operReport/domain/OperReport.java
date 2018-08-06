package com.information.project.business.operReport.domain;

import java.math.BigDecimal;

import com.information.framework.web.domain.BaseEntity;

/**
 * 系统操作报表 bus_oper_report
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class OperReport extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 报表日期 */
	private String reportDate;
	/** 交易码 */
	private String tradeCode;
	/** 交易笔数 */
	private Integer tradeNum;
	/** 交易金额 */
	private BigDecimal tradeSum;

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
	 * 设置：交易码
	 */
	public void setTradeCode(String tradeCode) 
	{
		this.tradeCode = tradeCode;
	}
	
	/**
	 * 获取：交易码
	 */
	public String getTradeCode() 
	{
		return tradeCode;
	}
	
	/**
	 * 设置：交易笔数
	 */
	public void setTradeNum(Integer tradeNum) 
	{
		this.tradeNum = tradeNum;
	}
	
	/**
	 * 获取：交易笔数
	 */
	public Integer getTradeNum() 
	{
		return tradeNum;
	}
	
	/**
	 * 设置：交易金额
	 */
	public void setTradeSum(BigDecimal tradeSum) 
	{
		this.tradeSum = tradeSum;
	}
	
	/**
	 * 获取：交易金额
	 */
	public BigDecimal getTradeSum() 
	{
		return tradeSum;
	}
	
}
