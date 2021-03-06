package com.ruoyi.project.bank.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.SysConfigUtil;
import com.ruoyi.project.business.checkdetail.domain.Checkdetail;
import com.ruoyi.project.business.checkdetail.mapper.CheckdetailMapper;
import com.ruoyi.project.business.transactionRecord.domain.TransactionRecord;
import com.ruoyi.project.business.transactionRecord.mapper.TransactionRecordMapper;

@Service
public class CheckUpAccountABCService {

    public static Logger logger = LoggerFactory.getLogger(CheckUpAccountABCService.class);
//    private static String ftpIp = SysConfigUtil.getNodeValue("check.ftp.address");
//    private static String ftpUserName = SysConfigUtil.getNodeValue("check.ftp.username");
//    private static String ftpPassword = SysConfigUtil.getNodeValue("check.ftp.password");
//    private static int port = Integer.parseInt(SysConfigUtil.getNodeValue("check.ftp.port"));
    //	private static String ftpPath = SysConfigUtil.getNodeValue("ftpPath");
    private static FTPClient ftpClient = null;
    @Autowired
    private CheckdetailMapper checkdetailMapper;
    @Autowired
    private TransactionRecordMapper transactionRecordMapper;


    public static boolean connectFtpServer() {
//        try {
//            ftpClient.connect(ftpIp, port);
//            ftpClient.login(ftpUserName, ftpPassword);
//            return true;
//        } catch (SocketException se) {
//            logger.error("ftpSocket连接失败", se);
//            return false;
//        } catch (IOException ie) {
//            logger.error("ftpIO连接失败", ie);
//            return false;
//        }
        return false;
    }

    public static void closeConnectFtpServer() {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException ie) {
                logger.error("ftp关闭失败", ie);
            }
        }

    }

    @Transactional
    public String saveCheckUpAccount(String date) throws Exception {
        ftpClient = new FTPClient();
        String result = "";
        if (connectFtpServer()) {
            InputStream ins = null;
            if (date.equals("")) {
                date = StringUtils.getDateBefore();
            }

            String fileName = "ABC_300000025532_qc_" + date + ".txt";
            ins = ftpClient.retrieveFileStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "gbk"));
            String readLine = null;
            Checkdetail checkdetail = new Checkdetail();
            Checkdetail detail = null;
            checkdetail.setTransDate(date);
            List<Checkdetail> detailList = checkdetailMapper.selectCheckdetailList(checkdetail);
            int i;
            if (!detailList.isEmpty()) {
                for (i = 0; i < detailList.size(); ++i) {
                    detail = detailList.get(i);
                    checkdetailMapper.deleteCheckdetailById(detail.getId());
                }
            }

            i = 0;

            while ((readLine = reader.readLine()) != null) {
                ++i;
                if (i > 1) {
                    String journo = readLine.substring(0, 32).trim();
                    String bankJourno = readLine.substring(32, 64).trim();
                    String txdate = readLine.substring(64, 72).trim();
                    String number = readLine.substring(72, 92).trim();
                    String userType = readLine.substring(92, 93).trim();
                    String txamt = readLine.substring(93, 110).trim();
                    detail = new Checkdetail();
                    detail.setIdserial(journo);
                    List<Checkdetail> list = checkdetailMapper.selectCheckdetailList(detail);
                    if (list.size() <= 0) {
                        detail.setBankIdserial(bankJourno);
                        detail.setTransDate(txdate);
                        detail.setNumber(number);
                        detail.setAmount(txamt);
                        detail.setUserType(userType);
                        checkdetailMapper.insertCheckdetail(detail);
                    }
                }
            }

            ins.close();
            ftpClient.completePendingCommand();
            reader.close();
            result = "0";
        } else {
            result = "-1";
        }

        closeConnectFtpServer();
        return result;
    }

    @Transactional
    public String checkSelfRecordAndFileRecord(String transDate) {
        TransactionRecord rechargeRecord = new TransactionRecord();
        rechargeRecord.setTransDate(transDate);
        rechargeRecord.setCode("1001");
        rechargeRecord.setReturnCode("000000");
        List<TransactionRecord> rechargeList = transactionRecordMapper.selectTransactionRecordList(rechargeRecord);
        if (rechargeList != null) {
            for (int i = 0; i < rechargeList.size(); ++i) {
                TransactionRecord record = rechargeList.get(i);
                Checkdetail detail = null;
                Checkdetail checkdetail = new Checkdetail();
                checkdetail.setTransDate(record.getTransDate());
                checkdetail.setIdserial(record.getTransIdserial());
                checkdetail.setAmount(record.getAmount());
                List<Checkdetail> fileRecordList = checkdetailMapper.selectCheckdetailList(checkdetail);
                if (fileRecordList.size() == 0) {
                    logger.info("系统多于银行的流水号：{}。", record.getTransIdserial());
                    rechargeRecord = new TransactionRecord();
                    rechargeRecord.setTransIdserial(record.getTransIdserial());
                    List<TransactionRecord> transferList = transactionRecordMapper.selectTransactionRecordList(rechargeRecord);
                    if (transferList.size() == 0) {
                        detail = new Checkdetail();
                        detail.setNumber(record.getUserCode());
                        detail.setIdserial(record.getTransIdserial());
                        detail.setUserType("1");
                        detail.setAmount(record.getAmount());
                        detail.setTransDate(record.getTransDate());
                        detail.setCheckStatus("2");//系统多余银行状态
                        checkdetailMapper.insertCheckdetail(detail);
                    }
                }
            }
        }

        return "";
    }

    @Transactional
    public String checkFileRecordAndSelfRecord(String transDate) {
        Checkdetail detail = new Checkdetail();
        detail.setTransDate(transDate);
        detail.setCheckStatus("1");
        List<Checkdetail> fileRecordList = checkdetailMapper.selectCheckdetailList(detail);
        List<TransactionRecord> rechargeRecords;
        TransactionRecord record = null;
        if (fileRecordList.size() > 0) {
            for (int i = 0; i < fileRecordList.size(); ++i) {
                detail = fileRecordList.get(i);
                record.setTransIdserial(detail.getIdserial());
                record.setUserCode(detail.getNumber());
                record.setAmount(detail.getAmount());
                record.setTransDate(detail.getTransDate());
                record.setCode("1001");
                record.setReturnCode("000000");
                rechargeRecords = transactionRecordMapper.selectTransactionRecordList(record);
                if (rechargeRecords != null && rechargeRecords.size() > 0) {
                    record = rechargeRecords.get(0);
                    record.setStatus("2");//对账完成
                    transactionRecordMapper.updateTransactionRecord(record);
                } else {
                    logger.error("银行多于系统的流水号：{}", detail.getIdserial());
                    record = new TransactionRecord();
                    record.setTransIdserial(detail.getBankIdserial());
                    List<TransactionRecord> checkList = transactionRecordMapper.selectTransactionRecordList(record);
                    if (checkList.size() <= 0) {
                        detail.setCheckStatus("3");//银行多余系统
                        checkdetailMapper.updateCheckdetail(detail);
                    }
                }
            }
        }
        return "";
    }
}