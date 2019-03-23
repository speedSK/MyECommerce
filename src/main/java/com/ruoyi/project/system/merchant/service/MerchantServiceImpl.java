package com.ruoyi.project.system.merchant.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.IdGen;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.merchant.mapper.MerchantMapper;
import com.ruoyi.project.system.merchant.domain.Merchant;
import com.ruoyi.project.system.merchant.service.IMerchantService;
import com.ruoyi.common.support.Convert;
import org.springframework.transaction.annotation.Transactional;

import static com.ruoyi.common.constant.Constants.ACCOUNT_ACTIVE_3;

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
	@Autowired
	private ITradeRecordService tradeRecordService;

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

		merchant.setCreateBy(ShiroUtils.getLoginName());

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

		merchant.setUpdateBy(ShiroUtils.getLoginName());

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

			merchant.setUpdateBy(ShiroUtils.getLoginName());

			merchantMapper.updateMerchant(merchant);

		}

		return 1;

	}

	@Override
	public List<Merchant> selectMerchantAll() {
		return merchantMapper.selectMerchantAll();
	}

	@Override
	@Transactional
	public int settleMerchant(Merchant merchant) {
		Date operDate = new Date();
		TradeRecord tradeRecord = new TradeRecord();
		Merchant personal = merchantMapper.selectMerchantById(merchant.getId());
		tradeRecord.setMerchantCode(personal.getId().toString());
		tradeRecord.setBefore(merchant.getBalance());
		tradeRecord.setAfter(new BigDecimal(0));
		tradeRecord.setTxamt(personal.getBalance());
		Merchant coming = merchantMapper.selectMerchantById(Constants.ACCOUNT_ACTIVE_1_ID);
		tradeRecord.setFromAcc(coming.getId().toString());
		Merchant out = merchantMapper.selectMerchantById(Constants.ACCOUNT_ACTIVE_2_ID);
		tradeRecord.setToAcc(out.getId().toString());
		coming.setBalance(coming.getBalance().subtract(personal.getBalance()));
		coming.setUpdateBy(ShiroUtils.getLoginName());
		coming.setUpdateTime(operDate);
		merchantMapper.updateMerchant(coming);
		out.setBalance(out.getBalance().add(personal.getBalance()));
		out.setUpdateBy(ShiroUtils.getLoginName());
		out.setUpdateTime(operDate);
		merchantMapper.updateMerchant(out);
		personal.setBalance(new BigDecimal(0));
		personal.setUpdateBy(ShiroUtils.getLoginName());
		personal.setUpdateTime(operDate);
		merchantMapper.updateMerchant(personal);
		tradeRecord.setJourno(IdGen.getJourno());
		tradeRecord.setTxcode(Constants.TX_CODE_MERCHANT_SETTLE);
		tradeRecord.setStationCode("0000");
		tradeRecord.setRemark("商户结算");
		tradeRecord.setCreateBy(ShiroUtils.getLoginName());
		tradeRecord.setCreateTime(operDate);
		return tradeRecordService.insertTradeRecord(tradeRecord);
	}

}
