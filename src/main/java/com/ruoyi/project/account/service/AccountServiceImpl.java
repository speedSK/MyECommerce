package com.ruoyi.project.account.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.account.mapper.AccountMapper;
import com.ruoyi.project.business.account.domain.Account;
import com.ruoyi.project.business.account.service.IAccountService;
import com.ruoyi.common.support.Convert;

/**
 * 用户账户 服务层实现
 * 
 * @author ruoyi
 * @date 2019-03-26
 */
@Service
public class AccountServiceImpl implements IAccountService 
{
	@Autowired
	private AccountMapper accountMapper;

	/**
     * 查询用户账户信息
     * 
     * @param id 用户账户ID
     * @return 用户账户信息
     */
    @Override
	public Account selectAccountById(Long id)
	{
	    return accountMapper.selectAccountById(id);
	}
	
	/**
     * 查询用户账户列表
     * 
     * @param account 用户账户信息
     * @return 用户账户集合
     */
	@Override
	public List<Account> selectAccountList(Account account)
	{
	    return accountMapper.selectAccountList(account);
	}
	
    /**
     * 新增用户账户
     * 
     * @param account 用户账户信息
     * @return 结果
     */
	@Override
	public int insertAccount(Account account)
	{
	    return accountMapper.insertAccount(account);
	}
	
	/**
     * 修改用户账户
     * 
     * @param account 用户账户信息
     * @return 结果
     */
	@Override
	public int updateAccount(Account account)
	{
	    return accountMapper.updateAccount(account);
	}

	/**
     * 删除用户账户对象
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
