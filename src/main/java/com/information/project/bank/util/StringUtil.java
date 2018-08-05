package com.information.project.bank.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtil {
	/**
	 * 去除开头的0
	 * 
	 * @param str
	 * @return
	 */
	public static String removeZero(String str) {
		char ch;
		String result = "";
		if (str != null && str.trim().length() > 0 && !str.trim().equalsIgnoreCase("null")) {
			try {
				for (int i = 0; i < str.length(); i++) {
					ch = str.charAt(i);
					if (ch != '0') {
						result = str.substring(i);
						break;
					}
				}
			} catch (Exception e) {
				result = "";
			}
		} else {
			result = "";
		}
		return result;

	}

	/**
	 * 金额转换分转元
	 * 
	 * @param o
	 * @return
	 */
	public static String fen2Yuan(Object o) {
		if (o == null)
			return "0.00";
		String s = o.toString();
		int len = -1;
		StringBuilder sb = new StringBuilder();
		if (s != null && s.trim().length() > 0 && !s.equalsIgnoreCase("null")) {
			s = removeZero(s);
			if (s != null && s.trim().length() > 0 && !s.equalsIgnoreCase("null")) {
				len = s.length();
				int tmp = s.indexOf("-");
				if (tmp >= 0) {
					if (len == 2) {
						sb.append("-0.0").append(s.substring(1));
					} else if (len == 3) {
						sb.append("-0.").append(s.substring(1));
					} else {
						sb.append(s.substring(0, len - 2)).append(".").append(s.substring(len - 2));
					}
				} else {
					if (len == 1) {
						sb.append("0.0").append(s);
					} else if (len == 2) {
						sb.append("0.").append(s);
					} else {
						sb.append(s.substring(0, len - 2)).append(".").append(s.substring(len - 2));
					}
				}
			} else {
				sb.append("0.00");
			}
		} else {
			sb.append("0.00");
		}
		return sb.toString();
	}

	/**
	 * 金额转换元转分
	 * 
	 * @param o
	 * @return
	 */
	public static String yuan2Fen(Object o) {
		if (o == null)
			return "0";
		String s = o.toString();
		int posIndex = -1;
		String str = "";
		StringBuilder sb = new StringBuilder();
		if (s != null && s.trim().length() > 0 && !s.equalsIgnoreCase("null")) {
			posIndex = s.indexOf(".");
			if (posIndex > 0) {
				int len = s.length();
				if (len == posIndex + 1) {
					str = s.substring(0, posIndex);
					if (str == "0") {
						str = "";
					}
					sb.append(str).append("00");
				} else if (len == posIndex + 2) {
					str = s.substring(0, posIndex);
					if (str == "0") {
						str = "";
					}
					sb.append(str).append(s.substring(posIndex + 1, posIndex + 2)).append("0");
				} else if (len == posIndex + 3) {
					str = s.substring(0, posIndex);
					if (str == "0") {
						str = "";
					}
					sb.append(str).append(s.substring(posIndex + 1, posIndex + 3));
				} else {
					str = s.substring(0, posIndex);
					if (str == "0") {
						str = "";
					}
					sb.append(str).append(s.substring(posIndex + 1, posIndex + 3));
				}
			} else {
				sb.append(s).append("00");
			}
		} else {
			sb.append("0");
		}
		str = removeZero(sb.toString());
		if (str != null && str.trim().length() > 0 && !str.trim().equalsIgnoreCase("null")) {
			return str;
		} else {
			return "0";
		}
	}

	/**
	 * 生成指定长度字符串，不足位右补空格
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String formatStr(String str, int length, String encoding) throws Exception {
		if (str == null) {
			return null;
		}
		int strLen = str.getBytes(encoding).length; // 空格的长度 = 20 - 字段的长度（GBK）。
		if (strLen == length) {
			return str;
		} else if (strLen < length) {
			int temp = length - strLen;
			String tem = "";
			for (int i = 0; i < temp; i++) {
				tem = tem + " ";
			}
			return str + tem;
		} else {
			return str.substring(0, length);
		}
	}

	/**
	 * 判断字符串编码
	 * 
	 * @Description:
	 * @author LiuNing
	 * @date 2018年8月1日 下午2:20:06
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}
	
	public static String getDateBefore() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(5, -1);
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}
	
}
