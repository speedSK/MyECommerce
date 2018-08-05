package com.information.project.bank.domain;

import java.io.Serializable;

public class ReceiveFromBankInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String responsecode;
	private String responsename;
	private String idserial;
	private String bankcdno;
	private long accbal;
	private long accavlbal;
	private String accstatus;
	private String accname;
	private long txamt;
	private long rewriteamt;
	private String transnum;
	private String yktJourno;

	public String getResponsecode() {
		return this.responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public String getResponsename() {
		return this.responsename;
	}

	public void setResponsename(String responsename) {
		this.responsename = responsename;
	}

	public String getIdserial() {
		return this.idserial;
	}

	public void setIdserial(String idserial) {
		this.idserial = idserial;
	}

	public String getBankcdno() {
		return this.bankcdno;
	}

	public void setBankcdno(String bankcdno) {
		this.bankcdno = bankcdno;
	}

	public long getAccbal() {
		return this.accbal;
	}

	public void setAccbal(long accbal) {
		this.accbal = accbal;
	}

	public long getAccavlbal() {
		return this.accavlbal;
	}

	public void setAccavlbal(long accavlbal) {
		this.accavlbal = accavlbal;
	}

	public String getAccstatus() {
		return this.accstatus;
	}

	public void setAccstatus(String accstatus) {
		this.accstatus = accstatus;
	}

	public String getAccname() {
		return this.accname;
	}

	public void setAccname(String accname) {
		this.accname = accname;
	}

	public long getTxamt() {
		return this.txamt;
	}

	public void setTxamt(long txamt) {
		this.txamt = txamt;
	}

	public long getRewriteamt() {
		return this.rewriteamt;
	}

	public void setRewriteamt(long rewriteamt) {
		this.rewriteamt = rewriteamt;
	}

	public String getTransnum() {
		return this.transnum;
	}

	public void setTransnum(String transnum) {
		this.transnum = transnum;
	}

	public String getYktJourno() {
		return this.yktJourno;
	}

	public void setYktJourno(String yktJourno) {
		this.yktJourno = yktJourno;
	}
}