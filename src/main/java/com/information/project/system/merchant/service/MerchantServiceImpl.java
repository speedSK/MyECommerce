package com.information.project.system.merchant.service;

import java.util.List;

import com.information.common.constant.Constants;
import com.information.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.system.merchant.mapper.MerchantMapper;
import com.information.project.system.merchant.domain.Merchant;
import com.information.project.system.merchant.service.IMerchantService;
import com.information.common.support.Convert;

import static com.information.common.constant.Constants.ACCOUNT_ACTIVE_3;

/**
 * 商户 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-07
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
		merchant.setStatus(Constants.STATUS_ACTIVE);

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
		merchant.setFlag(ACCOUNT_ACTIVE_3);

		merchant.setStatus(Constants.STATUS_ACTIVE);

		merchant.setCreateBy(ShiroUtils.getUserId().toString());

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

		//初始化數據信息

		merchant.setUpdateBy(ShiroUtils.getUserId().toString());

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
		String [] idsArray = Convert.toStrArray(ids);
		for (String id: idsArray) {
			Merchant merchant = new Merchant();
			merchant.setId(Long.valueOf(id));
			//初始化數據信息

			merchant.setStatus(Constants.STATUS_REMOVED);

			merchant.setUpdateBy(ShiroUtils.getUserId().toString());

			merchantMapper.updateMerchant(merchant);

		}

		return 1;

	}
	
}
