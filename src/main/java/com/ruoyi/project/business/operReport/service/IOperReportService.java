package com.ruoyi.project.business.operReport.service;

import com.ruoyi.project.business.operReport.domain.OperReport;
import java.util.List;

/**
 * 系统操作报 服务层
 * 
 * @author LiuNing
 * @date 2018-08-15
 */
public interface IOperReportService 
{
	/**
     * 查询系统操作报信息
     * 
     * @param id 系统操作报ID
     * @return 系统操作报信息
     */
	public OperReport selectOperReportById(Long id);
	
	/**
     * 查询系统操作报列表
     * 
     * @param operReport 系统操作报信息
     * @return 系统操作报集合
     */
	public List<OperReport> selectOperReportList(OperReport operReport);
	
	/**
     * 新增系统操作报
     * 
     * @param operReport 系统操作报信息
     * @return 结果
     */
	public int insertOperReport(OperReport operReport);
	
	/**
     * 修改系统操作报
     * 
     * @param operReport 系统操作报信息
     * @return 结果
     */
	public int updateOperReport(OperReport operReport);
		
	/**
     * 删除系统操作报信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOperReportByIds(String ids);
	
}
