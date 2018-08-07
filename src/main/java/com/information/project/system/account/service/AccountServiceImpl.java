package com.information.project.system.account.service;

import java.util.List;

import com.information.common.constant.Constants;
import com.information.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.system.account.mapper.AccountMapper;
import com.information.project.system.account.domain.Account;
import com.information.project.system.account.service.IAccountService;
import com.information.common.support.Convert;

import static com.information.common.constant.Constants.ACCOUNT_ACTIVE_3;

/**
 * 商户 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
@Service
public class AccountServiceImpl implements IAccountService 
{
	@Autowired
	private AccountMapper accountMapper;

	/**
     * 查询商户信息
     * 
     * @param id 商户ID
     * @return 商户信息
     */
    @Override
	public Account selectAccountById(Long id)
	{
	    return accountMapper.selectAccountById(id);
	}
	
	/**
     * 查询商户列表
     * 
     * @param account 商户信息
     * @return 商户集合
     */
	@Override
	public List<Account> selectAccountList(Account account)
	{

		account.setStatus(Constants.STATUS_ACTIVE);
		return accountMapper.selectAccountList(account);
	}
	
    /**
     * 新增商户
     * 
     * @param account 商户信息
     * @return 结果
     */
	@Override
	public int insertAccount(Account account)
	{

		account.setFlag(ACCOUNT_ACTIVE_3);

		account.setStatus(Constants.STATUS_ACTIVE);

		account.setCreateBy(ShiroUtils.getUserId().toString());

		return accountMapper.insertAccount(account);
	}
	
	/**
     * 修改商户
     * 
     * @param account 商户信息
     * @return 结果
     */
	@Override
	public int updateAccount(Account account)
	{

		//初始化數據信息

		account.setUpdateBy(ShiroUtils.getUserId().toString());

		return accountMapper.updateAccount(account);
	}

	/**
     * 删除商户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAccountByIds(String ids)
	{
		String [] idsArray = Convert.toStrArray(ids);
		for (String id: idsArray) {
			Account account = accountMapper.selectAccountById(Long.valueOf(id));
			//初始化數據信息

			account.setStatus(Constants.STATUS_REMOVED);

			account.setUpdateBy(ShiroUtils.getUserId().toString());

			accountMapper.updateAccount(account);

		}

		return 1;

	}
	
}
