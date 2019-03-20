package com.ruoyi.project.monitor.job.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.business.merchantReport.domain.MerchantReport;
import com.ruoyi.project.business.merchantReport.service.IMerchantReportService;
import com.ruoyi.project.business.operReport.domain.OperReport;
import com.ruoyi.project.business.operReport.service.IOperReportService;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private IMerchantReportService merchantReportService;
    @Autowired
    private IOperReportService operReportService;

    public void settleHistoryData(String params)
    {
        if (StringUtils.isEmpty(params)) {
            params = DateUtils.dateTime(DateUtils.getBeforeDay(new Date()));
        }
        createReport(params);
        logger.info("手动生成商户报表&操作报表：" + params);
    }

    public void settle()
    {
        String params = DateUtils.dateTime(DateUtils.getBeforeDay(new Date()));
        createReport(params);
        logger.info("自动生成商户报表&操作报表：" + params);
    }

    @Transactional
    public void createReport(String params) {
        List<MerchantReport> outRecords = merchantReportService.selectOut(params);
        for (MerchantReport or : outRecords) {
            merchantReportService.insertMerchantReport(or);
        }
        List<MerchantReport> comingRecords = merchantReportService.selectInComing(params);
        for (MerchantReport cr : comingRecords) {
            List<MerchantReport> reportList = merchantReportService.selectMerchantReportList(cr);
            if (StringUtils.isEmpty(reportList)) {
                merchantReportService.insertMerchantReport(cr);
            } else {
                merchantReportService.updateMerchantReport(cr);
            }
        }
        List<OperReport> operRecords = operReportService.selectOperation(params);
        for (OperReport oper : operRecords) {
            operReportService.insertOperReport(oper);
        }
    }


}
