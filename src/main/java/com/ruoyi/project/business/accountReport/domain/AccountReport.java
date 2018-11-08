package com.ruoyi.project.business.accountReport.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 系统账户报表 bus_account_report
 * 
 * @author LiuNing
 * @date 2018-08-15
 */
public class AccountReport extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	@Excel(name = "序号")
	private Long id;
	/** 报表日期 */
	@Excel(name = "报表日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date reportDate;
	/** 账户id */
	@Excel(name = "账户编号")
	private String accountCode;
	/** 收入笔数 */
	@Excel(name = "收入笔数")
	private Integer incomeNum;
	/** 收入金额 */
	@Excel(name = "收入金额")
	private BigDecimal incomeSum;
	/** 支出笔数 */
	@Excel(name = "支出笔数")
	private Integer outcomeNum;
	/** 支出金额 */
	@Excel(name = "支出金额")
	private BigDecimal outcomeSum;
	/**  */
	@Excel(name = "备注")
	private String remark;
	/**  */
	@Excel(name = "状态")
	private String status;
	/**  */
	private Date createTime;
	/**  */
	private Date updateTime;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setReportDate(Date reportDate) 
	{
		this.reportDate = reportDate;
	}

	public Date getReportDate() 
	{
		return reportDate;
	}
	public void setAccountCode(String accountCode) 
	{
		this.accountCode = accountCode;
	}

	public String getAccountCode() 
	{
		return accountCode;
	}
	public void setIncomeNum(Integer incomeNum) 
	{
		this.incomeNum = incomeNum;
	}

	public Integer getIncomeNum() 
	{
		return incomeNum;
	}
	public void setIncomeSum(BigDecimal incomeSum) 
	{
		this.incomeSum = incomeSum;
	}

	public BigDecimal getIncomeSum() 
	{
		return incomeSum;
	}
	public void setOutcomeNum(Integer outcomeNum) 
	{
		this.outcomeNum = outcomeNum;
	}

	public Integer getOutcomeNum() 
	{
		return outcomeNum;
	}
	public void setOutcomeSum(BigDecimal outcomeSum) 
	{
		this.outcomeSum = outcomeSum;
	}

	public BigDecimal getOutcomeSum() 
	{
		return outcomeSum;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
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
            .append("reportDate", getReportDate())
            .append("accountCode", getAccountCode())
            .append("incomeNum", getIncomeNum())
            .append("incomeSum", getIncomeSum())
            .append("outcomeNum", getOutcomeNum())
            .append("outcomeSum", getOutcomeSum())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
