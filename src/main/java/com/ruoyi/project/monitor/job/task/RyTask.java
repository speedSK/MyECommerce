package com.ruoyi.project.monitor.job.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask {

    private static final Logger logger = LoggerFactory.getLogger(RyTask.class);
    @Autowired
    private ITradeRecordService tradeRecordService;
    public void ryParams(String params)
    {
        if (StringUtils.isEmpty(params)) {
            params = DateUtils.dateTime(DateUtils.getBeforeDay(new Date()));
        }
        List<Map> outRecords = tradeRecordService.selectOut(params);
        List<TradeRecord> comingRecords = tradeRecordService.selectComing(params);
        List<TradeRecord> operRecords = tradeRecordService.selectOperation(params);



        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }



}
