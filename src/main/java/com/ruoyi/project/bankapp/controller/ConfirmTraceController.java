package com.ruoyi.project.bankapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ruoyi.project.bankapp.domain.ConfirmTraceRequest;
import com.ruoyi.project.bankapp.domain.ConfirmTraceResponse;
import com.ruoyi.project.bankapp.sign.SignatureAndVerification;
import com.ruoyi.project.bankapp.utils.Base64Util;
import com.ruoyi.project.bankapp.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 单笔缴费流水查询
 * @author marui
 *
 */
@Controller
public class ConfirmTraceController {
private Logger logger = LoggerFactory.getLogger(RefundTraceController.class);
	
	@Autowired
	private SignatureAndVerification signatureAndVerification;
	//请求路径，在conf.properties文件中读取
	@Value(value="${bank.Bridge_URL_ConfirmTrace}")
	private String reqUrl;
	@Value(value="${bank.Bridge_TransCode_ConfirmTrace}")
	private String confirmTrace;
	
	@RequestMapping(value = "/confirmTrace.do", method = RequestMethod.POST)
	public String ConfirmTrace(Map<String,Object> map,String traceNo,String merchantId){
		//输入项避免首尾出现空格的情况
		traceNo = traceNo.trim();
		merchantId = merchantId.trim();
		//创建发送请求对象
		ConfirmTraceRequest confirmTraceRequest = new ConfirmTraceRequest();
		//以当前时间为时间戳
		String stamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		
		//发送报文的格式，以json格式传送
		confirmTraceRequest.setFormat("json");
		// 交易的序列号需由第三方提供，且必须按照特定规则上送缴费中心，规则定义：BRIDGE前缀+当前17位时间戳timeStamp+商户号merchantId
		confirmTraceRequest.getMessage().getHead().setTransSeqNum("BRIDGE"+stamp+merchantId);
		//交易码，固定
		confirmTraceRequest.getMessage().getHead().setTransCode(confirmTrace); 
		//时间戳格式 yyyyMMddHHmmssSSS，
		confirmTraceRequest.getMessage().getHead().setTimestamp(stamp); 
		//上行下送标志，固定
		confirmTraceRequest.getMessage().getHead().setTransFlag("01");
		
		//缴费项目配置的主商户在商E付系统的编号
		confirmTraceRequest.getMessage().getInfo().setMerchantId(merchantId);
		//标志唯一一笔流水，可以用来查日志，必须跟缴费账单流水和查询账单流水保持一致
		confirmTraceRequest.getMessage().getInfo().setTraceNo(traceNo);
		/* 例如：String traceNo = "JF180824103752811618";
		 * String merchantId = "103881104410001";
		 */
		
		//封装完成，将数据转为json格式，然后加密加签名
		String reqJson = JSON.toJSONString(confirmTraceRequest);
		//对需要发送的json字符串进行签名
		String signatrue = signatureAndVerification.signWhithsha1withrsa(reqJson);
		logger.info("加密前发送的报文："+reqJson);
		//加签名
		String reqStr = signatrue+"||"+ Base64Util.encodeData(reqJson);
		logger.info("发送的报文："+reqStr);
		
		//发送签名信息获取返回签名信息
		String responseStr = HttpClientUtils.doPostStr(reqUrl, reqStr);
		logger.info("接收到的报文："+responseStr);
		
		//TODO 如下可以对返回的报文进行处理，封装到map回显到页面，也可以做其他处理
		if (responseStr.startsWith("{\"string\":"))
		{
			//如果系统发生异常则会返回"{"string":"异常信息"}"
			map.put("returnMessage", responseStr.substring(responseStr.indexOf(":") + 2,responseStr.indexOf("}")-1));
		}else{
			//截取签名信息
			String headSub = responseStr.substring(0, responseStr.indexOf("||"));
			logger.info("获取签名的前半部分："+headSub);
			//截取加密的json信息，进行解密
			String tailSub = responseStr.substring(responseStr.indexOf("||")+2);
			logger.info("获取签名的后半部分："+tailSub);
			//对获取的信息进行验签(该方法对signWhithsha1withrsa加密和Base64Util解密的字符串可以直接进行验签)
			try {
				signatureAndVerification.read_cer_and_verify_sign(tailSub,headSub);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("returnMessage", "返回签名解析失败！");
			}
			//解密返回报文
			String respJson = Base64Util.decodeData(tailSub);
			logger.info("获取签名解密后："+respJson);
			//截取签名信息中的json字符串，并转化为对象
			ConfirmTraceResponse confirmTraceResponse = JSON.parseObject(respJson,
					new TypeReference<ConfirmTraceResponse>(){});
			
			ConfirmTraceResponse.Message.Head head = confirmTraceResponse.getMessage().getHead();
			ConfirmTraceResponse.Message.Info info = confirmTraceResponse.getMessage().getInfo();
			//返回的消息头信息
			map.put("transSeqNum", head.getTransSeqNum() == null?"":head.getTransSeqNum());
			map.put("transCode", head.getTransCode() == null?"":head.getTransCode());
			map.put("returnCode", head.getReturnCode() == null?"":head.getReturnCode());
			map.put("returnMessage", head.getReturnMessage() == null?"":head.getReturnMessage());
			map.put("timeStamp", head.getTimeStamp() == null?"":head.getTimeStamp());
			map.put("transFlag", head.getTransFlag() == null?"":head.getTransFlag());
			//返回的消息体信息
			map.put("merchantId", info.getMerchantId() == null?"":info.getMerchantId());
			map.put("merchantName", info.getMerchantName() == null?"":info.getMerchantName());
			map.put("epayCode", info.getEpayCode() == null?"":info.getEpayCode());
			map.put("input1", info.getInput1() == null?"":info.getInput1());
			map.put("input2", info.getInput2() == null?"":info.getInput2());
			map.put("input3", info.getInput3() == null?"":info.getInput3());
			map.put("input4", info.getInput4() == null?"":info.getInput4());
			map.put("input5", info.getInput5() == null?"":info.getInput5());
			map.put("traceNo", info.getTraceNo() == null?"":info.getTraceNo());
			map.put("billNo", info.getBillNo() == null?"":info.getBillNo());
			map.put("amtEpay", info.getAmtEpay() == null?"":info.getAmtEpay());
			map.put("amtPreferential", info.getAmtPreferential() == null?"":info.getAmtPreferential());
			map.put("timeEpay", info.getTimeEpay() == null?"":info.getTimeEpay());
			map.put("payStatus", info.getPayStatus() == null?"":info.getPayStatus());
			map.put("payStatusMessage", info.getPayStatusMessage() == null?"":info.getPayStatusMessage());
		}
		return "confirmTraceResult.jsp";
	}
}
