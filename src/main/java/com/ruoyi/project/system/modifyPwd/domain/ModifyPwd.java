package com.ruoyi.project.system.modifyPwd.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 密码修改申请表 bus_modify_pwd
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
public class ModifyPwd extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 用户id */
	private Long userid;
	/** 编号 */
	private String number;
	/** 姓名 */
	private String name;
	/** 新密码 */
	private String newPwd;
	/** 是否同意 */
	private String agreest;

	private String status;

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
	 * 设置：编号
	 */
	public void setNumber(String number) 
	{
		this.number = number;
	}
	
	/**
	 * 获取：编号
	 */
	public String getNumber() 
	{
		return number;
	}
	
	/**
	 * 设置：姓名
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * 设置：新密码
	 */
	public void setNewPwd(String newPwd) 
	{
		this.newPwd = newPwd;
	}
	
	/**
	 * 获取：新密码
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
