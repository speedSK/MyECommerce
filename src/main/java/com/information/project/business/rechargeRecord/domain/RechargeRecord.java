package com.information.project.business.rechargeRecord.domain;

import com.information.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 银行转账表 bank_recharge_record
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class RechargeRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 交易码 */
	private String code;
	/** 银行交易码 */
	private String bankCode;
	/** 转账日期 */
	private String transDate;
	/** 转账流水号 */
	private String transIdserial;
	/** 用户编号 */
	private String userCode;
	/** 用户姓名 */
	private String userName;
	/** 身份证号 */
	private String diNumber;
	/** 银行卡号 */
	private String bankNumber;
	/** 金额 */
	private String amount;
	/** 状态 */
	private String status;
	/** 返回码 */
	private String returnCode;
	/** 返回信息 */
	private String returnMessage;
	/** 银行流水号 */
	private String bankIdserial;
	/** 创建时间 */
	private Date createTime;

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
	 * 设置：交易码
	 */
	public void setCode(String code) 
	{
		this.code = code;
	}
	
	/**
	 * 获取：交易码
	 */
	public String getCode() 
	{
		return code;
	}
	
	/**
	 * 设置：银行交易码
	 */
	public void setBankCode(String bankCode) 
	{
		this.bankCode = bankCode;
	}
	
	/**
	 * 获取：银行交易码
	 */
	public String getBankCode() 
	{
		return bankCode;
	}
	
	/**
	 * 设置：转账日期
	 */
	public void setTransDate(String transDate) 
	{
		this.transDate = transDate;
	}
	
	/**
	 * 获取：转账日期
	 */
	public String getTransDate() 
	{
		return transDate;
	}
	
	/**
	 * 设置：转账流水号
	 */
	public void setTransIdserial(String transIdserial) 
	{
		this.transIdserial = transIdserial;
	}
	
	/**
	 * 获取：转账流水号
	 */
	public String getTransIdserial() 
	{
		return transIdserial;
	}
	
	/**
	 * 设置：用户编号
	 */
	public void setUserCode(String userCode) 
	{
		this.userCode = userCode;
	}
	
	/**
	 * 获取：用户编号
	 */
	public String getUserCode() 
	{
		return userCode;
	}
	
	/**
	 * 设置：用户姓名
	 */
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	
	/**
	 * 获取：用户姓名
	 */
	public String getUserName() 
	{
		return userName;
	}
	
	/**
	 * 设置：身份证号
	 */
	public void setDiNumber(String diNumber) 
	{
		this.diNumber = diNumber;
	}
	
	/**
	 * 获取：身份证号
	 */
	public String getDiNumber() 
	{
		return diNumber;
	}
	
	/**
	 * 设置：银行卡号
	 */
	public void setBankNumber(String bankNumber) 
	{
		this.bankNumber = bankNumber;
	}
	
	/**
	 * 获取：银行卡号
	 */
	public String getBankNumber() 
	{
		return bankNumber;
	}
	
	/**
	 * 设置：金额
	 */
	public void setAmount(String amount) 
	{
		this.amount = amount;
	}
	
	/**
	 * 获取：金额
	 */
	public String getAmount() 
	{
		return amount;
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
	 * 设置：返回码
	 */
	public void setReturnCode(String returnCode) 
	{
		this.returnCode = returnCode;
	}
	
	/**
	 * 获取：返回码
	 */
	public String getReturnCode() 
	{
		return returnCode;
	}
	
	/**
	 * 设置：返回信息
	 */
	public void setReturnMessage(String returnMessage) 
	{
		this.returnMessage = returnMessage;
	}
	
	/**
	 * 获取：返回信息
	 */
	public String getReturnMessage() 
	{
		return returnMessage;
	}
	
	/**
	 * 设置：银行流水号
	 */
	public void setBankIdserial(String bankIdserial) 
	{
		this.bankIdserial = bankIdserial;
	}
	
	/**
	 * 获取：银行流水号
	 */
	public String getBankIdserial() 
	{
		return bankIdserial;
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
	
}
