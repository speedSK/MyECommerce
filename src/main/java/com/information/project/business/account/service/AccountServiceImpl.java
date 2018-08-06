package com.information.project.business.account.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.account.mapper.AccountMapper;
import com.information.project.business.account.domain.Account;
import com.information.project.business.account.service.IAccountService;
import com.information.common.support.Convert;

/**
 * 系统账户 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class AccountServiceImpl implements IAccountService 
{
	@Autowired
	private AccountMapper accountMapper;

	/**
     * 查询系统账户信息
     * 
     * @param id 系统账户ID
     * @return 系统账户信息
     */
    @Override
	public Account selectAccountById(Long id)
	{
	    return accountMapper.selectAccountById(id);
	}
	
	/**
     * 查询系统账户列表
     * 
     * @param account 系统账户信息
     * @return 系统账户集合
     */
	@Override
	public List<Account> selectAccountList(Account account)
	{
	    return accountMapper.selectAccountList(account);
	}
	
    /**
     * 新增系统账户
     * 
     * @param account 系统账户信息
     * @return 结果
     */
	@Override
	public int insertAccount(Account account)
	{
	    return accountMapper.insertAccount(account);
	}
	
	/**
     * 修改系统账户
     * 
     * @param account 系统账户信息
     * @return 结果
     */
	@Override
	public int updateAccount(Account account)
	{
	    return accountMapper.updateAccount(account);
	}

	/**
     * 删除系统账户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAccountByIds(String ids)
	{
		return accountMapper.deleteAccountByIds(Convert.toStrArray(ids));
	}
	
}
