package com.information.project.business.uploadRecord.domain;

import java.math.BigDecimal;

import com.information.framework.aspectj.lang.annotation.Excel;

public class BatchRechargeVo {
	
	@Excel(name="用户编号")
	private String number;
	@Excel(name="充值金额")
	private BigDecimal amount;
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
