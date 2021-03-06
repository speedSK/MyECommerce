package com.ruoyi.project.bank;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.xml.XmlUtils;
import com.ruoyi.project.bank.domain.ReceiveFromBankInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.SysConfigUtil;
import com.ruoyi.project.bank.domain.TransVo;

public class TransOfABC {
	
	private static Logger logger = LoggerFactory.getLogger(TransOfABC.class);

	private static String serverIp = SysConfigUtil.getNodeValue("bank.server.IP");
	private static String port = SysConfigUtil.getNodeValue("bank.server.port");
	private static String encodeType = "utf-8";


	public static synchronized ReceiveFromBankInfo transCommMsg(String txCode, TransVo vo, ReceiveFromBankInfo receiveFromBankInfo) {
		String sendPackage = "";

		try {
			Socket socket = new Socket(serverIp, Integer.parseInt(port));
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
			outputstream.close();
			socket.close();
			if (Constants.BANK_OPEN_CODE.equals(txCode)) {
				receiveFromBankInfo = dealMsgCMLT40(buf, receiveFromBankInfo);
			} else if (Constants.BANK_QUERY_CODE.equals(txCode)) {
				receiveFromBankInfo = dealMsgCQRD01(buf, vo, receiveFromBankInfo);
			}
			return receiveFromBankInfo;
		} catch (Exception e) {
			logger.error("通讯失败！", e);
			return null;
		}
	}

	private static String createPkgCMLT40(TransVo vo) {
		String addXml = XmlUtils.createAddXml(vo);
		String pkgLength = "0" + getRealString(3, getPackageLen(addXml), 6);
		return pkgLength + addXml;
	}

	private static String createPkgCQRD01(TransVo vo) {
		String queryXml = XmlUtils.createQueryXml(vo);
		String pkgLength = "0" + getRealString(3, getPackageLen(queryXml), 6);
		return pkgLength + queryXml;
	}

	private static ReceiveFromBankInfo dealMsgCMLT40(byte[] buf, ReceiveFromBankInfo receiveFromBankInfo) {
		String str = new String(buf);
		logger.info("dealMsgCMLT40:" + str);
		String xml = StringUtils.substring(str, 7).trim();
		Map<String, String> map = XmlUtils.readStringXmlOut(xml);
		String responseCode = map.get("RespCode");
		receiveFromBankInfo.setResponseCode(responseCode);
		if (!responseCode.equals("0000")) {
			receiveFromBankInfo.setResponseInfo(map.get("RespInfo"));
			logger.info("增加账簿号错误编码{}，错误信息{}:", responseCode, map.get("RespInfo"));
		}
		return receiveFromBankInfo;
	}

	private static ReceiveFromBankInfo dealMsgCQRD01(byte[] buf, TransVo vo, ReceiveFromBankInfo receiveFromBankInfo) {
		String str = new String(buf);
		logger.info("dealMsgCQRD01:" + str);
		String xml = StringUtils.substring(str, 7).trim();
		Map<String, String> map = XmlUtils.readStringXmlOut(xml);
		String responseCode = map.get("RespCode");
		logger.info("RespCode=" + responseCode);
		receiveFromBankInfo.setResponseCode(responseCode);
		if (responseCode.equals("0000")) {
			receiveFromBankInfo.setFileName(receiveFromBankInfo.getFileName() + "@" + map.get("BatchFileName"));
			String contFlag = map.get("ContFlag");
			if (contFlag.equals("1")) {
				vo.setContLast(map.get("ContLast"));
				return transCommMsg(Constants.BANK_QUERY_CODE, vo, receiveFromBankInfo);
			}
		} else {
			receiveFromBankInfo.setResponseInfo(map.get("RespInfo"));
			logger.info("查询余额错误编码{}，错误信息{}:", responseCode, map.get("RespInfo"));
		}
		return receiveFromBankInfo;
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

}
