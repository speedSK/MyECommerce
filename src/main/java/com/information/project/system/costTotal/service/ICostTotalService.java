package com.information.project.system.costTotal.service;

import com.information.project.system.costTotal.domain.CostTotal;
import java.util.List;

/**
 * 消费限额 服务层
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public interface ICostTotalService 
{
	/**
     * 查询消费限额信息
     * 
     * @param id 消费限额ID
     * @return 消费限额信息
     */
	public CostTotal selectCostTotalById(Long id);
	
	/**
     * 查询消费限额列表
     * 
     * @param costTotal 消费限额信息
     * @return 消费限额集合
     */
	public List<CostTotal> selectCostTotalList(CostTotal costTotal);
	
	/**
     * 新增消费限额
     * 
     * @param costTotal 消费限额信息
     * @return 结果
     */
	public int insertCostTotal(CostTotal costTotal);
	
	/**
     * 修改消费限额
     * 
     * @param costTotal 消费限额信息
     * @return 结果
     */
	public int updateCostTotal(CostTotal costTotal);
		
	/**
     * 删除消费限额信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCostTotalByIds(String ids);
	
}
