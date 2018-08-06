package com.information.project.business.accountReport.mapper;

import com.information.project.business.accountReport.domain.AccountReport;
import java.util.List;	

/**
 * 系统账户报 数据层
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public interface AccountReportMapper 
{
	/**
     * 查询系统账户报信息
     * 
     * @param id 系统账户报ID
     * @return 系统账户报信息
     */
	public AccountReport selectAccountReportById(Long id);
	
	/**
     * 查询系统账户报列表
     * 
     * @param accountReport 系统账户报信息
     * @return 系统账户报集合
     */
	public List<AccountReport> selectAccountReportList(AccountReport accountReport);
	
	/**
     * 新增系统账户报
     * 
     * @param accountReport 系统账户报信息
     * @return 结果
     */
	public int insertAccountReport(AccountReport accountReport);
	
	/**
     * 修改系统账户报
     * 
     * @param accountReport 系统账户报信息
     * @return 结果
     */
	public int updateAccountReport(AccountReport accountReport);
	
	/**
     * 删除系统账户报
     * 
     * @param id 系统账户报ID
     * @return 结果
     */
	public int deleteAccountReportById(Long id);
	
	/**
     * 批量删除系统账户报
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAccountReportByIds(String[] ids);
	
}