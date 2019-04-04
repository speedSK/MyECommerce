package com.ruoyi.project.bank.domain;

import java.io.Serializable;

public class ReceiveFromBankInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String responseCode;
	private String fileName;
	private String responseInfo;

	public String getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(String responseInfo) {
		this.responseInfo = responseInfo;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getFileName() {
		return fileName == null ? "" : fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}