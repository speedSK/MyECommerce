package com.information.project.business.checkbill.service;

import com.information.project.business.checkbill.domain.Checkbill;
import java.util.List;

/**
 * 银行总账对账 服务层
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public interface ICheckbillService 
{
	/**
     * 查询银行总账对账信息
     * 
     * @param id 银行总账对账ID
     * @return 银行总账对账信息
     */
	public Checkbill selectCheckbillById(Long id);
	
	/**
     * 查询银行总账对账列表
     * 
     * @param checkbill 银行总账对账信息
     * @return 银行总账对账集合
     */
	public List<Checkbill> selectCheckbillList(Checkbill checkbill);
	
	/**
     * 新增银行总账对账
     * 
     * @param checkbill 银行总账对账信息
     * @return 结果
     */
	public int insertCheckbill(Checkbill checkbill);
	
	/**
     * 修改银行总账对账
     * 
     * @param checkbill 银行总账对账信息
     * @return 结果
     */
	public int updateCheckbill(Checkbill checkbill);
		
	/**
     * 删除银行总账对账信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCheckbillByIds(String ids);
	
}
