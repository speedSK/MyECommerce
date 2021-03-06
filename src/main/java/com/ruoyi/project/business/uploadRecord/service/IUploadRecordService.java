package com.ruoyi.project.business.uploadRecord.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.uploadRecord.domain.UploadRecord;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 功能导入记录 服务层
 * 
 * @author LiuNing
 * @date 2018-08-08
 */
public interface IUploadRecordService 
{
	/**
     * 查询功能导入记录信息
     * 
     * @param id 功能导入记录ID
     * @return 功能导入记录信息
     */
	public UploadRecord selectUploadRecordById(Long id);
	
	/**
     * 查询功能导入记录列表
     * 
     * @param uploadRecord 功能导入记录信息
     * @return 功能导入记录集合
     */
	public List<UploadRecord> selectUploadRecordList(UploadRecord uploadRecord);
	
	/**
     * 新增功能导入记录
     * 
     * @param uploadRecord 功能导入记录信息
     * @return 结果
     */
	public int insertUploadRecord(UploadRecord uploadRecord);
	
	/**
     * 修改功能导入记录
     * 
     * @param uploadRecord 功能导入记录信息
     * @return 结果
     */
	public int updateUploadRecord(UploadRecord uploadRecord);
		
	/**
     * 删除功能导入记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUploadRecordByIds(String ids);

	public AjaxResult saveRecharge(MultipartFile file) throws IOException, Exception;

	public AjaxResult saveCost(MultipartFile file) throws IOException, Exception;
	
}
