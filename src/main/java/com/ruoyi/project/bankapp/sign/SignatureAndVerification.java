package com.ruoyi.project.bankapp.sign;

import com.ruoyi.project.bankapp.utils.HttpClientUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 验签和加签工具类
 * @author yzz
 *
 */
@Component
public class SignatureAndVerification {
	
	@Value("${bank.pfxPath}")
	private  String PFXPATH;
	
	@Value("${bank.cerPath}")
	private  String CERPATH;
	
	@Value("${bank.keystore_password}")
	private  String KEYSTORE_PASSWORD;
	
	@Value("${bank.keystore_alias}")
	private  String KEYSTORE_ALIAS;
	
	private String  rootPath=this.getClass().getClassLoader().getResource("certificate/").getPath();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 加签名
	 * @param dataString
	 * @return
	 */
	public  String signWhithsha1withrsa(String dataString) {
		String signatureString = null;
		String filePath=rootPath+PFXPATH;
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(filePath);
			char[] nPassword = null;
			if ((KEYSTORE_PASSWORD == null)
					|| KEYSTORE_PASSWORD.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = KEYSTORE_PASSWORD.toCharArray();
			}
			ks.load(fis, nPassword);
			fis.close();
			System.out.println("keystore type=" + ks.getType());
			Enumeration<String> enums = ks.aliases();
			String keyAlias = null;
			if (enums.hasMoreElements()) 
			{
				keyAlias = (String) enums.nextElement();
				System.out.println("alias=[" + keyAlias + "]");
			}
			System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
			PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			java.security.cert.Certificate cert = ks.getCertificate(keyAlias);
			PublicKey pubkey = cert.getPublicKey();
			System.out.println("cert class = " + cert.getClass().getName());
			System.out.println("cert = " + cert);
			System.out.println("public key = " + pubkey);
			System.out.println("private key = " + prikey);
			// SHA1withRSA算法进行签名
			Signature sign = Signature.getInstance("SHA1withRSA");
			sign.initSign(prikey);
			byte[] data = dataString.getBytes("utf-8");
			byte[] dataBase= Base64.encodeBase64(data);
			// 更新用于签名的数据
			sign.update(dataBase);
			byte[] signature = sign.sign();
			signatureString = new String(Base64.encodeBase64(signature));
			System.out.println("--------signature is : " + signatureString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signatureString;
	}

	/**
	 * 读取cer并验证公钥签名
	 */
	public  void read_cer_and_verify_sign(String requsetBody, String signature) {
		String filePath=rootPath+CERPATH;
		X509Certificate cert = null;
		try {
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			cert = (X509Certificate) cf
					.generateCertificate(new FileInputStream(new File(
							filePath)));
			PublicKey publicKey = cert.getPublicKey();
			String publicKeyString = new String(Base64.encodeBase64(publicKey
					.getEncoded()));
			System.out.println("-----------------公钥--------------------");
			System.out.println(publicKeyString);
			System.out.println("-----------------公钥--------------------");
			Signature verifySign = Signature.getInstance("SHA1withRSA");
			verifySign.initVerify(publicKey);
			// 用于验签的数据
			verifySign.update(requsetBody.getBytes("utf-8"));
			boolean flag = verifySign.verify(com.alibaba.fastjson.util.Base64
					.decodeFast(signature));// 验签由第三方做
			System.out.println("verifySign is " + flag);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 接收报文返回requsetBody和使用base64解析后的requsetBody以及缴费中心传送的签名
	 */

	public Map<String,String> requestBodyOfBase64(HttpServletRequest request){
		Map<String,String> requestMap=new HashMap<String,String>();
		// 接收报文
		String requestContent=null;
		try {
			requestContent = HttpClientUtils.getRequestBody(request)
					.trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 /* requestContent=
		  "k6lECy5TcFx9SNniGM8xg94ZeocFBOIp8xF1wJg817gcBYuN6UHssjwr0/U5W2D1XZIRXJHQkgfluQ2qzZhDl5eiOyHpNgbxR0I/QYxUokaZy3XnSAjCi+uv6O6gti5MCnFs3ZP1l4cKdJrKMPaZowoQKR0aeUUFc3zWTH3LTcg=||eyJmb3JtYXQiOiJqc29uIiwibWVzc2FnZSI6eyJoZWFkIjp7ImJyYW5jaENvZGUiOiIyMTEwIiwiY2hhbm5lbCI6Ik1CTksiLCJ0aW1lU3RhbXAiOiIyMDE4MDkyMTE1MTg0Nzg2NyIsInRyYW5zQ29kZSI6InF1ZXJ5QmlsbCIsInRyYW5zRmxhZyI6IjAxIiwidHJhbnNTZXFOdW0iOiJCUDE4MDkyMTE1MTg1NzM5MDAwOSJ9LCJpbmZvIjp7ImVwYXlDb2RlIjoiSkYtRVBBWTIwMTgwODAyNjU2MDIiLCJpbnB1dDEiOiIxMjM0NTYiLCJtZXJjaGFudElkIjoiMTAzODgxMTA0NDEwMDAxIiwidHJhY2VObyI6IkpGMTgwOTIxMTUxODU3ODQ4NTkyIiwidXNlcklkIj"
		  + "oiMTYzNzUwNDYwMjk5NDM1NiJ9fX0=";*/
		 
		if (logger.isWarnEnabled()) {
			logger.info("收到的报文：{}", requestContent);
		}
		String signatureString = requestContent.substring(0,
				requestContent.indexOf("||"));
		logger.info("截取报文的signatureString:", signatureString);
		String requsetBody = requestContent.substring(signatureString
				.length() + 2);
		logger.info("截取报文的requsetBody:", requsetBody);
		String requsetBodyOfDecoded = new String(
				com.alibaba.fastjson.util.Base64.decodeFast(requsetBody));
		System.out.println("-----解析完成后的requsetBody-------" + requsetBodyOfDecoded);
		//使用base64解析完成后的requsetBody
		requestMap.put("requsetBodyOfDecoded",requsetBodyOfDecoded);
		//解析前的requsetBody
		requestMap.put("requsetBody",requsetBody);
		//获取缴费中心传送过来的签名
		requestMap.put("signatureString",signatureString);
		return requestMap;
		
	} 
	
}
