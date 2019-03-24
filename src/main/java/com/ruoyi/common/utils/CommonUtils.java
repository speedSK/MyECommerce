package com.ruoyi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ruoyi.project.business.person.domain.Person;

/**
 * 通用工具类
 * @author CoderX
 */
public class CommonUtils {

	/**
	 * 生成订单号工具类:时间戳+4位ID序号
	 * @param person
	 * @return
	 */
	public static String getOrderCode(Person person){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
        return df.format(new Date())+getPersonNo(person,4);
	}
	
	/**
	 * 将personId转换成指定长度字符串
	 * @param person
	 * @param length
	 * @return
	 */
	public static String getPersonNo(Person person, int length){
		String unProcessNo = person.getId().toString();
		StringBuffer preFix = new StringBuffer();
		for(int i = 0; i<(length-unProcessNo.length()); i++){
			preFix.append("0");
		}
		return preFix.append(unProcessNo).toString();
	}
	
}
