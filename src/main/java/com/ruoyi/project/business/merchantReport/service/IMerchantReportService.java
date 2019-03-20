package com.ruoyi.project.business.merchantReport.service;

import com.ruoyi.project.business.merchantReport.domain.MerchantReport;
import java.util.List;

/**
 * 系统账户报 服务层
 * 
 * @author LiuNing
 * @date 2018-08-15
 */
public interface IMerchantReportService 
{
	/**
     * 查询系统账户报信息
     * 
     * @param id 系统账户报ID
     * @return 系统账户报信息
     */
	public MerchantReport selectMerchantReportById(Long id);
	
	/**
     * 查询系统账户报列表
     * 
     * @param merchantReport 系统账户报信息
     * @return 系统账户报集合
     */
	public List<MerchantReport> selectMerchantReportList(MerchantReport merchantReport);
	
	/**
     * 新增系统账户报
     * 
     * @param merchantReport 系统账户报信息
     * @return 结果
     */
	public int insertMerchantReport(MerchantReport merchantReport);
	
	/**
     * 修改系统账户报
     * 
     * @param merchantReport 系统账户报信息
     * @return 结果
     */
	public int updateMerchantReport(MerchantReport merchantReport);
		
	/**
     * 删除系统账户报信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMerchantReportByIds(String ids);

	List<MerchantReport> selectOut(String params);

	List<MerchantReport> selectInComing(String params);
}
