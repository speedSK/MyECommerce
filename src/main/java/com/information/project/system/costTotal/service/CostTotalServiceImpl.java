package com.information.project.system.costTotal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.system.costTotal.mapper.CostTotalMapper;
import com.information.project.system.costTotal.domain.CostTotal;
import com.information.project.system.costTotal.service.ICostTotalService;
import com.information.common.support.Convert;

/**
 * 消费限额 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class CostTotalServiceImpl implements ICostTotalService 
{
	@Autowired
	private CostTotalMapper costTotalMapper;

	/**
     * 查询消费限额信息
     * 
     * @param id 消费限额ID
     * @return 消费限额信息
     */
    @Override
	public CostTotal selectCostTotalById(Long id)
	{
	    return costTotalMapper.selectCostTotalById(id);
	}
	
	/**
     * 查询消费限额列表
     * 
     * @param costTotal 消费限额信息
     * @return 消费限额集合
     */
	@Override
	public List<CostTotal> selectCostTotalList(CostTotal costTotal)
	{
	    return costTotalMapper.selectCostTotalList(costTotal);
	}
	
    /**
     * 新增消费限额
     * 
     * @param costTotal 消费限额信息
     * @return 结果
     */
	@Override
	public int insertCostTotal(CostTotal costTotal)
	{
	    return costTotalMapper.insertCostTotal(costTotal);
	}
	
	/**
     * 修改消费限额
     * 
     * @param costTotal 消费限额信息
     * @return 结果
     */
	@Override
	public int updateCostTotal(CostTotal costTotal)
	{
	    return costTotalMapper.updateCostTotal(costTotal);
	}

	/**
     * 删除消费限额对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCostTotalByIds(String ids)
	{
		return costTotalMapper.deleteCostTotalByIds(Convert.toStrArray(ids));
	}
	
}
