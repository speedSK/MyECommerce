package com.information.project.business.account.mapper;

import com.information.project.business.account.domain.Account;
import java.util.List;	

/**
 * 系统账户 数据层
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public interface AccountMapper 
{
	/**
     * 查询系统账户信息
     * 
     * @param id 系统账户ID
     * @return 系统账户信息
     */
	public Account selectAccountById(Long id);
	
	/**
     * 查询系统账户列表
     * 
     * @param account 系统账户信息
     * @return 系统账户集合
     */
	public List<Account> selectAccountList(Account account);
	
	/**
     * 新增系统账户
     * 
     * @param account 系统账户信息
     * @return 结果
     */
	public int insertAccount(Account account);
	
	/**
     * 修改系统账户
     * 
     * @param account 系统账户信息
     * @return 结果
     */
	public int updateAccount(Account account);
	
	/**
     * 删除系统账户
     * 
     * @param id 系统账户ID
     * @return 结果
     */
	public int deleteAccountById(Long id);
	
	/**
     * 批量删除系统账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAccountByIds(String[] ids);
	
}