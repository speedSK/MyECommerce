package com.ruoyi.project.system.merchant.service;

import com.ruoyi.project.system.merchant.domain.Merchant;
import java.util.List;

/**
 * 商户 服务层
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
public interface IMerchantService 
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
     * 删除商户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMerchantByIds(String ids);

	public List<Merchant> selectMerchantAll();

    public int settleMerchant(Merchant merchant);
}
