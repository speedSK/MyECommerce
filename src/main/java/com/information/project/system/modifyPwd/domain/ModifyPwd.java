package com.information.project.system.modifyPwd.domain;

import com.information.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 密码修改申请表 bus_modify_pwd
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class ModifyPwd extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 用户id */
	private Long userid;
	/** 新秘密 */
	private String newPwd;
	/** 是否同意 */
	private String agreest;
	/** 状态 */
	private String status;
	/** 操作人 */
	private Long operUser;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;

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
	 * 设置：用户id
	 */
	public void setUserid(Long userid) 
	{
		this.userid = userid;
	}
	
	/**
	 * 获取：用户id
	 */
	public Long getUserid() 
	{
		return userid;
	}
	
	/**
	 * 设置：新秘密
	 */
	public void setNewPwd(String newPwd) 
	{
		this.newPwd = newPwd;
	}
	
	/**
	 * 获取：新秘密
	 */
	public String getNewPwd() 
	{
		return newPwd;
	}
	
	/**
	 * 设置：是否同意
	 */
	public void setAgreest(String agreest) 
	{
		this.agreest = agreest;
	}
	
	/**
	 * 获取：是否同意
	 */
	public String getAgreest() 
	{
		return agreest;
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
	 * 设置：操作人
	 */
	public void setOperUser(Long operUser) 
	{
		this.operUser = operUser;
	}
	
	/**
	 * 获取：操作人
	 */
	public Long getOperUser() 
	{
		return operUser;
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
	
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}
	
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() 
	{
		return updateTime;
	}
	
}
