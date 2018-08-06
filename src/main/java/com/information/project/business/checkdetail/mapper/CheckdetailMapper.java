package com.information.project.business.checkdetail.mapper;

import com.information.project.business.checkdetail.domain.Checkdetail;
import java.util.List;	

/**
 * 银行详情对账对账 数据层
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public interface CheckdetailMapper 
{
	/**
     * 查询银行详情对账对账信息
     * 
     * @param id 银行详情对账对账ID
     * @return 银行详情对账对账信息
     */
	public Checkdetail selectCheckdetailById(Long id);
	
	/**
     * 查询银行详情对账对账列表
     * 
     * @param checkdetail 银行详情对账对账信息
     * @return 银行详情对账对账集合
     */
	public List<Checkdetail> selectCheckdetailList(Checkdetail checkdetail);
	
	/**
     * 新增银行详情对账对账
     * 
     * @param checkdetail 银行详情对账对账信息
     * @return 结果
     */
	public int insertCheckdetail(Checkdetail checkdetail);
	
	/**
     * 修改银行详情对账对账
     * 
     * @param checkdetail 银行详情对账对账信息
     * @return 结果
     */
	public int updateCheckdetail(Checkdetail checkdetail);
	
	/**
     * 删除银行详情对账对账
     * 
     * @param id 银行详情对账对账ID
     * @return 结果
     */
	public int deleteCheckdetailById(Long id);
	
	/**
     * 批量删除银行详情对账对账
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCheckdetailByIds(String[] ids);
	
}