package com.information.project.monitor.job.task;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.information.project.business.checkbill.domain.Checkbill;
import com.information.project.business.checkbill.service.ICheckbillService;
import com.information.project.business.transactionRecord.domain.TransactionRecord;
import com.information.project.business.transactionRecord.service.ITransactionRecordService;

/**
 * 定时任务调度测试
 * 
 * @author LiuNing
 */
@Component("systemTask")
public class SystemTask {
	@Autowired
	private ITransactionRecordService transactionRecordService;
	@Autowired
	private ICheckbillService checkbillService;

	public void ryParams(String params) {
		System.out.println("执行有参方法：" + params);
	}

	public void ryNoParams() {

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
			checkbill.setCheckDate("20180813");
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
			checkbillService.insertCheckbill(checkbill);
		}
		
		System.out.println(list.size());
	}

	public static void main(String[] args) {
		String str = "123456";
		System.out.println(Long.parseLong(str.substring(str.length() - 5, str.length())));
	}
}
