package com.information.project.business.tradeRecord.domain;

import com.information.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 流水表 bus_trade_record
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class TradeRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 流水号 */
	private String idserial;
	/** 交易代码 */
	private String tradeCode;
	/** 关联id（订单号，或流水号） */
	private String relationId;
	/** 交易用户 */
	private Long userId;
	/** 交易金额 */
	private BigDecimal amount;
	/** 来源账户 */
	private String fromAcc;
	/** 目标账户 */
	private String toAcc;
	/** 备注 */
	private String remark;
	/** 状态 */
	private String status;
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
	 * 设置：流水号
	 */
	public void setIdserial(String idserial) 
	{
		this.idserial = idserial;
	}
	
	/**
	 * 获取：流水号
	 */
	public String getIdserial() 
	{
		return idserial;
	}
	
	/**
	 * 设置：交易代码
	 */
	public void setTradeCode(String tradeCode) 
	{
		this.tradeCode = tradeCode;
	}
	
	/**
	 * 获取：交易代码
	 */
	public String getTradeCode() 
	{
		return tradeCode;
	}
	
	/**
	 * 设置：关联id（订单号，或流水号）
	 */
	public void setRelationId(String relationId) 
	{
		this.relationId = relationId;
	}
	
	/**
	 * 获取：关联id（订单号，或流水号）
	 */
	public String getRelationId() 
	{
		return relationId;
	}
	
	/**
	 * 设置：交易用户
	 */
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}
	
	/**
	 * 获取：交易用户
	 */
	public Long getUserId() 
	{
		return userId;
	}
	
	/**
	 * 设置：交易金额
	 */
	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}
	
	/**
	 * 获取：交易金额
	 */
	public BigDecimal getAmount() 
	{
		return amount;
	}
	
	/**
	 * 设置：来源账户
	 */
	public void setFromAcc(String fromAcc) 
	{
		this.fromAcc = fromAcc;
	}
	
	/**
	 * 获取：来源账户
	 */
	public String getFromAcc() 
	{
		return fromAcc;
	}
	
	/**
	 * 设置：目标账户
	 */
	public void setToAcc(String toAcc) 
	{
		this.toAcc = toAcc;
	}
	
	/**
	 * 获取：目标账户
	 */
	public String getToAcc() 
	{
		return toAcc;
	}
	
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
	
	/**
	 * 获取：备注
	 */
	public String getRemark() 
	{
		return remark;
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
