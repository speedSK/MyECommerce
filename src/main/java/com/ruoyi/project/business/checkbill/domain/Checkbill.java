package com.ruoyi.project.business.checkbill.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 银行总账对账表 bank_checkbill
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class Checkbill extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	@Excel(name = "序号")
	private Long id;
	/** 系统交易码 */
    @Excel(name = "系统交易码")
	private String code;
	/** 银行交易码 */
    @Excel(name = "银行交易码")
	private String bankCode;
	/** 对账日期 */
    @Excel(name = "对账日期")
	private String checkDate;
	/** 充值总笔数 */
    @Excel(name = "充值笔数")
	private String rechargeNum;
	/** 充值总金额 */
    @Excel(name = "充值金额")
	private String rechargeSum;
	/** 冲正总笔数 */
    @Excel(name = "冲正笔数")
	private String correctionNum;
	/** 冲正总金额 */
    @Excel(name = "冲正金额")
	private String correctionSum;
	/** 销户总笔数 */
    @Excel(name = "销户笔数")
	private String closeNum;
	/** 销户总金额 */
    @Excel(name = "销户金额")
	private String closeSum;
	/** 商户总笔数 */
    @Excel(name = "结算笔数")
	private String merchantNum;
	/** 商户总金额 */
    @Excel(name = "结算金额")
	private String merchantSum;
	/** 所有正确圈存的卡号总和 */
    @Excel(name = "圈存卡号总和")
	private String rechargeCardSum;
	/** 所有冲正的卡号总和 */
    @Excel(name = "冲正卡号总和")
	private String correctionCardSum;
	/** 所有销户的卡号总和 */
    @Excel(name = "销户卡号总和")
	private String closeCardSum;
	/** 所有结算的卡号总和 */
    @Excel(name = "结算卡号总和")
	private String merchantCardSum;
	/** 返回码 */
    @Excel(name = "返回码")
	private String returnCode;
	/** 返回信息 */
    @Excel(name = "返回信息")
	private String returnMessage;
	/** 状态 */
    @Excel(name = "状态")
	private String status;
	/** 创建时间戳 */
    @Excel(name = "时间戳")
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
	 * 设置：系统交易码
	 */
	public void setCode(String code) 
	{
		this.code = code;
	}
	
	/**
	 * 获取：系统交易码
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
	 * 设置：对账日期
	 */
	public void setCheckDate(String checkDate) 
	{
		this.checkDate = checkDate;
	}
	
	/**
	 * 获取：对账日期
	 */
	public String getCheckDate() 
	{
		return checkDate;
	}
	
	/**
	 * 设置：充值总笔数
	 */
	public void setRechargeNum(String rechargeNum) 
	{
		this.rechargeNum = rechargeNum;
	}
	
	/**
	 * 获取：充值总笔数
	 */
	public String getRechargeNum() 
	{
		return rechargeNum;
	}
	
	/**
	 * 设置：充值总金额
	 */
	public void setRechargeSum(String rechargeSum) 
	{
		this.rechargeSum = rechargeSum;
	}
	
	/**
	 * 获取：充值总金额
	 */
	public String getRechargeSum() 
	{
		return rechargeSum;
	}
	
	/**
	 * 设置：冲正总笔数
	 */
	public void setCorrectionNum(String correctionNum) 
	{
		this.correctionNum = correctionNum;
	}
	
	/**
	 * 获取：冲正总笔数
	 */
	public String getCorrectionNum() 
	{
		return correctionNum;
	}
	
	/**
	 * 设置：冲正总金额
	 */
	public void setCorrectionSum(String correctionSum) 
	{
		this.correctionSum = correctionSum;
	}
	
	/**
	 * 获取：冲正总金额
	 */
	public String getCorrectionSum() 
	{
		return correctionSum;
	}
	
	/**
	 * 设置：销户总笔数
	 */
	public void setCloseNum(String closeNum) 
	{
		this.closeNum = closeNum;
	}
	
	/**
	 * 获取：销户总笔数
	 */
	public String getCloseNum() 
	{
		return closeNum;
	}
	
	/**
	 * 设置：销户总金额
	 */
	public void setCloseSum(String closeSum) 
	{
		this.closeSum = closeSum;
	}
	
	/**
	 * 获取：销户总金额
	 */
	public String getCloseSum() 
	{
		return closeSum;
	}
	
	/**
	 * 设置：商户总笔数
	 */
	public void setMerchantNum(String merchantNum) 
	{
		this.merchantNum = merchantNum;
	}
	
	/**
	 * 获取：商户总笔数
	 */
	public String getMerchantNum() 
	{
		return merchantNum;
	}
	
	/**
	 * 设置：商户总金额
	 */
	public void setMerchantSum(String merchantSum) 
	{
		this.merchantSum = merchantSum;
	}
	
	/**
	 * 获取：商户总金额
	 */
	public String getMerchantSum() 
	{
		return merchantSum;
	}
	
	/**
	 * 设置：所有正确圈存的卡号总和
	 */
	public void setRechargeCardSum(String rechargeCardSum) 
	{
		this.rechargeCardSum = rechargeCardSum;
	}
	
	/**
	 * 获取：所有正确圈存的卡号总和
	 */
	public String getRechargeCardSum() 
	{
		return rechargeCardSum;
	}
	
	/**
	 * 设置：所有冲正的卡号总和
	 */
	public void setCorrectionCardSum(String correctionCardSum) 
	{
		this.correctionCardSum = correctionCardSum;
	}
	
	/**
	 * 获取：所有冲正的卡号总和
	 */
	public String getCorrectionCardSum() 
	{
		return correctionCardSum;
	}
	
	/**
	 * 设置：所有销户的卡号总和
	 */
	public void setCloseCardSum(String closeCardSum) 
	{
		this.closeCardSum = closeCardSum;
	}
	
	/**
	 * 获取：所有销户的卡号总和
	 */
	public String getCloseCardSum() 
	{
		return closeCardSum;
	}
	
	/**
	 * 设置：所有结算的卡号总和
	 */
	public void setMerchantCardSum(String merchantCardSum) 
	{
		this.merchantCardSum = merchantCardSum;
	}
	
	/**
	 * 获取：所有结算的卡号总和
	 */
	public String getMerchantCardSum() 
	{
		return merchantCardSum;
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
	 * 设置：创建时间戳
	 */
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}
	
	/**
	 * 获取：创建时间戳
	 */
	public Date getCreateTime() 
	{
		return createTime;
	}
	
}
