package com.information.project.bank.service;

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
import org.springframework.transaction.annotation.Transactional;

import com.information.common.utils.SysConfigUtil;
import com.information.project.bank.domain.BankCheckdetail;
import com.information.project.bank.domain.BankCheckdetailExample;
import com.information.project.bank.domain.BankCheckdetailExample.Criteria;
import com.information.project.bank.domain.BankRechargeRecord;
import com.information.project.bank.domain.BankRechargeRecordExample;
import com.information.project.bank.mapper.BankCheckdetailMapper;
import com.information.project.bank.mapper.BankRechargeRecordMapper;
import com.information.project.bank.util.StringUtil;

public class CheckUpAccountABCService {
	public static Logger logger = LoggerFactory.getLogger(CheckUpAccountABCService.class.getName());
	private static String ftpIp = SysConfigUtil.getNodeValue("ftpAddress");
	private static String ftpUserName = SysConfigUtil.getNodeValue("ftpUserName");
	private static String ftpPassword = SysConfigUtil.getNodeValue("ftpPassword");
	private static int port = Integer.parseInt(SysConfigUtil.getNodeValue("ftpPort"));
	private static String ftpPath = SysConfigUtil.getNodeValue("ftpPath");
	private static FTPClient ftpClient = null;
	@Autowired
	private BankCheckdetailMapper bankCheckdetailMapper;
	@Autowired
	private BankRechargeRecordMapper bankRechargeRecordMapper;


	public static boolean connectFtpServer() {
		try {
			ftpClient.connect(ftpIp, port);
			ftpClient.login(ftpUserName, ftpPassword);
			return true;
		} catch (SocketException var1) {
			var1.printStackTrace();
			return false;
		} catch (IOException var2) {
			var2.printStackTrace();
			return false;
		}
	}

	public static void closeConnectFtpServer() {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException var1) {
				var1.printStackTrace();
			}
		}

	}

	@Transactional
	public String saveCheckUpAccount(String date) throws Exception {
		ftpClient = new FTPClient();
		String result = "0";
		if (connectFtpServer()) {
			InputStream ins = null;
			if (date.equals("")) {
				date = StringUtil.getDateBefore();
			}

			String fileName = "ABC_300000025532_qc_" + date + ".txt";
			ins = ftpClient.retrieveFileStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "gbk"));
			String readLine = null;
			BankCheckdetailExample ex = new BankCheckdetailExample();
			BankCheckdetail detail = null;
			Criteria cc = ex.createCriteria();
			cc.andTransDateEqualTo(date);
			List<BankCheckdetail> detailList = bankCheckdetailMapper.selectByExample(ex);
			int i;
			if (!detailList.isEmpty()) {
				for (i = 0; i < detailList.size(); ++i) {
					detail = detailList.get(i);
					bankCheckdetailMapper.deleteByPrimaryKey(detail.getId());
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
					detail = new BankCheckdetail();
					detail.setIdserial(journo);
					detail.setBankIdserial(bankJourno);
					detail.setTransDate(txdate);
					detail.setNumber(number);
					detail.setAmount(txamt);
					detail.setUserType(userType);
					BankCheckdetailExample ex1 = new BankCheckdetailExample();
					Criteria cc1 = ex1.createCriteria();
					cc1.andIdserialEqualTo(journo);
					List<BankCheckdetail> list = this.bankCheckdetailMapper.selectByExample(ex1);
					if (list.size() <= 0) {
						this.bankCheckdetailMapper.insertSelective(detail);
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
	public String checkSelfRecordAndFileRecord(String tansDate) {
		BankRechargeRecordExample ex = new BankRechargeRecordExample();
		com.information.project.bank.domain.BankRechargeRecordExample.Criteria cc = ex.createCriteria();
		cc.andTransDateEqualTo(tansDate);
		cc.andCodeEqualTo("1001");
		cc.andReturnCodeEqualTo("000000");
		List<BankRechargeRecord> selfList = bankRechargeRecordMapper.selectByExample(ex);
		if (selfList != null) {
			for (int i = 0; i < selfList.size(); ++i) {
				BankRechargeRecord selfRecord = selfList.get(i);
				BankCheckdetailExample detailEX = new BankCheckdetailExample();
				BankCheckdetail detail = null;
				Criteria detailCC = detailEX.createCriteria();
				detailCC.andTransDateEqualTo(selfRecord.getTransDate());
				detailCC.andIdserialEqualTo(selfRecord.getTransIdserial());
				detailCC.andAmountEqualTo(selfRecord.getAmount());
				List<BankCheckdetail> fileRecordList = bankCheckdetailMapper.selectByExample(detailEX);
				if (fileRecordList.size() == 0) {
					logger.info("系统多于银行的流水号：{}。",selfRecord.getTransIdserial());
					ex = new BankRechargeRecordExample();
					com.information.project.bank.domain.BankRechargeRecordExample.Criteria cc1 = ex.createCriteria();
					cc1.andTransIdserialEqualTo(selfRecord.getTransIdserial());
					List<BankRechargeRecord> transferList = bankRechargeRecordMapper.selectByExample(ex);
					if (transferList.size() == 0) {
						detail = new BankCheckdetail();
						detail.setNumber(selfRecord.getUserCode());
						detail.setIdserial(selfRecord.getTransIdserial());
						detail.setUserType("1");
						detail.setAmount(selfRecord.getAmount());
						detail.setTransDate(selfRecord.getTransDate());
						detail.setCheckStatus("2");//系统多余银行状态
						bankCheckdetailMapper.insertSelective(detail);
					}
				}
			}
		}

		return "";
	}

	@Transactional
	public String checkFileRecordAndSelfRecord(String tansDate) {
		BankCheckdetailExample ex = new BankCheckdetailExample();
		BankCheckdetail detail = null;
		Criteria cc = ex.createCriteria();
		cc.andTransDateEqualTo(tansDate);
		cc.andCheckStatusEqualTo("1");
		List<BankCheckdetail> fileRecordList = bankCheckdetailMapper.selectByExample(ex);
		List<BankRechargeRecord> rechargeRecords;
		BankRechargeRecord record = null;
		if (fileRecordList.size() > 0) {
			for (int i = 0; i < fileRecordList.size(); ++i) {
				detail = fileRecordList.get(i);
				BankRechargeRecordExample rechargeEX = new BankRechargeRecordExample();
				com.information.project.bank.domain.BankRechargeRecordExample.Criteria rechargeCC = rechargeEX.createCriteria();
				rechargeCC.andTransIdserialEqualTo(detail.getIdserial());
				rechargeCC.andUserCodeEqualTo(detail.getNumber());
				rechargeCC.andAmountEqualTo(detail.getAmount());
				rechargeCC.andTransDateEqualTo(detail.getTransDate());
				rechargeCC.andCodeEqualTo("1001");
				rechargeCC.andReturnCodeEqualTo("000000");
				rechargeRecords = bankRechargeRecordMapper.selectByExample(rechargeEX);
				if (rechargeRecords != null && rechargeRecords.size() > 0) {
					record = rechargeRecords.get(0);
					record.setStatus("2");//对账完成
					bankRechargeRecordMapper.updateByPrimaryKeySelective(record);
				} else {
					logger.error("银行多于系统的流水号：{}", detail.getIdserial());
					rechargeEX = new BankRechargeRecordExample();
					com.information.project.bank.domain.BankRechargeRecordExample.Criteria rechargeCC1 = rechargeEX.createCriteria();
					rechargeCC1.andBankIdserialEqualTo(detail.getIdserial());
					List<BankRechargeRecord> checkList = bankRechargeRecordMapper.selectByExample(rechargeEX);
					if (checkList.size() <= 0) {
						detail.setCheckStatus("3");//银行多余系统
						bankCheckdetailMapper.updateByPrimaryKeySelective(detail);
					}
				}
			}
		}
		return "";
	}
}