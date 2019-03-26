package com.ruoyi.project.business.account.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 用户账户表 bus_account
 * 
 * @author ruoyi
 * @date 2019-03-26
 */
public class Account extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 用户id */
	private Long personId;
	/** 账户号 */
	private String personAccount;
	/** 创建人 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setPersonId(Long personId) 
	{
		this.personId = personId;
	}

	public Long getPersonId() 
	{
		return personId;
	}
	public void setPersonAccount(String personAccount) 
	{
		this.personAccount = personAccount;
	}

	public String getPersonAccount() 
	{
		return personAccount;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("personId", getPersonId())
            .append("personAccount", getPersonAccount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
