package com.ruoyi.project.business.tradeRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 流水表 bus_trade_record
 * 
 * @author LiuNing
 * @date 2018-08-18
 */
public class TradeRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 流水号 */
	private String journo;
	/** 用户编号 */
	private String userNumber;
	/** 商户编号 */
	private String merchantCode;
	/** 订单号 */
	private String orderCode;
	/** 交易代码 */
	private String txcode;
	/** 交易金额 */
	private BigDecimal txamt;
	/** 来源账户 */
	private String fromAcc;
	/** 目标账户 */
	private String toAcc;
	/** 系统入账日期 */
	private Date regDate;
	/** 设备编号 */
	private String stationCode;
	/** 备注 */
	private String remark;
	/** 状态 */
	private String status;
	/** 创建人 */
	private String createBy;
	/** 创建时间 */
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
	public void setJourno(String journo) 
	{
		this.journo = journo;
	}

	public String getJourno() 
	{
		return journo;
	}
	public void setUserNumber(String userNumber) 
	{
		this.userNumber = userNumber;
	}

	public String getUserNumber() 
	{
		return userNumber;
	}
	public void setMerchantCode(String merchantCode) 
	{
		this.merchantCode = merchantCode;
	}

	public String getMerchantCode() 
	{
		return merchantCode;
	}
	public void setOrderCode(String orderCode) 
	{
		this.orderCode = orderCode;
	}

	public String getOrderCode() 
	{
		return orderCode;
	}
	public void setTxcode(String txcode) 
	{
		this.txcode = txcode;
	}

	public String getTxcode() 
	{
		return txcode;
	}
	public void setTxamt(BigDecimal txamt) 
	{
		this.txamt = txamt;
	}

	public BigDecimal getTxamt() 
	{
		return txamt;
	}
	public void setFromAcc(String fromAcc) 
	{
		this.fromAcc = fromAcc;
	}

	public String getFromAcc() 
	{
		return fromAcc;
	}
	public void setToAcc(String toAcc) 
	{
		this.toAcc = toAcc;
	}

	public String getToAcc() 
	{
		return toAcc;
	}
	public void setRegDate(Date regDate) 
	{
		this.regDate = regDate;
	}

	public Date getRegDate() 
	{
		return regDate;
	}
	public void setStationCode(String stationCode) 
	{
		this.stationCode = stationCode;
	}

	public String getStationCode() 
	{
		return stationCode;
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
            .append("journo", getJourno())
            .append("userNumber", getUserNumber())
            .append("merchantCode", getMerchantCode())
            .append("orderCode", getOrderCode())
            .append("txcode", getTxcode())
            .append("txamt", getTxamt())
            .append("fromAcc", getFromAcc())
            .append("toAcc", getToAcc())
            .append("regDate", getRegDate())
            .append("stationCode", getStationCode())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
