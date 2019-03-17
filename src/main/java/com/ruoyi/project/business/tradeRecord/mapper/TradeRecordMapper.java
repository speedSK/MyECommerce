package com.ruoyi.project.business.tradeRecord.mapper;

import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 流水 数据层
 * 
 * @author ruoyi
 * @date 2018-12-10
 */
@Repository
public interface TradeRecordMapper 
{
	/**
     * 查询流水信息
     * 
     * @param id 流水ID
     * @return 流水信息
     */
	public TradeRecord selectTradeRecordById(Long id);
	
	/**
     * 查询流水列表
     * 
     * @param tradeRecord 流水信息
     * @return 流水集合
     */
	public List<TradeRecord> selectTradeRecordList(TradeRecord tradeRecord);
	
	/**
     * 新增流水
     * 
     * @param tradeRecord 流水信息
     * @return 结果
     */
	public int insertTradeRecord(TradeRecord tradeRecord);
	
	/**
     * 修改流水
     * 
     * @param tradeRecord 流水信息
     * @return 结果
     */
	public int updateTradeRecord(TradeRecord tradeRecord);
	
	/**
     * 删除流水
     * 
     * @param id 流水ID
     * @return 结果
     */
	public int deleteTradeRecordById(Long id);
	
	/**
     * 批量删除流水
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTradeRecordByIds(String[] ids);

	public List<Map> selectOutList(String params);
}