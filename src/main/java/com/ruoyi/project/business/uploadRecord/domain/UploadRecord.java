package com.ruoyi.project.business.uploadRecord.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 功能导入记录表 bus_upload_record
 * 
 * @author LiuNing
 * @date 2018-08-08
 */
public class UploadRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 功能模块名称 */
	private String module;
	/** 上传文件名 */
	private String uploadName;
	/** 成功条数 */
	private Long successCount;
	/** 失败文件名 */
	private String failName;
	/** 失败条数 */
	private Long failCount;
	/** 状态 */
	private String status;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private String createBy;
	/** 修改时间 */
	private Date updateTime;
	/** 修改人 */
	private String updateBy;

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
	 * 设置：功能模块名称
	 */
	public void setModule(String module) 
	{
		this.module = module;
	}
	
	/**
	 * 获取：功能模块名称
	 */
	public String getModule() 
	{
		return module;
	}
	
	/**
	 * 设置：上传文件名
	 */
	public void setUploadName(String uploadName) 
	{
		this.uploadName = uploadName;
	}
	
	/**
	 * 获取：上传文件名
	 */
	public String getUploadName() 
	{
		return uploadName;
	}
	
	/**
	 * 设置：成功条数
	 */
	public void setSuccessCount(Long successCount) 
	{
		this.successCount = successCount;
	}
	
	/**
	 * 获取：成功条数
	 */
	public Long getSuccessCount() 
	{
		return successCount;
	}
	
	/**
	 * 设置：失败文件名
	 */
	public void setFailName(String failName) 
	{
		this.failName = failName;
	}
	
	/**
	 * 获取：失败文件名
	 */
	public String getFailName() 
	{
		return failName;
	}
	
	/**
	 * 设置：失败条数
	 */
	public void setFailCount(Long failCount) 
	{
		this.failCount = failCount;
	}
	
	/**
	 * 获取：失败条数
	 */
	public Long getFailCount() 
	{
		return failCount;
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
	 * 设置：备注
	 */
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
	
	/**
	 * 获取：备注
	 */
	public String getRemark() 
	{
		return remark;
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
	 * 设置：创建人
	 */
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}
	
	/**
	 * 获取：创建人
	 */
	public String getCreateBy() 
	{
		return createBy;
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
	
	/**
	 * 设置：修改人
	 */
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}
	
	/**
	 * 获取：修改人
	 */
	public String getUpdateBy() 
	{
		return updateBy;
	}
	
}
