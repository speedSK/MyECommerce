package com.information.project.business.accountReport.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.accountReport.mapper.AccountReportMapper;
import com.information.project.business.accountReport.domain.AccountReport;
import com.information.project.business.accountReport.service.IAccountReportService;
import com.information.common.support.Convert;

/**
 * 系统账户报 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class AccountReportServiceImpl implements IAccountReportService 
{
	@Autowired
	private AccountReportMapper accountReportMapper;

	/**
     * 查询系统账户报信息
     * 
     * @param id 系统账户报ID
     * @return 系统账户报信息
     */
    @Override
	public AccountReport selectAccountReportById(Long id)
	{
	    return accountReportMapper.selectAccountReportById(id);
	}
	
	/**
     * 查询系统账户报列表
     * 
     * @param accountReport 系统账户报信息
     * @return 系统账户报集合
     */
	@Override
	public List<AccountReport> selectAccountReportList(AccountReport accountReport)
	{
	    return accountReportMapper.selectAccountReportList(accountReport);
	}
	
    /**
     * 新增系统账户报
     * 
     * @param accountReport 系统账户报信息
     * @return 结果
     */
	@Override
	public int insertAccountReport(AccountReport accountReport)
	{
	    return accountReportMapper.insertAccountReport(accountReport);
	}
	
	/**
     * 修改系统账户报
     * 
     * @param accountReport 系统账户报信息
     * @return 结果
     */
	@Override
	public int updateAccountReport(AccountReport accountReport)
	{
	    return accountReportMapper.updateAccountReport(accountReport);
	}

	/**
     * 删除系统账户报对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAccountReportByIds(String ids)
	{
		return accountReportMapper.deleteAccountReportByIds(Convert.toStrArray(ids));
	}
	
}
