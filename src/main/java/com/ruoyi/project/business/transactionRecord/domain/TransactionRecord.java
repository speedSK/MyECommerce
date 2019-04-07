package com.ruoyi.project.business.transactionRecord.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 银行转账表 bank_transaction_record
 * 
 * @author ruoyi
 * @date 2018-12-10
 */
public class TransactionRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 交易码 */
	private String code;
	/** 银行交易码 */
	@Excel(name = "银行交易码")
	private String bankCode;
	/** 转账日期 */
	@Excel(name = "转账日期")
	private String transDate;
	/** 转账流水号 */
	private String transIdserial;
	/** 用户编号 */
	@Excel(name = "用户编号")
	private String userCode;
	/** 用户姓名 */
	@Excel(name = "用户姓名")
	private String userName;
	/** 身份证号 */
	private String idNumber;
	/** 银行卡号 */
	@Excel(name = "银行卡号")
	private String bankNumber;
	/** 金额 */
	@Excel(name = "转账金额")
	private String amount;
	/** 状态 */
	private String status;
	/** 返回码 */
	private String returnCode;
	/** 返回信息 */
	private String returnMessage;
	/** 银行流水号 */
	@Excel(name = "银行流水号")
	private String bankIdserial;
	/** 创建人 */
	private String createBy;
	/** 创建时间 */
	@Excel(name = "创建时间",dateFormat = "yyyy-MM-dd")
	private Date createTime;
	/** 修改人 */
	private String updateBy;
	/** 修改时间 */
	private Date updateTime;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setBankCode(String bankCode) 
	{
		this.bankCode = bankCode;
	}

	public String getBankCode() 
	{
		return bankCode;
	}
	public void setTransDate(String transDate) 
	{
		this.transDate = transDate;
	}

	public String getTransDate() 
	{
		return transDate;
	}
	public void setTransIdserial(String transIdserial) 
	{
		this.transIdserial = transIdserial;
	}

	public String getTransIdserial() 
	{
		return transIdserial;
	}
	public void setUserCode(String userCode) 
	{
		this.userCode = userCode;
	}

	public String getUserCode() 
	{
		return userCode;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getUserName() 
	{
		return userName;
	}
	public void setIdNumber(String idNumber) 
	{
		this.idNumber = idNumber;
	}

	public String getIdNumber() 
	{
		return idNumber;
	}
	public void setBankNumber(String bankNumber) 
	{
		this.bankNumber = bankNumber;
	}

	public String getBankNumber() 
	{
		return bankNumber;
	}
	public void setAmount(String amount) 
	{
		this.amount = amount;
	}

	public String getAmount() 
	{
		return amount;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setReturnCode(String returnCode) 
	{
		this.returnCode = returnCode;
	}

	public String getReturnCode() 
	{
		return returnCode;
	}
	public void setReturnMessage(String returnMessage) 
	{
		this.returnMessage = returnMessage;
	}

	public String getReturnMessage() 
	{
		return returnMessage;
	}
	public void setBankIdserial(String bankIdserial) 
	{
		this.bankIdserial = bankIdserial;
	}

	public String getBankIdserial() 
	{
		return bankIdserial;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("bankCode", getBankCode())
            .append("transDate", getTransDate())
            .append("transIdserial", getTransIdserial())
            .append("userCode", getUserCode())
            .append("userName", getUserName())
            .append("idNumber", getIdNumber())
            .append("bankNumber", getBankNumber())
            .append("amount", getAmount())
            .append("status", getStatus())
            .append("returnCode", getReturnCode())
            .append("returnMessage", getReturnMessage())
            .append("bankIdserial", getBankIdserial())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
