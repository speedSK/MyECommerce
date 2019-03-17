package com.ruoyi.project.business.tradeRecord.service;

import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import java.util.List;
import java.util.Map;

/**
 * 流水 服务层
 * 
 * @author ruoyi
 * @date 2018-12-10
 */
public interface ITradeRecordService 
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
     * 删除流水信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTradeRecordByIds(String ids);

	public List<Map> selectOut(String params);

	public List<Map> selectComing(String params);

	public List<Map> selectOperation(String params);
}
