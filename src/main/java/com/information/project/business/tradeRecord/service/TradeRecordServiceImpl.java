package com.information.project.business.tradeRecord.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.tradeRecord.mapper.TradeRecordMapper;
import com.information.project.business.tradeRecord.domain.TradeRecord;
import com.information.project.business.tradeRecord.service.ITradeRecordService;
import com.information.common.support.Convert;

/**
 * 流水 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class TradeRecordServiceImpl implements ITradeRecordService 
{
	@Autowired
	private TradeRecordMapper tradeRecordMapper;

	/**
     * 查询流水信息
     * 
     * @param id 流水ID
     * @return 流水信息
     */
    @Override
	public TradeRecord selectTradeRecordById(Long id)
	{
	    return tradeRecordMapper.selectTradeRecordById(id);
	}
	
	/**
     * 查询流水列表
     * 
     * @param tradeRecord 流水信息
     * @return 流水集合
     */
	@Override
	public List<TradeRecord> selectTradeRecordList(TradeRecord tradeRecord)
	{
	    return tradeRecordMapper.selectTradeRecordList(tradeRecord);
	}
	
    /**
     * 新增流水
     * 
     * @param tradeRecord 流水信息
     * @return 结果
     */
	@Override
	public int insertTradeRecord(TradeRecord tradeRecord)
	{
	    return tradeRecordMapper.insertTradeRecord(tradeRecord);
	}
	
	/**
     * 修改流水
     * 
     * @param tradeRecord 流水信息
     * @return 结果
     */
	@Override
	public int updateTradeRecord(TradeRecord tradeRecord)
	{
	    return tradeRecordMapper.updateTradeRecord(tradeRecord);
	}

	/**
     * 删除流水对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTradeRecordByIds(String ids)
	{
		return tradeRecordMapper.deleteTradeRecordByIds(Convert.toStrArray(ids));
	}
	
}
