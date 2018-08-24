package com.ruoyi.project.business.tradeRecord.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.security.ShiroUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.tradeRecord.mapper.TradeRecordMapper;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import com.ruoyi.common.support.Convert;

/**
 * 流水 服务层实现
 *
 * @author LiuNing
 * @date 2018-08-18
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
	    tradeRecord.setStatus(Constants.STATUS_ACTIVE);
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
	    tradeRecord.setStatus(Constants.STATUS_ACTIVE);

        tradeRecord.setCreateBy(ShiroUtils.getUserId().toString());
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
	    tradeRecord.setUpdateBy(ShiroUtils.getUserId().toString());
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
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            TradeRecord tradeRecord = new TradeRecord();
            tradeRecord.setId(Long.valueOf(id));
            //初始化數據信息

            tradeRecord.setStatus(Constants.STATUS_REMOVED);

            tradeRecord.setUpdateBy(ShiroUtils.getUserId().toString());

            tradeRecordMapper.updateTradeRecord(tradeRecord);

        }

        return 1;
	}
	
}
