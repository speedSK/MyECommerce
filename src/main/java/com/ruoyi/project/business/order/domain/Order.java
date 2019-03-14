package com.ruoyi.project.business.order.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表 bus_order
 * 
 * @author LiuNing
 * @date 2018-08-10
 */
public class Order extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 订单号 */
	@Excel(name="订单号")
	private String orderCode;
	/** 订单总金额 */
	@Excel(name="订单金额")
	private BigDecimal money;
	/** 购买人ID */
	private Long personId;
	/** 购买人编码 */
	@Excel(name="购买人编号")
	private String personCode;
	/** 购买人姓名 */
	@Excel(name="购买人姓名")
	private String personName;
	/** 订单状态 */
	@Excel(name="订单状态",readConverterExp = "0=配送中,1=完成,2=取消")
	private String flag;
	/** 完成人员 */
	private String finishUser;
	/** 完成时间 */
	@Excel(name="完成时间" , width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Date finishTime;
	/** 状态 */
	private String status;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setOrderCode(String orderCode) 
	{
		this.orderCode = orderCode;
	}

	public String getOrderCode() 
	{
		return orderCode;
	}
	public void setMoney(BigDecimal money) 
	{
		this.money = money;
	}

	public BigDecimal getMoney() 
	{
		return money;
	}
	public void setPersonId(Long personId) 
	{
		this.personId = personId;
	}

	public Long getPersonId() 
	{
		return personId;
	}
	public void setPersonCode(String personCode) 
	{
		this.personCode = personCode;
	}

	public String getPersonCode() 
	{
		return personCode;
	}
	public void setPersonName(String personName) 
	{
		this.personName = personName;
	}

	public String getPersonName() 
	{
		return personName;
	}
	public void setFlag(String flag) 
	{
		this.flag = flag;
	}

	public String getFlag() 
	{
		return flag;
	}
	public void setFinishUser(String finishUser) 
	{
		this.finishUser = finishUser;
	}

	public String getFinishUser() 
	{
		return finishUser;
	}
	public void setFinishTime(Date finishTime) 
	{
		this.finishTime = finishTime;
	}

	public Date getFinishTime() 
	{
		return finishTime;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderCode", getOrderCode())
            .append("money", getMoney())
            .append("personId", getPersonId())
            .append("personCode", getPersonCode())
            .append("personName", getPersonName())
            .append("flag", getFlag())
            .append("finishUser", getFinishUser())
            .append("finishTime", getFinishTime())
            .append("status", getStatus())
            .toString();
    }
}
