package com.information.project.business.settleDate.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.information.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 系统结账日期表 bus_settle_date
 * 
 * @author LiuNing
 * @date 2018-08-14
 */
public class SettleDate extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 结算日期 */
	private Date settleDate;
	/** 结算状态 */
	private String settleStatus;
	/** 数据状态 */
	private String status;
	/** 备注 */
	private String remark;
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
	public void setSettleDate(Date settleDate) 
	{
		this.settleDate = settleDate;
	}

	public Date getSettleDate() 
	{
		return settleDate;
	}
	public void setSettleStatus(String settleStatus) 
	{
		this.settleStatus = settleStatus;
	}

	public String getSettleStatus() 
	{
		return settleStatus;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
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
            .append("settleDate", getSettleDate())
            .append("settleStatus", getSettleStatus())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
