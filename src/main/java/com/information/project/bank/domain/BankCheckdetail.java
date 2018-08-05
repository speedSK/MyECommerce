package com.information.project.bank.domain;

import java.util.Date;

public class BankCheckdetail {
    private Long id;

    private String number;

    private String idserial;

    private String bankIdserial;

    private String transDate;

    private String amount;

    private String userType;

    private String checkStatus;

    private Long operUser;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getIdserial() {
        return idserial;
    }

    public void setIdserial(String idserial) {
        this.idserial = idserial == null ? null : idserial.trim();
    }

    public String getBankIdserial() {
        return bankIdserial;
    }

    public void setBankIdserial(String bankIdserial) {
        this.bankIdserial = bankIdserial == null ? null : bankIdserial.trim();
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate == null ? null : transDate.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    public Long getOperUser() {
        return operUser;
    }

    public void setOperUser(Long operUser) {
        this.operUser = operUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}