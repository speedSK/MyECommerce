package com.ruoyi.project.business.checkdetail.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 银行详情对账对账表 bank_checkdetail
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class Checkdetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	@Excel(name = "序号")
	private Long id;
	/** 编号 */
    @Excel(name = "人员编号")
	private String number;
	/** 系统流水号 */
    @Excel(name = "系统流水号")
	private String idserial;
	/** 银行流水号 */
    @Excel(name = "银行流水号")
	private String bankIdserial;
	/** 转账日期 */
    @Excel(name = "转账日期")
	private String transDate;
	/** 金额 */
    @Excel(name = "转账金额")
	private String amount;
	/** 用户类型 */
    @Excel(name = "用户类型")
	private String userType;
	/** 对账状态 */
    @Excel(name = "对账状态")
	private String checkStatus;
	/** 操作人 */
	private Long operUser;
	/** 时间戳 */
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
	 * 设置：编号
	 */
	public void setNumber(String number) 
	{
		this.number = number;
	}
	
	/**
	 * 获取：编号
	 */
	public String getNumber() 
	{
		return number;
	}
	
	/**
	 * 设置：系统流水号
	 */
	public void setIdserial(String idserial) 
	{
		this.idserial = idserial;
	}
	
	/**
	 * 获取：系统流水号
	 */
	public String getIdserial() 
	{
		return idserial;
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
	 * 设置：用户类型
	 */
	public void setUserType(String userType) 
	{
		this.userType = userType;
	}
	
	/**
	 * 获取：用户类型
	 */
	public String getUserType() 
	{
		return userType;
	}
	
	/**
	 * 设置：对账状态
	 */
	public void setCheckStatus(String checkStatus) 
	{
		this.checkStatus = checkStatus;
	}
	
	/**
	 * 获取：对账状态
	 */
	public String getCheckStatus() 
	{
		return checkStatus;
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
	 * 设置：时间戳
	 */
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}
	
	/**
	 * 获取：时间戳
	 */
	public Date getCreateTime() 
	{
		return createTime;
	}
	
}
