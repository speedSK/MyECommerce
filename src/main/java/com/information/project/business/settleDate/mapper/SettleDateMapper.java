package com.information.project.business.settleDate.mapper;

import com.information.project.business.settleDate.domain.SettleDate;
import java.util.List;	

/**
 * 系统结账日期 数据层
 * 
 * @author LiuNing
 * @date 2018-08-14
 */
public interface SettleDateMapper 
{
	/**
     * 查询系统结账日期信息
     * 
     * @param id 系统结账日期ID
     * @return 系统结账日期信息
     */
	public SettleDate selectSettleDateById(Long id);
	
	/**
     * 查询系统结账日期列表
     * 
     * @param settleDate 系统结账日期信息
     * @return 系统结账日期集合
     */
	public List<SettleDate> selectSettleDateList(SettleDate settleDate);
	
	/**
     * 新增系统结账日期
     * 
     * @param settleDate 系统结账日期信息
     * @return 结果
     */
	public int insertSettleDate(SettleDate settleDate);
	
	/**
     * 修改系统结账日期
     * 
     * @param settleDate 系统结账日期信息
     * @return 结果
     */
	public int updateSettleDate(SettleDate settleDate);
	
	/**
     * 删除系统结账日期
     * 
     * @param id 系统结账日期ID
     * @return 结果
     */
	public int deleteSettleDateById(Long id);
	
	/**
     * 批量删除系统结账日期
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSettleDateByIds(String[] ids);
	
}