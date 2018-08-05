package com.information.project.bank.domain;

import java.util.Date;

public class BankCheckbill {
    private Long id;

    private String code;

    private String bankCode;

    private String checkDate;

    private String rechargeNum;

    private String rechargeSum;

    private String correctionNum;

    private String correctionSum;

    private String closeNum;

    private String closeSum;

    private String merchantNum;

    private String merchantSum;

    private String rechargeCardSum;

    private String correctionCardSum;

    private String closeCardSum;

    private String merchantCardSum;

    private String returnCode;

    private String returnMessage;

    private String status;

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

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate == null ? null : checkDate.trim();
    }

    public String getRechargeNum() {
        return rechargeNum;
    }

    public void setRechargeNum(String rechargeNum) {
        this.rechargeNum = rechargeNum == null ? null : rechargeNum.trim();
    }

    public String getRechargeSum() {
        return rechargeSum;
    }

    public void setRechargeSum(String rechargeSum) {
        this.rechargeSum = rechargeSum == null ? null : rechargeSum.trim();
    }

    public String getCorrectionNum() {
        return correctionNum;
    }

    public void setCorrectionNum(String correctionNum) {
        this.correctionNum = correctionNum == null ? null : correctionNum.trim();
    }

    public String getCorrectionSum() {
        return correctionSum;
    }

    public void setCorrectionSum(String correctionSum) {
        this.correctionSum = correctionSum == null ? null : correctionSum.trim();
    }

    public String getCloseNum() {
        return closeNum;
    }

    public void setCloseNum(String closeNum) {
        this.closeNum = closeNum == null ? null : closeNum.trim();
    }

    public String getCloseSum() {
        return closeSum;
    }

    public void setCloseSum(String closeSum) {
        this.closeSum = closeSum == null ? null : closeSum.trim();
    }

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum == null ? null : merchantNum.trim();
    }

    public String getMerchantSum() {
        return merchantSum;
    }

    public void setMerchantSum(String merchantSum) {
        this.merchantSum = merchantSum == null ? null : merchantSum.trim();
    }

    public String getRechargeCardSum() {
        return rechargeCardSum;
    }

    public void setRechargeCardSum(String rechargeCardSum) {
        this.rechargeCardSum = rechargeCardSum == null ? null : rechargeCardSum.trim();
    }

    public String getCorrectionCardSum() {
        return correctionCardSum;
    }

    public void setCorrectionCardSum(String correctionCardSum) {
        this.correctionCardSum = correctionCardSum == null ? null : correctionCardSum.trim();
    }

    public String getCloseCardSum() {
        return closeCardSum;
    }

    public void setCloseCardSum(String closeCardSum) {
        this.closeCardSum = closeCardSum == null ? null : closeCardSum.trim();
    }

    public String getMerchantCardSum() {
        return merchantCardSum;
    }

    public void setMerchantCardSum(String merchantCardSum) {
        this.merchantCardSum = merchantCardSum == null ? null : merchantCardSum.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}