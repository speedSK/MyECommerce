package com.ruoyi.project.business.transactionRecord.service;

import com.ruoyi.project.business.transactionRecord.domain.TransactionRecord;
import java.util.List;

/**
 * 银行转账 服务层
 * 
 * @author ruoyi
 * @date 2018-12-10
 */
public interface ITransactionRecordService 
{
	/**
     * 查询银行转账信息
     * 
     * @param id 银行转账ID
     * @return 银行转账信息
     */
	public TransactionRecord selectTransactionRecordById(Long id);
	
	/**
     * 查询银行转账列表
     * 
     * @param transactionRecord 银行转账信息
     * @return 银行转账集合
     */
	public List<TransactionRecord> selectTransactionRecordList(TransactionRecord transactionRecord);
	
	/**
     * 新增银行转账
     * 
     * @param transactionRecord 银行转账信息
     * @return 结果
     */
	public int insertTransactionRecord(TransactionRecord transactionRecord);
	
	/**
     * 修改银行转账
     * 
     * @param transactionRecord 银行转账信息
     * @return 结果
     */
	public int updateTransactionRecord(TransactionRecord transactionRecord);
		
	/**
     * 删除银行转账信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTransactionRecordByIds(String ids);
	
}
