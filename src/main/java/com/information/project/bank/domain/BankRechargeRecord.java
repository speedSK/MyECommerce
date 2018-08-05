package com.information.project.bank.domain;

import java.util.Date;

public class BankRechargeRecord {
    private Long id;

    private String code;

    private String bankCode;

    private String transDate;

    private String transIdserial;

    private String userCode;

    private String userName;

    private String diNumber;

    private String bankNumber;

    private String amount;

    private String status;

    private String returnCode;

    private String returnMessage;

    private String bankIdserial;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate == null ? null : transDate.trim();
    }

    public String getTransIdserial() {
        return transIdserial;
    }

    public void setTransIdserial(String transIdserial) {
        this.transIdserial = transIdserial == null ? null : transIdserial.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getDiNumber() {
        return diNumber;
    }

    public void setDiNumber(String diNumber) {
        this.diNumber = diNumber == null ? null : diNumber.trim();
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber == null ? null : bankNumber.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage == null ? null : returnMessage.trim();
    }

    public String getBankIdserial() {
        return bankIdserial;
    }

    public void setBankIdserial(String bankIdserial) {
        this.bankIdserial = bankIdserial == null ? null : bankIdserial.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}