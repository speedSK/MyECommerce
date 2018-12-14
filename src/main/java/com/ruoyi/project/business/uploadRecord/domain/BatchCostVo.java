package com.ruoyi.project.business.uploadRecord.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;

public class BatchCostVo {
	
	@Excel(name="用户编号")
	private String number;
	@Excel(name="消费金额")
	private String amount;
	@Excel(name="收款商户")
	private String merchant;
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

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
}
