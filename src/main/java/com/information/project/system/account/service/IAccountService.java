package com.information.project.system.account.service;

import com.information.project.system.account.domain.Account;
import java.util.List;

/**
 * 商户 服务层
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
public interface IAccountService 
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
     * 删除商户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAccountByIds(String ids);
	
}
