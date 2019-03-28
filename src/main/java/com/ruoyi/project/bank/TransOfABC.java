package com.ruoyi.project.bank;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.ruoyi.common.constant.Constants;
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
	private static String encodeType = "utf-8";


	public static synchronized String transCommMsg(String txCode, TransVo vo) {
		String backMsg = "";
		String sendPackage = "";

		try {
			Socket socket = new Socket("127.0.0.1", 8801);
//			Socket socket = new Socket(serverIp, Integer.parseInt(port));
			OutputStream outputstream = socket.getOutputStream();
			if (Constants.BANK_OPEN_CODE.equals(txCode)) {
				sendPackage = createPkgCMLT40(vo);
				logger.info("增加子账簿交易\n,报文:{}.", sendPackage);
			} else if (Constants.BANK_QUERY_CODE.equals(txCode)) {
				sendPackage = createPkgCQRD01(vo);
				logger.info("查询子账簿余额交易\n,报文:{}.", sendPackage);
			}

			outputstream.write(sendPackage.getBytes());
			DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
			byte[] buf = new byte[2048];
			datainputstream.read(buf);
			if (Constants.BANK_OPEN_CODE.equals(txCode)) {
				backMsg = dealMsgCMLT40(buf);
			} else if (Constants.BANK_QUERY_CODE.equals(txCode)) {
				backMsg = dealMsgCQRD01(buf, vo);
			}

			outputstream.close();
			socket.close();
			return backMsg;
		} catch (Exception e) {
			logger.error("通讯失败！",e);
			return "ERR01";
		}
	}

	private static String createPkgCMLT40(TransVo vo) {
		String addXml = XmlUtils.createAddXml(vo);
		String pkgLength = "0" + getRealString(1, getPackageLen(addXml), 6);
		return pkgLength + addXml;
	}

	private static String createPkgCQRD01(TransVo vo) {
		String queryXml = XmlUtils.createQueryXml(vo);
		String pkgLength = "0" + getRealString(1, getPackageLen(queryXml), 6);
		return pkgLength + queryXml;
	}

	private static String dealMsgCMLT40(byte[] buf) {
		String str = new String(buf);
		logger.info("dealMsgCMLT40:" + str);
		Map<String, String> map = XmlUtils.readStringXmlOut(str);
		String responseCode = map.get("RespCode");
		if (responseCode.equals("0000")) {
			return responseCode;
		} else {
			logger.info("增加账簿号错误编码{}，错误信息{}:", responseCode, map.get("RespInfo"));
			return responseCode;
		}
	}

	private static String dealMsgCQRD01(byte[] buf, TransVo vo) {
		String str = new String(buf);
		logger.info("dealMsgCQRD01:" + str);
		Map<String, String> map = XmlUtils.readStringXmlOut(str);
		String responseCode = map.get("RespCode");
		if (responseCode.equals("0000")) {
			String contFlag = map.get("ContFlag");
			if (contFlag.equals("1")) {
				vo.setContLast(map.get("ContLast"));
				transCommMsg(Constants.BANK_QUERY_CODE, vo);
			} else {
				return responseCode + "@" + map.get("FileFlag");
			}
		} else {
			logger.info("查询余额错误编码{}，错误信息{}:", responseCode, map.get("RespInfo"));
		}
		return responseCode + "@" + map.get("FileFlag");
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
