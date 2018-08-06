package com.information.project.system.merchant.mapper;

import com.information.project.system.merchant.domain.Merchant;
import java.util.List;	

/**
 * 商户 数据层
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public interface MerchantMapper 
{
	/**
     * 查询商户信息
     * 
     * @param id 商户ID
     * @return 商户信息
     */
	public Merchant selectMerchantById(Long id);
	
	/**
     * 查询商户列表
     * 
     * @param merchant 商户信息
     * @return 商户集合
     */
	public List<Merchant> selectMerchantList(Merchant merchant);
	
	/**
     * 新增商户
     * 
     * @param merchant 商户信息
     * @return 结果
     */
	public int insertMerchant(Merchant merchant);
	
	/**
     * 修改商户
     * 
     * @param merchant 商户信息
     * @return 结果
     */
	public int updateMerchant(Merchant merchant);
	
	/**
     * 删除商户
     * 
     * @param id 商户ID
     * @return 结果
     */
	public int deleteMerchantById(Long id);
	
	/**
     * 批量删除商户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMerchantByIds(String[] ids);
	
}