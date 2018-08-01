package com.information.common.utils.bank.abc.xj;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.runners.Parameterized.UseParametersRunnerFactory;

import com.information.common.utils.StringUtils;

public class Communication {
	
	private final static String SYSTEM_FLAG = "";
	private final static String IP = "127.0.0.1";
	private final static String PORT = "4567";
	
	
	
	
	
	
	public String connection(String txcode,String tradeno,String userNumber,String userName,String IDnumber,String bankCardNumber,String transAmount,String userMobile){
		String backMsg="";
		try {
			Socket socket = new Socket(IP, Integer.parseInt(PORT));
			OutputStream outputstream = socket.getOutputStream();
			
			String sendMsg = null;
			if(txcode.equals("1000")){//查询余额交易
				sendMsg=getBlance(txcode, userNumber, bankCardNumber);
			}else if(txcode.equals("2000")){//转账交易
				sendMsg=recharge(txcode,tradeno, userNumber, userName, IDnumber, bankCardNumber, transAmount);
			}else if(txcode.equals("3000")){//解约
				sendMsg=terminateSign(txcode,tradeno, userNumber, userName, IDnumber, bankCardNumber, transAmount,userMobile);
			}/*else if(txcode.equals("2000")){//对总账
				sendMsg=checkBill();
			}else if(txcode.equals("2000")){//对明细
				sendMsg=checkDetail();
			}*/
			//发送报文
			outputstream.write(sendMsg.getBytes("gbk"));
			//接收服务器端返回来的消息
			DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
			byte  buf[]=new byte[1024]; 
			datainputstream.read(buf);
			backMsg=new String(buf);
			if(txcode.equals("1000")){
				backMsg=dealPckbdy1000(backMsg);
			}else if(txcode.equals("2000")){
				backMsg=dealPckbdy2000(backMsg, bankno,userName);
			}else if(txcode.equals("2000")){
				backMsg=dealPckbdy2000(backMsg, bankno,userName);
			}
			outputstream.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("与银校通讯失败！");
			return "ERR01";
		}
		
		return backMsg;
	}
	
//	1.		一卡通方交易码	4	定值
//	ASC字符 “3011”
//	2.		银行方交易码	5	定值
//	ASC字符 “YKT03”
//	3.		交易日期	8	格式为 YYYYMMDD
//	交易日期可以取一卡通主机的CPU日期
//	4.		流水号	32	一卡通流水号。流水号用于在冲正和对帐时，标识一笔交易。同一交易日期内不能重复。(格式为YYMMDDXXXXXX...)
//	5.		一卡通卡号(或学生号)	20	签约时使用的手机号码
//	3.		姓名	20	右补空格
//	4.		身份证号	18	右补空格
//	6.		银行账号	32	右补空格
//	7.		交易金额	17	单位：分，右补空格
//	8.		一卡通标识	15	银行分给一卡通方的标识
//	 
//	响应报文
//	序号	名称	长度	说明
//	1.		清算日期	8	格式为 YYYYMMDD
//	一卡通系统的日期
//	2.		流水号	32	银行方流水号。银行方流水号
//	3.		回应码	6	如果响应码为 000000 则表明交易成功，
//	否则交易失败
//	4.		响应消息	34	回应码的中文描述
//	右补空格


	public String recharge(String txcode,String tradeno,String userNumber,String userName,String IDnumber,String bankCardNumber,String transAmount){
		String result = "";
		try {
			//request
			String len = "0171";
			String trade_code = "3011";
			String bank_code = "YKT03";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(new Date());
			String trade_serial = StringUtils.formatStr(tradeno, 32);
			String number = StringUtils.formatStr(userNumber, 20);
			String name = StringUtils.formatStr(userName, 20);
			String id_number = StringUtils.formatStr(IDnumber, 18);
			String bank_card_number = StringUtils.formatStr(bankCardNumber, 32);
			String amount = StringUtils.formatStr(transAmount, 17);;
			String system_flag = StringUtils.formatStr(SYSTEM_FLAG, 15);;
			result = len+trade_code+bank_code+date+trade_serial+number+name+id_number+bank_card_number+amount+system_flag;
			
			//response
			String res_len = result.substring(0,4);
			String res_date = result.substring(4,12);
			String res_tradeNo = result.substring(12,44);
			String res_code = result.substring(44,50);
			String res_message = result.substring(50,result.length());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
//	1.		一卡通方交易码	4	定值
//	ASC字符 “3021
//	2.		银行方交易码	5	定值
//	ASC字符 “YKT01
//	3.		一卡通卡号(或学生号)	20	右补空格
//	4.		银行账号	32	右补空格
//	5.		一卡通标识	15	银行分给一卡通方的标识
	
//	2.6.2	响应报文
//	序号	名称	长度	说明
//	1.		回应码	6	如果响应码为 000000 则表明交易成功，否则交易失败
//	2.		响应消息	34	回应码的中文描述
//	3.		账户余额	17	单位：分 右补空格

	public String getBlance(String txcode, String userNumber, String bankCardNumber){
		String result = "";
		try {
			//request
			String len = "0076";
			String trade_code = "3021";
			String bank_code = "YKT01";
			String number = StringUtils.formatStr(userNumber, 20);
			String bank_card_number = StringUtils.formatStr(bankCardNumber, 32);
			String system_flag = StringUtils.formatStr(SYSTEM_FLAG, 15);;
			result = len+trade_code+bank_code+number+bank_card_number+system_flag;
			
			//response
//			String result = "";
			String res_len = result.substring(0,4);
			String res_date = result.substring(4,10);
			String res_tradeNo = result.substring(10,44);
			String res_code = result.substring(44,61);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
//	1.		一卡通方交易码	4	定值
//	ASC字符 “3031”
//	2.		银行方交易码	5	定值
//	ASC字符 “YKT07”
//	3.		一卡通卡号(或学生号)/商户号	20	右补空格
//	4.		用户类型	1	0：用户1：商户
//	2.7.2	响应报文
//	序号	名称	长度	说明
//	1.		回应码	6	如果响应码为 000000 则表明交易成功，
//	否则交易失败
//	2.		响应消息	34	如果响应码为 000000 （也即交易成功）则为一卡通卡号的相关信息，可以为姓名或其它信息，该信息会显示在签约的接口上，以供客户确认输入的一卡通卡号正确
//
//	如果响应不为 000000 ，则为出错信息描述
	public void getUserInfo(String request){
		try {
			//request
			String number = request.substring(9,29);
			
			//resphonse
			String result="";
			String return_len = "0040";
			String return_code="000000";
			String return_message = StringUtils.formatStr("", 34);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
//	1.		一卡通方交易码	4	定值
//	ASC字符 “3001”
//	2.		银行方交易码	5	定值
//	ASC字符 “YKT06”
//	3.		交易日期	8	格式为 YYYYMMDD
//	4.		流水号	32	银行方流水
//	5.		用户类型	1	0：用户签约
//	1：商户签约
//	6.		一卡通卡号(或学生号)/商户号	20	如果是用户签约，则该字段为用户工号
//	如果是商户签约，则该字段为商户编号
//	右补空格
//	6	姓名	20	右补空格
//	7	身份证	18	右补空格
//	8	手机号	11	固定长度
//	9	银行账号	32	右补空格
//
//	2.1.2	响应报文
//	序号	名称	长度	说明
//	1.		回应码	6	如果响应码为 000000 则表明交易成功，
//	否则交易失败
//	2.		响应消息	34	回应码的中文描述
//		注：以下情况仍然返回成功
//	1.	客户已签约
	public void sign(String request){
		try {
			//request
			String number = request.substring(50,70);
			String name = request.substring(70,90);
			String id_number = request.substring(90,108);
			String mobile = request.substring(108,119);
			String bank_card_number = request.substring(119,151);
			
			//resphonse
			String result="";
			String return_len = "0040";
			String return_code="000000";
			String return_message = StringUtils.formatStr("", 34);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
//	1.		一卡通方交易码	4	定值
//	ASC字符 “3002”
//	2.		银行交易码	5	定值
//	ASC字符 “YKT02”
//	3.		交易日期	8	交易日期。格式为 YYYYMMDD
//	4.		流水号	32	一卡通流水(格式为YYMMDDXXXXXX...)
//	5.		用户类型	1	0：用户解约
//	1：商户解约
//	6.		一卡通卡号(或学生号)/商户号	20	右补空格
//	7.		姓名	20	右补空格
//	8.		身份证	18	右补空格
//	9.		手机号	11	固定长度
//	9	银行账号	32	右补空格
//	10	用户卡余额	17	右补空格 单位：分 
//	11	一卡通标识	15	银行分给一卡通方的标识
//
//	2.2.2	响应报文
//	序号	名称	长度	说明
//	1.		银行方交易日期	8	银行方处理此交易的日期
//	2.		业务流水	32	对应请求报文的业务流水
//	3.		回应码	6	如果响应码为 000000 则表明交易成功，
//	否则交易失败
//	4.		响应消息	34	回应码的中文描述
//	右补空格
//
	public String terminateSign(String txcode, String tradeno, String userNumber, String userName, String iDnumber, String bankCardNumber, String transAmount, String userMobile){
		String result = "";
		try {
			//request
			String len = "0183";
			String trade_code = "3002";
			String bank_code = "YKT02";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(new Date());
			String trade_serial = StringUtils.formatStr(tradeno, 32);
			String flag = "0";
			String number = StringUtils.formatStr(userNumber, 20);
			String name = StringUtils.formatStr(tradeno, 20);
			String id_number = StringUtils.formatStr(userName, 18);
			String mobile = userMobile;
			String bank_card_number = StringUtils.formatStr("", 32);
			String amount = StringUtils.formatStr(bankCardNumber, 17);;
			String system_flag = StringUtils.formatStr(SYSTEM_FLAG, 15);;
			
			
			//response
//			String result = "";
			String res_len = result.substring(0,4);
			String res_date = result.substring(4,12);
			String res_tradeNo = result.substring(12,44);
			String res_code = result.substring(44,50);
			String res_message = result.substring(50,result.length());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
//	1.		一卡通方交易码	4	定值
//	ASC字符 “3051
//	2.		银行方交易码	5	定值
//	ASC字符 “YKT08”
//	3.		对账日期	8	银行方需要获取交易的日期yyyymmdd
//	4.		当天圈存总笔数	4	当前所有圈存的总笔数，右补空格
//	5.		当天圈存总金额	17	当前所有圈存的总金额，单位：分 右补空格
//	6.		当天冲正总笔数	4	当前所有冲正的总笔数，右补空格
//	7.		当天冲正总金额	17	当前所有冲正的总金额，单位：分 右补空格
//	8.		当天销户总笔数	4	当前所有销户的总笔数，右补空格
//	9.		当天销户总金额	17	当前所有销户的总金额，单位：分 右补空格
//	10.		当天所有商户交易总笔数	4	当天所有商户交易总笔数，右补空格。
//	注意：此交易总笔数只是一卡通向银行发起的商户结算的总笔数，每个商户相当于一条数据
//	11.		当天所有商户交易总金额	17	当前所有商户交易的总金额，右补空格。
//	此总金额就是商户交易总笔数中的每笔金额的总和
//	12.		所有正确圈存的卡号总和	17	此字段为当天所有在银行方圈存成功的银行卡卡号的后五位相加的总和
//	13.		所有冲正的卡号总和	17	此字段为当天所有冲正用户的银行卡卡号的后五位相加的总和
//	14.		所有销户的卡号总和	17	此字段为当天所有销户用户的银行卡卡号的后五位相加的总和
//	15.		所有结算的卡号总和	17	此字段为当天所有结算商户的银行卡卡号的后五位相加的总和
//	16.		一卡通标识	15	银行分给一卡通方的标识
//	1.		回应码	6	如果响应码为 
//	000000 则表明交易成功，
//	L99999代表银行正在处理账务，
//	其他代表交易失败
//	2.		响应消息	34	回应码的中文描述
	public void checkBill(){
		try {
			//request
			String len = "0184";
			String trade_code = "3051";
			String bank_code = "YKT08";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(new Date());
			String qc_num = StringUtils.formatStr("", 4);
			String qc_sum = StringUtils.formatStr("", 17);
			String cz_num = StringUtils.formatStr("", 4);
			String cz_sum = StringUtils.formatStr("", 17);
			String xh_num = StringUtils.formatStr("", 4);
			String xh_sum = StringUtils.formatStr("", 17);
			String js_num = StringUtils.formatStr("", 4);
			String js_sum = StringUtils.formatStr("", 17);
			String qc_card_sum = StringUtils.formatStr("", 17);
			String cz_card_sum = StringUtils.formatStr("", 17);
			String xh_card_sum = StringUtils.formatStr("", 17);
			String js_card_sum = StringUtils.formatStr("", 17);
			String system_flag = StringUtils.formatStr("", 15);
			
			//response
			String result = "";
			String res_len = result.substring(0,4);
			String res_date = result.substring(4,10);
			String res_tradeNo = result.substring(10,44);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
//	1.		一卡通方交易码	4	定值
//	ASC字符 “3071
//	2.		银行方交易码	5	定值
//	ASC字符 “YKT10”
//	3.		对账明细时间	8	格式：yyyymmdd
//	4.		一卡通FTP地址	15	右补空格
//	一卡通方的ftp地址,比如192.168.1.1
//	5.		一卡通FTP端口	5	右补空格
//	一卡通方的ftp端口号，比如23
//	6.		一卡通FTP用户名	20	右补空格
//	一卡通方的ftp用户名
//	7.		一卡通FTP密码	20	右补空格
//	一卡通方FTP密码
//	8.		一卡通FTP路径	100	右补空格
//	一卡通方明细对账ftp路径
//	9.		一卡通标识	15	银行分给一卡通方的标识
//	2.10.2	响应报文
//	序号	名称	长度	说明
//	3.		回应码	6	如果响应码为 000000 则表明交易成功，
//	否则交易失败
//	4.		响应消息	34	回应码的中文描述
//	银行向一卡通响应成功后，就开始生成明细文件，一卡通等待采集明细文件

	public void checkDetail(){
		try {
			//request
			String len = "0184";
			String trade_code = "3071";
			String bank_code = "YKT10";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(new Date());
			String ftp_ip = StringUtils.formatStr("", 15);
			String ftp_port = StringUtils.formatStr("", 5);
			String ftp_user = StringUtils.formatStr("", 20);
			String ftp_password = StringUtils.formatStr("", 20);
			String ftp_path = StringUtils.formatStr("", 100);
			String system_flag = StringUtils.formatStr("", 15);
			
			//response
			String result = "";
			String res_len = result.substring(0,4);
			String res_date = result.substring(4,10);
			String res_tradeNo = result.substring(10,44);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
//		文件头
//	序号	名称	长度	说明
//	1.		总笔数	6	右补空格
//	2.		总金额	17	右补空格
//
//		文件体
//	序号	名称	长度	说明
//	1.		一卡通流水号	32	右补空格
//	2.		银行流水号	32	银行向一卡通返回的相应的流水
//	3.		交易日期	8	银行方交易日期
//	4.		用户工号\商户编号	20	用户工号或者商户的编号
//	5.		用户类型	1	0：用户
//	1：商户
//	6.		交易金额	17	右补空格
//	对账明细文件命名规范：
//
//	银行代码（ABC）+”_”+一卡通标识+”_”+交易种类+”_”+”对账日期”
//	银行代码 三位数 农行为ABC
//	交易种类 两位数 圈存为qc、冲正为cz、销户为xh、结算为js
//	对账日期为一卡通方请求的对账日。
	public void checkFile(){
		try {
			File file = new File("");
			BufferedReader reader = new BufferedReader(new FileReader(file));//获取本地对账文件
			String readLine = null;

			int i=0;
			while((readLine=reader.readLine())!=null){
				i+=1;
				if(i>1){//第二行开始才记录对账文件
					String tradeno = readLine.substring(0, 32);
					String bank_tradeno = readLine.substring(32, 64);
					String txdate = readLine.substring(64, 72);
					String number = readLine.substring(72, 92);
					String type = readLine.substring(92, 93);
					String amount = readLine.substring(93, 110);
				}
			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		String result = "008020121022201210220000000000000001        000000交易成功                          ";
		
		String str=StringUtils.formatStr("交易成功", 34);;
		System.out.println(str);
	}
}
