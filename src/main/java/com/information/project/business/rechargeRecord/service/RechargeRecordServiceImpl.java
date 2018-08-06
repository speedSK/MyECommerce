package com.information.project.business.rechargeRecord.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.rechargeRecord.mapper.RechargeRecordMapper;
import com.information.project.business.rechargeRecord.domain.RechargeRecord;
import com.information.project.business.rechargeRecord.service.IRechargeRecordService;
import com.information.common.support.Convert;

/**
 * 银行转账 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class RechargeRecordServiceImpl implements IRechargeRecordService 
{
	@Autowired
	private RechargeRecordMapper rechargeRecordMapper;

	/**
     * 查询银行转账信息
     * 
     * @param id 银行转账ID
     * @return 银行转账信息
     */
    @Override
	public RechargeRecord selectRechargeRecordById(Long id)
	{
	    return rechargeRecordMapper.selectRechargeRecordById(id);
	}
	
	/**
     * 查询银行转账列表
     * 
     * @param rechargeRecord 银行转账信息
     * @return 银行转账集合
     */
	@Override
	public List<RechargeRecord> selectRechargeRecordList(RechargeRecord rechargeRecord)
	{
	    return rechargeRecordMapper.selectRechargeRecordList(rechargeRecord);
	}
	
    /**
     * 新增银行转账
     * 
     * @param rechargeRecord 银行转账信息
     * @return 结果
     */
	@Override
	public int insertRechargeRecord(RechargeRecord rechargeRecord)
	{
	    return rechargeRecordMapper.insertRechargeRecord(rechargeRecord);
	}
	
	/**
     * 修改银行转账
     * 
     * @param rechargeRecord 银行转账信息
     * @return 结果
     */
	@Override
	public int updateRechargeRecord(RechargeRecord rechargeRecord)
	{
	    return rechargeRecordMapper.updateRechargeRecord(rechargeRecord);
	}

	/**
     * 删除银行转账对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRechargeRecordByIds(String ids)
	{
		return rechargeRecordMapper.deleteRechargeRecordByIds(Convert.toStrArray(ids));
	}
	
}
