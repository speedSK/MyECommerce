package com.information.project.business.rechargeRecord.service;

import com.information.project.business.rechargeRecord.domain.RechargeRecord;
import java.util.List;

/**
 * 银行转账 服务层
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public interface IRechargeRecordService 
{
	/**
     * 查询银行转账信息
     * 
     * @param id 银行转账ID
     * @return 银行转账信息
     */
	public RechargeRecord selectRechargeRecordById(Long id);
	
	/**
     * 查询银行转账列表
     * 
     * @param rechargeRecord 银行转账信息
     * @return 银行转账集合
     */
	public List<RechargeRecord> selectRechargeRecordList(RechargeRecord rechargeRecord);
	
	/**
     * 新增银行转账
     * 
     * @param rechargeRecord 银行转账信息
     * @return 结果
     */
	public int insertRechargeRecord(RechargeRecord rechargeRecord);
	
	/**
     * 修改银行转账
     * 
     * @param rechargeRecord 银行转账信息
     * @return 结果
     */
	public int updateRechargeRecord(RechargeRecord rechargeRecord);
		
	/**
     * 删除银行转账信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRechargeRecordByIds(String ids);
	
}
