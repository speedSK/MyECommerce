package com.information.project.system.account.mapper;

import com.information.project.system.account.domain.Account;
import java.util.List;	

/**
 * 商户 数据层
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
public interface AccountMapper 
{
	/**
     * 查询商户信息
     * 
     * @param id 商户ID
     * @return 商户信息
     */
	public Account selectAccountById(Long id);
	
	/**
     * 查询商户列表
     * 
     * @param account 商户信息
     * @return 商户集合
     */
	public List<Account> selectAccountList(Account account);
	
	/**
     * 新增商户
     * 
     * @param account 商户信息
     * @return 结果
     */
	public int insertAccount(Account account);
	
	/**
     * 修改商户
     * 
     * @param account 商户信息
     * @return 结果
     */
	public int updateAccount(Account account);
	
	/**
     * 删除商户
     * 
     * @param id 商户ID
     * @return 结果
     */
	public int deleteAccountById(Long id);
	
	/**
     * 批量删除商户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAccountByIds(String[] ids);
	
}