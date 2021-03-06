package com.ruoyi.project.business.operReport.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 系统操作报表 bus_oper_report
 * 
 * @author LiuNing
 * @date 2018-08-15
 */
public class OperReport extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	private Long id;
	/** 报表日期 */
	@Excel(name = "报表日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date reportDate;
	@Excel(name = "交易名称")
	private String remark;
	/** 交易码 */
	private String tradeCode;
	/** 交易笔数 */
	@Excel(name = "交易笔数")
	private Integer tradeNum;
	/** 交易金额 */
	@Excel(name = "交易金额")
	private BigDecimal tradeSum;
	/**  */
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
	public void setTradeCode(String tradeCode) 
	{
		this.tradeCode = tradeCode;
	}

	public String getTradeCode() 
	{
		return tradeCode;
	}
	public void setTradeNum(Integer tradeNum) 
	{
		this.tradeNum = tradeNum;
	}

	public Integer getTradeNum() 
	{
		return tradeNum;
	}
	public void setTradeSum(BigDecimal tradeSum) 
	{
		this.tradeSum = tradeSum;
	}

	public BigDecimal getTradeSum() 
	{
		return tradeSum;
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
            .append("tradeCode", getTradeCode())
            .append("tradeNum", getTradeNum())
            .append("tradeSum", getTradeSum())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
