package com.information.project.monitor.job.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.information.common.constant.Constants;
import com.information.common.utils.DateUtils;
import com.information.project.business.checkbill.domain.Checkbill;
import com.information.project.business.checkbill.service.ICheckbillService;
import com.information.project.business.settleDate.domain.SettleDate;
import com.information.project.business.settleDate.service.ISettleDateService;
import com.information.project.business.transactionRecord.domain.TransactionRecord;
import com.information.project.business.transactionRecord.service.ITransactionRecordService;

/**
 * 定时任务调度测试
 * 
 * @author LiuNing
 */
@Component("systemTask")
public class SystemTask {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemTask.class);
	@Autowired
	private ITransactionRecordService transactionRecordService;
	@Autowired
	private ICheckbillService checkbillService;
	@Autowired
	private ISettleDateService settleDateService;

	public void ryParams(String params) {
		System.out.println("执行有参方法：" + params);
	}

	public void checkBill() {
		
		SettleDate settleDate = settleDateService.selectSettleDateById(1l);
		int days = DateUtils.differentDaysByMillisecond(new Date(), settleDate.getSettleDate());
		if (days>1) {
			logger.info("对账间隔超过一天:{}天",days);
		}else {
			List<TransactionRecord> list = transactionRecordService.selectTransactionRecordList(new TransactionRecord());
			if (list != null & list.size() > 0) {
				int rechargeNum = list.size();
				BigDecimal rechargeSum = new BigDecimal(0);
				long cardsum = 0l;
				for (TransactionRecord transactionRecord : list) {
					rechargeSum = rechargeSum.add(new BigDecimal(transactionRecord.getAmount()));
					cardsum = cardsum + Long.parseLong(transactionRecord.getBankNumber().substring(
							transactionRecord.getBankNumber().length() - 5, transactionRecord.getBankNumber().length()));
				}
				Checkbill checkbill = new Checkbill();
				checkbill.setCode("1001");
				checkbill.setBankCode("3051");
				checkbill.setCheckDate(DateUtils.dateTimeNow("yyyyMMdd"));
				checkbill.setRechargeNum(String.valueOf(rechargeNum));
				checkbill.setRechargeSum(rechargeSum.toString());
				checkbill.setRechargeCardSum(String.valueOf(cardsum));
				checkbill.setCloseNum("0");
				checkbill.setCloseSum("0");
				checkbill.setCloseCardSum("0");
				checkbill.setCorrectionNum("0");
				checkbill.setCorrectionSum("0");
				checkbill.setCorrectionCardSum("0");
				checkbill.setMerchantNum("0");
				checkbill.setMerchantSum("0");
				checkbill.setMerchantCardSum("0");
				checkbill.setStatus(Constants.BANK_UNCHECK);
				checkbillService.insertCheckbill(checkbill);
			}
		}
	}

}
