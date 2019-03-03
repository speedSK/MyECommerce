package com.ruoyi.project.bankapp.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 单笔缴费流水查询
 * @author marui
 *
 */
@Controller
public class TraceController {
private Logger logger = LoggerFactory.getLogger(RefundTraceController.class);
	
	@Autowired
	private SignatureAndVerification signatureAndVerification;
	//请求路径，在conf.properties文件中读取
	@Value(value="${bank.Bridge_URL_ConfirmTrace}")
	private String reqUrl;
	
	@ResponseBody
	@RequestMapping(value = "/trace.do", method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String ConfirmTrace(HttpServletRequest req, HttpServletResponse resp, @RequestParam("callback")String callback) throws IOException{
		
		String reqJson = req.getParameter("reqJson");
		
		//对需要发送的json字符串进行签名
		String signatrue = signatureAndVerification.signWhithsha1withrsa(reqJson);
		logger.info("加密前发送的报文："+reqJson);
		//加签名
		String reqStr = signatrue+"||"+ Base64Util.encodeData(reqJson);
		logger.info("发送的报文："+reqStr);
		
		//发送签名信息获取返回签名信息
		String responseStr = HttpClientUtils.doPostStr(reqUrl, reqStr);
		logger.info("接收到的报文："+responseStr);
		String respJson = null;
		if (responseStr.startsWith("{\"string\":")){
			respJson = responseStr;
		}else{
			//截取签名信息
			String headSub = responseStr.substring(0, responseStr.indexOf("||"));
			logger.info("获取签名的前半部分："+headSub);
			//截取加密的json信息，进行解密
			String tailSub = responseStr.substring(responseStr.indexOf("||")+2);
			logger.info("获取签名的后半部分："+tailSub);
			//对获取的信息进行验签(该方法对signWhithsha1withrsa加密和Base64Util解密的字符串可以直接进行验签)
			signatureAndVerification.read_cer_and_verify_sign(tailSub,headSub);
			
			respJson = Base64Util.decodeData(tailSub);
			logger.info("获取签名解密后："+respJson);
		}
		//解决post跨域请求问题
//		resp.setHeader("Access-Control-Allow-Origin", "*");
//		resp.setHeader("Access-Control-Allow-Methods", "*");
		return callback+"("+respJson+")";
	}
}
