package com.information.project.system.identity.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.information.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 身份管理表 bus_identity
 * 
 * @author LiuNing
 * @date 2018-08-10
 */
public class Identity extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 身份编码 */
	private String code;
	/** 身份名称 */
	private String name;
	/** 消费上限 */
	private BigDecimal costTotal;

	/** 身份名称 */
	private String status;

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
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setCostTotal(BigDecimal costTotal) 
	{
		this.costTotal = costTotal;
	}

	public BigDecimal getCostTotal() 
	{
		return costTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("costTotal", getCostTotal())
            .toString();
    }
}
