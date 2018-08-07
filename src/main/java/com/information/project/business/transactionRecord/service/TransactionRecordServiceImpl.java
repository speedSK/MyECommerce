package com.information.project.business.transactionRecord.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.transactionRecord.mapper.TransactionRecordMapper;
import com.information.project.business.transactionRecord.domain.TransactionRecord;
import com.information.project.business.transactionRecord.service.ITransactionRecordService;
import com.information.common.support.Convert;

/**
 * 银行转账 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
@Service
public class TransactionRecordServiceImpl implements ITransactionRecordService 
{
	@Autowired
	private TransactionRecordMapper transactionRecordMapper;

	/**
     * 查询银行转账信息
     * 
     * @param id 银行转账ID
     * @return 银行转账信息
     */
    @Override
	public TransactionRecord selectTransactionRecordById(Long id)
	{
	    return transactionRecordMapper.selectTransactionRecordById(id);
	}
	
	/**
     * 查询银行转账列表
     * 
     * @param transactionRecord 银行转账信息
     * @return 银行转账集合
     */
	@Override
	public List<TransactionRecord> selectTransactionRecordList(TransactionRecord transactionRecord)
	{
	    return transactionRecordMapper.selectTransactionRecordList(transactionRecord);
	}
	
    /**
     * 新增银行转账
     * 
     * @param transactionRecord 银行转账信息
     * @return 结果
     */
	@Override
	public int insertTransactionRecord(TransactionRecord transactionRecord)
	{
	    return transactionRecordMapper.insertTransactionRecord(transactionRecord);
	}
	
	/**
     * 修改银行转账
     * 
     * @param transactionRecord 银行转账信息
     * @return 结果
     */
	@Override
	public int updateTransactionRecord(TransactionRecord transactionRecord)
	{
	    return transactionRecordMapper.updateTransactionRecord(transactionRecord);
	}

	/**
     * 删除银行转账对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTransactionRecordByIds(String ids)
	{
		return transactionRecordMapper.deleteTransactionRecordByIds(Convert.toStrArray(ids));
	}
	
}
