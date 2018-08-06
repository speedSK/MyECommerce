package com.information.project.system.merchant.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.system.merchant.mapper.MerchantMapper;
import com.information.project.system.merchant.domain.Merchant;
import com.information.project.system.merchant.service.IMerchantService;
import com.information.common.support.Convert;

/**
 * 商户 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class MerchantServiceImpl implements IMerchantService 
{
	@Autowired
	private MerchantMapper merchantMapper;

	/**
     * 查询商户信息
     * 
     * @param id 商户ID
     * @return 商户信息
     */
    @Override
	public Merchant selectMerchantById(Long id)
	{
	    return merchantMapper.selectMerchantById(id);
	}
	
	/**
     * 查询商户列表
     * 
     * @param merchant 商户信息
     * @return 商户集合
     */
	@Override
	public List<Merchant> selectMerchantList(Merchant merchant)
	{
	    return merchantMapper.selectMerchantList(merchant);
	}
	
    /**
     * 新增商户
     * 
     * @param merchant 商户信息
     * @return 结果
     */
	@Override
	public int insertMerchant(Merchant merchant)
	{
	    return merchantMapper.insertMerchant(merchant);
	}
	
	/**
     * 修改商户
     * 
     * @param merchant 商户信息
     * @return 结果
     */
	@Override
	public int updateMerchant(Merchant merchant)
	{
	    return merchantMapper.updateMerchant(merchant);
	}

	/**
     * 删除商户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMerchantByIds(String ids)
	{
		return merchantMapper.deleteMerchantByIds(Convert.toStrArray(ids));
	}
	
}
