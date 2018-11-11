package com.ruoyi.project.monitor.job.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.project.bank.service.CheckUpAccountABCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.bank.TransOfABC;
import com.ruoyi.project.bank.domain.TransVo;
import com.ruoyi.project.business.checkbill.domain.Checkbill;
import com.ruoyi.project.business.checkbill.service.ICheckbillService;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import com.ruoyi.project.business.settleDate.domain.SettleDate;
import com.ruoyi.project.business.settleDate.service.ISettleDateService;
import com.ruoyi.project.business.transactionRecord.domain.TransactionRecord;
import com.ruoyi.project.business.transactionRecord.service.ITransactionRecordService;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask {

    private static final Logger logger = LoggerFactory.getLogger(RyTask.class);
    @Autowired
    private CheckUpAccountABCService checkUpAccountABCService;
    @Autowired
    private ITransactionRecordService transactionRecordService;
    @Autowired
    private ICheckbillService checkbillService;
    @Autowired
    private ISettleDateService settleDateService;
    @Autowired
    private IPersonService personService;

    public void ryParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void recharge() {
        Person p = new Person();
        p.setStatus(Constants.STATUS_ACTIVE);
        List<Person> personList = personService.selectPersonList(p);
        for (Person person : personList) {
            String txamt = "";
            TransVo vo = new TransVo();
            vo.setYktTxcode("3021");
            vo.setBankTxcode("YKT01");
            vo.setYktNo(person.getNumber());
            vo.setBankCardNo(person.getBankCardNumber());
            String queryBalance = TransOfABC.transCommMsg("3021", vo);
            String[] balanceArray = queryBalance.split("\\|");
            if (StringUtils.isNotEmpty(balanceArray[0]) && balanceArray[0].equals("000000")) {
                txamt = balanceArray[2];
            } else {
                continue;
            }
            vo.setYktTxcode("3011");
            vo.setBankTxcode("YKT03");
            vo.setTxamt(txamt);
            vo.setYktJourno("");
            vo.setUsername(person.getName());
            vo.setIdserial2(person.getIdcard());
            String transResult = TransOfABC.transCommMsg("3011", vo);
            String[] transArray = transResult.split("\\|");
            if (StringUtils.isNotEmpty(transArray[0]) && transArray[0].equals("000000")) {
                person.setBalance(person.getBalance().add(new BigDecimal(StringUtils.fen2Yuan(txamt))));
                personService.updatePerson(person);
            }
        }
    }

    public void checkBill() {

        SettleDate settleDate = settleDateService.selectSettleDateById(1l);
        int days = DateUtils.differentDaysByMillisecond(new Date(), settleDate.getSettleDate());
        if (days > 1) {
            logger.info("对账间隔超过一天:{}天", days);
        } else {
            List<TransactionRecord> list = transactionRecordService.selectTransactionRecordList(new TransactionRecord());
            if (list != null & list.size() > 0) {
                int rechargeNum = list.size();
                BigDecimal rechargeSum = new BigDecimal(0);
                long cardsum = 0l;
                for (TransactionRecord transactionRecord : list) {
                    rechargeSum = rechargeSum.add(new BigDecimal(transactionRecord.getAmount()));
                    cardsum = cardsum + Long.parseLong(transactionRecord.getBankNumber().substring(
                            transactionRecord.getBankNumber().length() - 5));
                }
                Checkbill checkbill = new Checkbill();
                checkbill.setCode("3051");
                checkbill.setBankCode("YKT08");
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
                TransVo vo = new TransVo();
                vo.setYktTxcode("3051");
                vo.setBankTxcode("YKT08");
                vo.setTxdate(checkbill.getCheckDate());
                vo.setQcTotal(checkbill.getRechargeNum());
                vo.setQcTotalAmt(checkbill.getRechargeSum());
                vo.setCzTotal(checkbill.getCorrectionNum());
                vo.setCzTotalAmt(checkbill.getCorrectionSum());
                vo.setXhTotal(checkbill.getCloseNum());
                vo.setXhTotalAmt(checkbill.getCloseSum());
                vo.setShTotal(checkbill.getMerchantNum());
                vo.setShTotalAmt(checkbill.getMerchantSum());
                vo.setCzBankCdNoTotal(checkbill.getCorrectionCardSum());
                vo.setQcBankCdNoTotal(checkbill.getRechargeCardSum());
                vo.setXhBankCdNoTotal(checkbill.getCloseCardSum());
                vo.setJsBankCdNoTotal(checkbill.getMerchantCardSum());
                String checkBillStr = TransOfABC.transCommMsg("3051", vo);
                String[] checkBillArray = checkBillStr.split("\\|");
                if (StringUtils.isNotEmpty(checkBillArray[0]) && checkBillArray[0].equals("000000")) {
                    checkbill.setStatus(Constants.BANK_CHECKED);
                } else {
                    checkbill.setStatus(Constants.BANK_CHECK_fAIL);
                }
                checkbill.setReturnCode(checkBillArray[0]);
                checkbill.setReturnMessage(checkBillArray[1]);
                checkbillService.updateCheckbill(checkbill);
            }
        }
    }

    public void getBillDetail() {
        String checkDate = DateUtils.parseDateToStr("yyyyMMdd", DateUtils.getBeforeDay(new Date()));
        Checkbill checkbill = new Checkbill();
        checkbill.setCheckDate(checkDate);
        List<Checkbill> check = checkbillService.selectCheckbillList(checkbill);
        if (StringUtils.isNotEmpty(check) && check.size() > 0) {
            Checkbill bill = check.get(0);
            if (bill.getStatus().equals(Constants.BANK_CHECK_fAIL)) {
                TransVo vo = new TransVo();
                vo.setYktTxcode("3071");
                vo.setBankTxcode("YKT10");
                String result = TransOfABC.transCommMsg("3071", vo);
                if (StringUtils.isEmpty(result) && result.equals("000000")) {
                    checkbill.setStatus(Constants.BANK_CHECK_DETAIL);
                    checkbillService.updateCheckbill(checkbill);
                }
            }
        }
    }

    public void checkBillDetail() {
        try {
            String checkDate = DateUtils.parseDateToStr("yyyyMMdd", DateUtils.getBeforeDay(new Date()));
            Checkbill checkbill = new Checkbill();
            checkbill.setCheckDate(checkDate);
            List<Checkbill> check = checkbillService.selectCheckbillList(checkbill);
            if (StringUtils.isNotEmpty(check) && check.size() > 0) {
                Checkbill bill = check.get(0);
                if (bill.getStatus().equals(Constants.BANK_CHECK_DETAIL)) {
                    checkUpAccountABCService.saveCheckUpAccount(checkDate);
                    checkUpAccountABCService.checkSelfRecordAndFileRecord(checkDate);
                    checkUpAccountABCService.checkFileRecordAndSelfRecord(checkDate);
                }
            }
        } catch (Exception e) {
            logger.error("银行详情对账失败", e);
        }

    }



}
