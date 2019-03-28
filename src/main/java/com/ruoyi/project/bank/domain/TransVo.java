package com.ruoyi.project.bank.domain;

public class TransVo {
	private String journo;
	private String startTime;
	private String endTime;
	private String accName;
	private String accNumber;
	private String startNumber;
	private String endNumber;
	private String contLast;

	public String getJourno() {
		return journo;
	}

	public void setJourno(String journo) {
		this.journo = journo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(String startNumber) {
		this.startNumber = startNumber;
	}

	public String getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(String endNumber) {
		this.endNumber = endNumber;
	}

	public String getContLast() {
		return contLast;
	}

	public void setContLast(String contLast) {
		this.contLast = contLast;
	}

	@Override
	public String toString() {
		return "TransVo{" +
				"journo='" + journo + '\'' +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", accName='" + accName + '\'' +
				", accNumber='" + accNumber + '\'' +
				", startNumber='" + startNumber + '\'' +
				", endNumber='" + endNumber + '\'' +
				", contLast='" + contLast + '\'' +
				'}';
	}
}
