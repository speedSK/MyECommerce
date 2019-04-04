package com.ruoyi.project.monitor.job.task;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.IdGen;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.bank.TransOfABC;
import com.ruoyi.project.bank.domain.ReceiveFromBankInfo;
import com.ruoyi.project.bank.domain.TransVo;
import com.ruoyi.project.business.account.mapper.AccountMapper;
import com.ruoyi.project.business.merchantReport.domain.MerchantReport;
import com.ruoyi.project.business.merchantReport.service.IMerchantReportService;
import com.ruoyi.project.business.operReport.domain.OperReport;
import com.ruoyi.project.business.operReport.service.IOperReportService;
import com.ruoyi.project.business.person.service.IPersonService;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
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
    @Autowired
    private IPersonService personService;
    @Autowired
    private AccountMapper accountMapper;

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
        MerchantReport merchantReport = new MerchantReport();
        merchantReport.setReportDate(DateUtils.dateTime(DateUtils.YYYY_MM_DD, params));
        List<MerchantReport> merchantReports = merchantReportService.selectMerchantReportList(merchantReport);
        if (StringUtils.isEmpty(merchantReports)) {
            createReport(params);
            logger.info("自动生成商户报表&操作报表：" + params);
        } else {
            logger.info("已经存在报表：" + params);
        }
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

    public void batchQueryForRecharge(String date) {
        if (StringUtils.isEmpty(date)) {
            date = DateUtils.dateTime(DateUtils.getBeforeDay(new Date()),DateUtils.YYYYMMDD);
        }
        TransVo transVo = new TransVo();
        transVo.setJourno(IdGen.getJourno());
        transVo.setStartNumber("1000000001");
        transVo.setEndNumber(accountMapper.selectMacAccount().getPersonAccount());
        transVo.setStartTime(date);
        transVo.setEndTime(date);
        transVo.setContLast("");
        ReceiveFromBankInfo receiveFromBankInfo = TransOfABC.transCommMsg(Constants.BANK_QUERY_CODE, transVo, new ReceiveFromBankInfo());
        if (receiveFromBankInfo.getResponseCode().equals("0000") && StringUtils.isNotEmpty(receiveFromBankInfo.getFileName())) {
            String[] fileNames = StringUtils.split(receiveFromBankInfo.getFileName(), "@");
            for (String fileName : fileNames) {
                File file = new File(RuoYiConfig.getBankFile() + fileName);
                try {
                    List<String> strings = FileUtils.readLines(file, "UTF-8");
                    dealStrings(strings);
                } catch (IOException e) {
                    logger.info("读取银行文件异常", e);
                }
            }
        }
    }

    private void dealStrings(List<String> strings) {
        if (StringUtils.isNotEmpty(strings)) {
            for (String string : strings) {
                String[] split = StringUtils.splitPreserveAllTokens(string, "\\|");
                personService.bankBatchRecharge(split);
            }
        }
    }


    public void clearAlreadyCost() {
        personService.clearAlreadyCost();
    }
}
