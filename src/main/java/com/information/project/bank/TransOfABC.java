package com.information.project.bank;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.information.common.utils.StringUtils;
import com.information.common.utils.SysConfigUtil;
import com.information.common.utils.spring.SpringUtils;
import com.information.project.bank.domain.ReceiveFromBankInfo;
import com.information.project.bank.domain.TransVo;
import com.information.project.bank.service.BankServiceImpl;
import com.information.project.bank.service.IBankService;
import com.information.project.business.person.domain.Person;

public class TransOfABC {
	
	private static Logger logger = LoggerFactory.getLogger(TransOfABC.class);
	private static String serverIp = SysConfigUtil.getNodeValue("serverIp");
	private static String port = SysConfigUtil.getNodeValue("serverPort");
	private static String sysFlag = SysConfigUtil.getNodeValue("systemFlag");
	private static String encodeType = "gbk";
	//通过springApplicationContextAware获取bean
	private static IBankService bankService = (IBankService) SpringUtils.getBean(BankServiceImpl.class);
	//通过postContruct注入service
//	public static TransOfABC transOfABC;
//	@PostConstruct
//	public void init() {
//		transOfABC=this;
//		transOfABC.bankService=this.bankService;
//	}
	public static synchronized String transCommMsg(String txCode, TransVo vo) throws Exception {
		String backMsg = "";
		String sendPackage = "";

		try {
			Socket socket = new Socket(serverIp, Integer.parseInt(port));
			OutputStream outputstream = socket.getOutputStream();
			if ("3002".equals(txCode)) {
				sendPackage = createPkg3002(vo);
				logger.info("解约发送报文3002交易\n,报文:{}.", sendPackage);
			} else if ("3011".equals(txCode)) {
				sendPackage = createPkg3011(vo);
				logger.info("转账发送报文3011交易\n,报文:{}.", sendPackage);
			} else if ("3021".equals(txCode)) {
				sendPackage = createPkg3021(vo);
				logger.info("查询余额发送报文3021交易\n,报文:{}.", sendPackage);
			} else if ("3051".equals(txCode)) {
				sendPackage = createPkg3051(vo);
				logger.info("总额对账报文3051交易\n,报文:{}.", sendPackage);
			} else if ("3071".equals(txCode)) {
				sendPackage = createPkg3071(vo);
				logger.info("明细对账报文3071交易\n,报文:{}.", sendPackage);
			}

			outputstream.write(sendPackage.getBytes());
			DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
			byte[] buf = new byte[1024];
			datainputstream.read(buf);
			if ("3002".equals(txCode)) {
				backMsg = dealMsg3002(buf);
			} else if ("3021".equals(txCode)) {
				backMsg = dealMsg3021(buf);
			} else if ("3011".equals(txCode)) {
				backMsg = dealMsg3011(buf);
			} else if ("3051".equals(txCode)) {
				backMsg = dealMsg3051(buf);
			} else if ("3071".equals(txCode)) {
				backMsg = dealMsg3071(buf);
			}

			outputstream.close();
			socket.close();
			return backMsg;
		} catch (Exception e) {
			logger.error("通讯失败！",e);
			return "ERR01";
		}
	}

	public static String createPkg3002(TransVo vo) throws Exception {
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

	public static String createPkg3011(TransVo vo) throws Exception {
		String pckbdy = "";
		String yktTxcode = getRealString(3, "3011", 4);
		String bankTxcode = getRealString(3, "YKT03", 5);
		String txdate = getRealString(3, getCurrentDate(), 8);
		String yktJourno = getRealString(3, vo.getYktJourno(), 32);
		String yktNo = getRealString(3, vo.getYktNo(), 20);
		String username = StringUtils.formatStr(vo.getUsername(), 20, encodeType);
		String idserial2 = getRealString(3, vo.getIdserial2(), 18);
		String bankCdNo = getRealString(3, vo.getBankCardNo(), 32);
		String txamt = getRealString(3, vo.getTxamt(), 17);
		String flag = getRealString(3, sysFlag, 15);
		pckbdy = yktTxcode + bankTxcode + txdate + yktJourno + yktNo + username + idserial2 + bankCdNo + txamt + flag;
		String pkgLength = getRealString(1, getPackageLen(pckbdy), 4);
		return pkgLength + pckbdy;
	}

	public static String createPkg3021(TransVo vo) throws Exception {
		String pckbdy = "";
		String yktTxcode = getRealString(3, "3021", 4);
		String bankTxcode = getRealString(3, "YKT01", 5);
		String yktno = getRealString(3, vo.getYktNo(), 20);
		String bankCdNo = getRealString(3, vo.getBankCardNo(), 32);
		String flag = getRealString(3, sysFlag, 15);
		pckbdy = yktTxcode + bankTxcode + yktno + bankCdNo + flag;
		String pkgLength = getRealString(1, getPackageLen(pckbdy), 4);
		return pkgLength + pckbdy;
	}

	public static String createPkg3051(TransVo vo) throws Exception {
		String pckbdy = "";
		String yktTxcode = getRealString(3, "3051", 4);
		String bankTxcode = getRealString(3, "YKT08", 5);
		String txdate = getRealString(3, vo.getTxdate(), 8);
		String qcTotal = getRealString(3, vo.getQcTotal(), 4);
		String qcTotalAmt = getRealString(3, vo.getQcTotalAmt(), 17);
		String czTotal = getRealString(3, vo.getCzTotal(), 4);
		String czTotalAmt = getRealString(3, vo.getCzTotalAmt(), 17);
		String xhTotal = getRealString(3, vo.getXhTotal(), 4);
		String xhTotalAmt = getRealString(3, vo.getXhTotalAmt(), 17);
		String shTotal = getRealString(3, vo.getShTotal(), 4);
		String shTotalAmt = getRealString(3, vo.getShTotalAmt(), 17);
		String qcBankCdNoTotal = getRealString(3, vo.getQcBankCdNoTotal(), 17);
		String czBankCdNoTotal = getRealString(3, vo.getCzBankCdNoTotal(), 17);
		String xhBankCdNoTotal = getRealString(3, vo.getXhBankCdNoTotal(), 17);
		String jsBankCdNoTotal = getRealString(3, vo.getJsBankCdNoTotal(), 17);
		String flag = getRealString(3, sysFlag, 15);
		pckbdy = yktTxcode + bankTxcode + txdate + qcTotal + qcTotalAmt + czTotal + czTotalAmt + xhTotal + xhTotalAmt
				+ shTotal + shTotalAmt + qcBankCdNoTotal + czBankCdNoTotal + xhBankCdNoTotal + jsBankCdNoTotal + flag;
		String pkgLength = getRealString(1, getPackageLen(pckbdy), 4);
		return pkgLength + pckbdy;
	}

	public static String createPkg3071(TransVo vo) throws Exception {
		String pckbdy = "";
		String yktTxcode = getRealString(3, "3", 4);
		String bankTxcode = getRealString(3, "YKT10", 5);
		String txdate = getRealString(3, vo.getTxdate(), 8);
		String ftpIP = getRealString(3, vo.getFtpIp(), 15);
		String ftpPort = getRealString(3, vo.getFtpPort(), 5);
		String ftpUser = getRealString(3, vo.getFtpUser(), 20);
		String ftpPwd = getRealString(3, vo.getFtpPwd(), 20);
		String ftpPath = getRealString(3, vo.getFtpPath(), 100);
		String flag = getRealString(3, sysFlag, 15);
		pckbdy = yktTxcode + bankTxcode + txdate + ftpIP + ftpPort + ftpUser + ftpPwd + ftpPath + flag;
		String pkgLength = getRealString(1, getPackageLen(pckbdy), 4);
		return pkgLength + pckbdy;
	}

	public static String dealMsg3002(byte[] buf) throws UnsupportedEncodingException {
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

	public static String dealMsg3011(byte[] buf) throws UnsupportedEncodingException {
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

	public static String dealMsg3021(byte[] buf) throws UnsupportedEncodingException {
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

	public static String dealMsg3051(byte[] buf) throws UnsupportedEncodingException {
		logger.info("dealMsg3051:" + new String(buf));
		String responseCode = new String(buf, 4, 6);
		String responseMsg = new String(buf, 10, 34, encodeType);
		if (responseCode.equals("000000")) {
			logger.info("处理总额对账文件成功报文体3051交易\n报文:{},交易时间{},响应码{},相应信息{}.", new String(buf),getCurrentDateTime() ,responseCode,responseMsg);
			return responseCode;
		} else if (responseCode.equals("L99999")) {
			logger.info("处理总额对账文件系统忙报文体3051交易\n报文:{},交易时间{},响应码{},相应信息{}.", new String(buf),getCurrentDateTime() ,responseCode,responseMsg);
			return responseCode;
		} else {
			logger.info("处理总额对账文件错误报文体3051交易\n报文:{},交易时间{},响应码{},相应信息{}.", new String(buf),getCurrentDateTime() ,responseCode,responseMsg);
			return responseCode;
		}
	}

	public static String dealMsg3071(byte[] buf) throws UnsupportedEncodingException {
		logger.info("dealMsg3071:" + new String(buf));
		String responseCode = new String(buf, 4, 6);
		String responseMsg = new String(buf, 10, 34, encodeType);
		if (responseCode.equals("000000")) {
			logger.info("处理明细对账文件成功报文体3071交易\n报文:{},交易时间{},响应码{},相应信息{}.", new String(buf),getCurrentDateTime() ,responseCode,responseMsg);
			return responseCode;
		} else {
			logger.info("处理明细对账文件失败报文体3071交易\n报文:{},交易时间{},响应码{},相应信息{}.", new String(buf),getCurrentDateTime() ,responseCode,responseMsg);
			return responseCode;
		}
	}

	public static String getRealString(int type, String oldString, int length) {
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

	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

	public static String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	public static String getPackageLen(String packageStr) {
		int length = packageStr.getBytes().length;
		return String.valueOf(length);
	}

	public static byte[] createPckUserSign(ReceiveFromBankInfo receiveFromBankInfo) throws Exception {
		String resMessage = StringUtils.formatStr(receiveFromBankInfo.getResponsename(), 34, encodeType);
		String packgBody = receiveFromBankInfo.getResponsecode() + resMessage;
		String packgTopLen = getRealString(1, getPackageLen(packgBody), 4);
		String packgTop = packgTopLen + packgBody;
		logger.info("createPckUserSign,3031发送报文:{}.",packgTop);
		return packgTop.getBytes();
	}

	public static byte[] createPckSign(ReceiveFromBankInfo receiveFromBankInfo) throws Exception {
		String resMessage = StringUtils.formatStr(receiveFromBankInfo.getResponsename(), 34, encodeType);
		String packgBody = receiveFromBankInfo.getResponsecode() + resMessage;
		String packgTopLen = getRealString(1, getPackageLen(packgBody), 4);
		String packgTop = packgTopLen + packgBody;
		logger.info("createPckUserSign,3001发送报文:{}.",packgTop);
		return packgTop.getBytes();
	}

	public static byte[] queryUserInfoSign(byte[] buf) throws Exception {
		logger.info("queryUserInfoSign接收报文:" + new String(buf));
		String idserial = new String(buf, 15, 18);
		Person user = new Person();
		user.setNumber(idserial);
		logger.info("编号：" + idserial + "。");
		try {
			ReceiveFromBankInfo receiveFromBankInfo = bankService.queryUserInfo4Bank(user);
			byte[] pckBody = createPckUserSign(receiveFromBankInfo);
			if ("000000".equals(receiveFromBankInfo.getResponsecode())) {
				logger.info("用户:" + idserial + ",发起信息查询成功");
			} else {
				logger.info("用户:" + idserial + ",发起签约，签约失败");
			}

			return pckBody;
		} catch (Exception var6) {
			var6.printStackTrace();
			return null;
		}
	}

	public static byte[] querySign(byte[] buf) throws Exception {
		logger.info("querySign接收报文:" + new String(buf));
		String idserial = new String(buf, 56, 18);
		String idserial2 = new String(buf, 94, 18);
		String bankcdno = new String(buf, 123, 32);
		logger.info("idserial:" + idserial + ",idserial2:" + idserial2 + ",bankcdno:" + bankcdno);
		Person person = new Person();
		person.setNumber(idserial);
		person.setIdNumber(idserial2);
		person.setBankCardNumber(bankcdno);
		ReceiveFromBankInfo receiveFromBankInfo = bankService.queryUserSign(person);
		byte[] pckBody = createPckSign(receiveFromBankInfo);
		if ("000000".equals(receiveFromBankInfo.getResponsecode())) {
			logger.info("用户:" + idserial + ",发起签约，签约成功");
		} else {
			logger.info("用户:" + idserial + ",发起签约，签约失败");
		}

		return pckBody;
	}
}
