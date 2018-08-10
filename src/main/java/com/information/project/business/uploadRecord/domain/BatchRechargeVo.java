package com.information.project.business.uploadRecord.domain;

import com.information.framework.aspectj.lang.annotation.Excel;

public class BatchRechargeVo {
	
	@Excel(name="用户编号")
	private String number;
	@Excel(name="充值金额")
	private String amount;
	@Excel(name="失败原因")
	private String failure;
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFailure() {
		return failure;
	}

	public void setFailure(String failure) {
		this.failure = failure;
	}
	
	
}
