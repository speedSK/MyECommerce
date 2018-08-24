package com.ruoyi.project.business.settleDate.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.security.ShiroUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.settleDate.mapper.SettleDateMapper;
import com.ruoyi.project.business.settleDate.domain.SettleDate;
import com.ruoyi.project.business.settleDate.service.ISettleDateService;
import com.ruoyi.common.support.Convert;

/**
 * 系统结账日期 服务层实现
 *
 * @author LiuNing
 * @date 2018-08-14
 */
@Service
public class SettleDateServiceImpl implements ISettleDateService
{
	@Autowired
	private SettleDateMapper settleDateMapper;

	/**
     * 查询系统结账日期信息
     *
     * @param id 系统结账日期ID
     * @return 系统结账日期信息
     */
    @Override
	public SettleDate selectSettleDateById(Long id)
	{
	    return settleDateMapper.selectSettleDateById(id);
	}

	/**
     * 查询系统结账日期列表
     *
     * @param settleDate 系统结账日期信息
     * @return 系统结账日期集合
     */
	@Override
	public List<SettleDate> selectSettleDateList(SettleDate settleDate)
	{
	    settleDate.setStatus(Constants.STATUS_ACTIVE);
	    return settleDateMapper.selectSettleDateList(settleDate);
	}

    /**
     * 新增系统结账日期
     *
     * @param settleDate 系统结账日期信息
     * @return 结果
     */
	@Override
	public int insertSettleDate(SettleDate settleDate)
	{
	    settleDate.setStatus(Constants.STATUS_ACTIVE);

        settleDate.setCreateBy(ShiroUtils.getUserId().toString());
	    return settleDateMapper.insertSettleDate(settleDate);
	}

	/**
     * 修改系统结账日期
     *
     * @param settleDate 系统结账日期信息
     * @return 结果
     */
	@Override
	public int updateSettleDate(SettleDate settleDate)
	{
	    settleDate.setUpdateBy(ShiroUtils.getUserId().toString());
	    return settleDateMapper.updateSettleDate(settleDate);
	}

	/**
     * 删除系统结账日期对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSettleDateByIds(String ids)
	{
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            SettleDate settleDate = new SettleDate();
            settleDate.setId(Long.valueOf(id));
            //初始化數據信息

            settleDate.setStatus(Constants.STATUS_REMOVED);

            settleDate.setUpdateBy(ShiroUtils.getUserId().toString());

            settleDateMapper.updateSettleDate(settleDate);

        }

        return 1;
	}
	
}
