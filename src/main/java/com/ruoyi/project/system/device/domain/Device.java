package com.ruoyi.project.system.device.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 设备表 bus_device
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public class Device extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 编号 */
	private String code;
	/** 名称 */
	private String name;
	/** 类型 */
	private String type;
	/** IP地址 */
	private String ip;
	/** 位置 */
	private String address;

	/** 状态 */
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
	 * 设置：编号
	 */
	public void setCode(String code) 
	{
		this.code = code;
	}
	
	/**
	 * 获取：编号
	 */
	public String getCode() 
	{
		return code;
	}
	
	/**
	 * 设置：名称
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：名称
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * 设置：类型
	 */
	public void setType(String type) 
	{
		this.type = type;
	}
	
	/**
	 * 获取：类型
	 */
	public String getType() 
	{
		return type;
	}
	
	/**
	 * 设置：IP地址
	 */
	public void setIp(String ip) 
	{
		this.ip = ip;
	}
	
	/**
	 * 获取：IP地址
	 */
	public String getIp() 
	{
		return ip;
	}
	
	/**
	 * 设置：位置
	 */
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	/**
	 * 获取：位置
	 */
	public String getAddress() 
	{
		return address;
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

	
}
