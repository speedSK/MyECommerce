package com.ruoyi.framework.shiro.domain;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsrPwTokenExtend extends UsernamePasswordToken{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * user type : 1.prisonManager 2.prisoner
	 */
	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	/**
	 * generate arguments constructor
	 */
	public UsrPwTokenExtend(String username, String password, String usertype){
		super(username, password);
		this.userType=usertype;
	}
	
	public UsrPwTokenExtend(String username, String password, boolean rememberMe, String usertype){
		super(username, password, rememberMe);
		this.userType=usertype;
	}
}
