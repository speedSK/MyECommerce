package com.ruoyi.project.bank;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ruoyi.common.utils.xml.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.SysConfigUtil;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.bank.domain.ReceiveFromBankInfo;
import com.ruoyi.project.bank.domain.TransVo;
import com.ruoyi.project.bank.service.BankServiceImpl;
import com.ruoyi.project.bank.service.IBankService;
import com.ruoyi.project.business.person.domain.Person;

public class TransOfABC {
	
	private static Logger logger = LoggerFactory.getLogger(TransOfABC.class);
//	private static String serverIp = SysConfigUtil.getNodeValue("bank.server.IP");
//	private static String port = SysConfigUtil.getNodeValue("bank.server.port");
//	private static String sysFlag = SysConfigUtil.getNodeValue("bank.server.flag");
	private static String encodeType = "utf-8";
//	//通过springApplicationContextAware获取bean
//	private static IBankService bankService = (IBankService) SpringUtils.getBean(BankServiceImpl.class);

	public static synchronized String transCommMsg(String txCode, TransVo vo) {
		String backMsg = "";
		String sendPackage = "";

		try {
			Socket socket = new Socket("127.0.0.1", 8801);
//			Socket socket = new Socket(serverIp, Integer.parseInt(port));
			OutputStream outputstream = socket.getOutputStream();
			if ("3002".equals(txCode)) {
				sendPackage = XmlUtils.createAddXml();
				logger.info("增加子账簿交易\n,报文:{}.", sendPackage);
			} else if ("3011".equals(txCode)) {
				sendPackage = XmlUtils.createQueryXml();
				logger.info("查询子账簿余额交易\n,报文:{}.", sendPackage);
			}

			outputstream.write(sendPackage.getBytes());
			DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
			byte[] buf = new byte[1024];
			datainputstream.read(buf);
			if ("3002".equals(txCode)) {
				backMsg = dealMsg3002(buf);
			} else if ("3021".equals(txCode)) {
				backMsg = dealMsg3021(buf);
			}

			outputstream.close();
			socket.close();
			return backMsg;
		} catch (Exception e) {
			logger.error("通讯失败！",e);
			return "ERR01";
		}
	}

	private static String createPkg3002(TransVo vo) throws Exception {
		String pckbdy = "";
		String yktTxcode = getRealString(3, "3002", 4);
		String bankTxcode = getRealString(3, "YKT02", 5);
		String txdate = getRealString(3, getCurrentDate(), 8);
		String yktJourno = getRealString(3, vo.getYktJourno(), 32);
		String userType = getRealString(3, vo.getUserType(), 1);
		String yktNo = getRealString(3, vo.getYktNo(), 20);
		String username = StringUtils.formatStr(vo.getUsername(), 20, encodeType);
		String idserial2 = getRealString(3, vo.getIdserial2(), 18);
		String telphone = vo.getTelphone();
		String bankCdNo = getRealString(3, vo.getBankCardNo(), 32);
		String cardBal = getRealString(3, vo.getCardBal(), 17);
		String yktflag = getRealString(3, vo.getYktflag(), 15);
		pckbdy = yktTxcode + bankTxcode + txdate + yktJourno + userType + yktNo + username + idserial2 + telphone
				+ bankCdNo + cardBal + yktflag;
		String pkgLength = getRealString(1, getPackageLen(pckbdy), 4);
		return pkgLength + pckbdy;
	}

	private static String createPkg3011(TransVo vo) throws Exception {
		String pckbdy = "";
		String yktTxcode = getRealString(3, "3011", 4);
		String bankTxcode = getRealString(3, "YKT03", 5);
		String txdate = getRealString(3, getCurrentDate(), 8);
		String yktJourno = getRealString(3, "180929"+vo.getYktJourno(), 32);
		String yktNo = getRealString(3, vo.getYktNo(), 20);
		String username = StringUtils.formatStr(vo.getUsername(), 20, encodeType);
		String idserial2 = getRealString(3, vo.getIdserial2(), 18);
		String bankCdNo = getRealString(3, vo.getBankCardNo(), 32);
		String txamt = getRealString(3, vo.getTxamt(), 17);
//		String flag = getRealString(3, sysFlag, 15);
//		pckbdy = yktTxcode + bankTxcode + txdate + yktJourno + yktNo + username + idserial2 + bankCdNo + txamt + flag;
		String pkgLength = getRealString(1, getPackageLen(pckbdy), 4);
		return pkgLength + pckbdy;
	}

	private static String dealMsg3002(byte[] buf) throws UnsupportedEncodingException {
		logger.info("dealMsg3002:" + new String(buf));
		String txdate = new String(buf, 4, 8);
		String journo = new String(buf, 12, 32);
		String responseCode = new String(buf, 44, 6);
		String responseMsg = new String(buf, 50, 34, encodeType);
		if (responseCode.equals("000000")) {
			logger.info("处理解约成功报文体3002交易\n报文:{},交易时间{},业务流水{},响应码{},相应信息{}.", new String(buf),txdate,journo,responseCode,responseMsg);
			return responseCode;
		} else {
			logger.info("处理解约失败报文体3002交易\n报文:{},交易时间{},业务流水{},响应码{},相应信息{}.", new String(buf),txdate,journo,responseCode,responseMsg);
			return responseCode;
		}
	}

	private static String dealMsg3011(byte[] buf) throws UnsupportedEncodingException {
		logger.info("dealMsg3011:" + new String(buf));
		String txdate = new String(buf, 4, 8);
		String journo = new String(buf, 12, 32);
		String responseCode = new String(buf, 44, 6);
		String responseMsg = new String(buf, 50, 34, encodeType);
		if (responseCode.equals("000000")) {
			logger.info("处理圈存转账成功报文体3011交易\n报文:{},交易时间{},业务流水{},响应码{},相应信息{}.", new String(buf),txdate,journo,responseCode,responseMsg);
			return responseCode + "|" + responseMsg;
		} else {
			logger.info("处理圈存转账失败报文体3011交易\n报文:{},交易时间{},业务流水{},响应码{},相应信息{}.", new String(buf),txdate,journo,responseCode,responseMsg);
			return responseCode + "|" + responseMsg;
		}
	}

	private static String dealMsg3021(byte[] buf) throws UnsupportedEncodingException {
		logger.info("dealMsg3021:" + new String(buf));
		String responseCode = new String(buf, 4, 6);
		String responseMsg = new String(buf, 10, 34, encodeType);
		String accbaltemp = new String(buf, 44, 17);
		String accbal = StringUtils.fen2Yuan(accbaltemp.trim());
		if (responseCode.equals("000000")) {
			logger.info("处理查询余额成功报文体3021交易\n报文:{},交易时间{},账户余额{},响应码{},相应信息{}.", new String(buf),getCurrentDateTime(),accbal,responseCode,responseMsg);
			return responseCode + "|" + responseMsg + "|" + accbaltemp;
		} else {
			logger.info("处理查询余额失败报文体3021交易\n报文:{},交易时间{},账户余额{},响应码{},相应信息{}.", new String(buf),getCurrentDateTime(),accbal,responseCode,responseMsg);
			return responseCode + "|" + responseMsg + "|" + accbaltemp;
		}
	}

	private static String getRealString(int type, String oldString, int length) {
		String newString = "";
		String temp = "";
		int i;
		switch (type) {
			case 1 :
				if (oldString.length() < length) {
					for (i = 0; i < length - oldString.length(); ++i) {
						temp = temp + "0";
					}
				}

				newString = temp + oldString;
				break;
			case 2 :
				if (oldString.length() < length) {
					for (i = 0; i < length - oldString.length(); ++i) {
						temp = temp + "0";
					}
				}

				newString = oldString + temp;
				break;
			case 3 :
				if (oldString.length() < length) {
					for (i = 0; i < length - oldString.length(); ++i) {
						temp = temp + " ";
					}
				}

				newString = oldString + temp;
				break;
			case 4 :
				if (oldString.length() < length) {
					for (i = 0; i < length - oldString.length(); ++i) {
						temp = temp + " ";
					}
				}

				newString = temp + oldString;
		}

		return newString;
	}

	private static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

	private static String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	private static String getPackageLen(String packageStr) {
		int length = packageStr.getBytes().length;
		return String.valueOf(length);
	}

	public static void main(String[] args) {
		TransOfABC.transCommMsg("3002", new TransVo());
	}

}
