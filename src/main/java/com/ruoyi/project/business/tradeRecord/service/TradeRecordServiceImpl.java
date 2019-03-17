package com.ruoyi.project.business.tradeRecord.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.tradeRecord.mapper.TradeRecordMapper;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import com.ruoyi.common.support.Convert;

/**
 * 流水 服务层实现
 * 
 * @author ruoyi
 * @date 2018-12-10
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
        ShiroUtils.getLoginName();
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

	@Override
	public List<Map> selectOut(String params) {
		return tradeRecordMapper.selectOutList(params);
	}

	@Override
	public List<TradeRecord> selectComing(String params) {
//		return tradeRecordMapper.selectInComingList(params);
		return null;
	}

	@Override
	public List<TradeRecord> selectOperation(String params) {
//		return tradeRecordMapper.selectOperation(params);
		return null;
	}

}
