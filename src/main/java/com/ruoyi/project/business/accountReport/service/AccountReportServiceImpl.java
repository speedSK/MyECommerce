package com.ruoyi.project.business.accountReport.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.security.ShiroUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.accountReport.mapper.AccountReportMapper;
import com.ruoyi.project.business.accountReport.domain.AccountReport;
import com.ruoyi.project.business.accountReport.service.IAccountReportService;
import com.ruoyi.common.support.Convert;

/**
 * 系统账户报 服务层实现
 *
 * @author LiuNing
 * @date 2018-08-15
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
	    accountReport.setStatus(Constants.STATUS_ACTIVE);
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
	    accountReport.setStatus(Constants.STATUS_ACTIVE);

        accountReport.setCreateBy(ShiroUtils.getLoginName());
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
	    accountReport.setUpdateBy(ShiroUtils.getLoginName());
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
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            AccountReport accountReport = new AccountReport();
            accountReport.setId(Long.valueOf(id));
            //初始化數據信息

            accountReport.setStatus(Constants.STATUS_REMOVED);

            accountReport.setUpdateBy(ShiroUtils.getLoginName());

            accountReportMapper.updateAccountReport(accountReport);

        }

        return 1;
	}
	
}
