package com.ruoyi.project.business.account.mapper;

import com.ruoyi.project.business.account.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户账户 数据层
 * 
 * @author ruoyi
 * @date 2019-03-26
 */
@Repository
public interface AccountMapper 
{
	/**
     * 查询用户账户信息
     * 
     * @param id 用户账户ID
     * @return 用户账户信息
     */
	public Account selectAccountById(Long id);
	
	/**
     * 查询用户账户列表
     * 
     * @param account 用户账户信息
     * @return 用户账户集合
     */
	public List<Account> selectAccountList(Account account);
	
	/**
     * 新增用户账户
     * 
     * @param account 用户账户信息
     * @return 结果
     */
	public int insertAccount(Account account);
	
	/**
     * 修改用户账户
     * 
     * @param account 用户账户信息
     * @return 结果
     */
	public int updateAccount(Account account);
	
	/**
     * 删除用户账户
     * 
     * @param id 用户账户ID
     * @return 结果
     */
	public int deleteAccountById(Long id);
	
	/**
     * 批量删除用户账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAccountByIds(String[] ids);
	
}