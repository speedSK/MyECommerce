package com.ruoyi.project.business.person.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import java.math.BigDecimal;

/**
 * 人员管理 bus_person
 * 
 * @author LiuNing
 * @date 2018-08-09
 */
public class Person extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	@Excel(name = "人员序号")
	private Long id;
	/** 编号 */
    @Excel(name = "人员编号")
	private String number;
	/** 姓名 */
    @Excel(name = "姓名")
	private String name;
	/** 性别 */
	private String sex;
	/** 年龄 */
    @Excel(name = "年龄")
	private Integer age;
	/** 民族 */
	private Long nation;
	/** 身份证号 */
    @Excel(name = "身份证号")
	private String idcard;
	/** 银行卡号 */
    @Excel(name = "银行卡号")
	private String bankCardNumber;
	/** 楼号 */
    @Excel(name = "楼号")
	private String build;
	/** 照片 */
	private String photo;
	/** 手机号 */
    @Excel(name = "手机号码")
	private String mobile;
	/** 监区 */
    @Excel(name = "监区")
	private String area;
	/** 房间 */
    @Excel(name = "房间")
	private String room;
	/** 床号 */
    @Excel(name = "床号")
	private String bed;
	/** 部门 */
	private Long deptId;
	/** 部门 */
	private String deptName;
	/** 身份 */
	private Long identityId;
	/** 密码（用于登录） */
	private String password;
	/** 盐加密 */
	private String salt;
	/** 押金 */
	private BigDecimal deposit;
	/** 账户余额 */
	private BigDecimal balance;
	/** 当月已经消费 */
	private BigDecimal alreadyCost;
	/** 账户状态 */
	private String flag;
	/** 状态 */
	private String status;
	/**充值金额*/
	private BigDecimal recharge;
	/**银行卡余额*/
	private String bankBalance;

	public String getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(String bankBalance) {
		this.bankBalance = bankBalance;
	}

	/**
	 * 设置：主键
	 */
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	/**
	 * 获取：主键
	 */
	public Long getId() 
	{
		return id;
	}
	
	/**
	 * 设置：编号
	 */
	public void setNumber(String number) 
	{
		this.number = number;
	}
	
	/**
	 * 获取：编号
	 */
	public String getNumber() 
	{
		return number;
	}
	
	/**
	 * 设置：姓名
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) 
	{
		this.sex = sex;
	}
	
	/**
	 * 获取：性别
	 */
	public String getSex() 
	{
		return sex;
	}
	
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) 
	{
		this.age = age;
	}
	
	/**
	 * 获取：年龄
	 */
	public Integer getAge() 
	{
		return age;
	}
	
	/**
	 * 设置：民族
	 */
	public void setNation(Long nation) 
	{
		this.nation = nation;
	}
	
	/**
	 * 获取：民族
	 */
	public Long getNation() 
	{
		return nation;
	}
	
	/**
	 * 设置：身份证号
	 */
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	/**
	 * 设置：银行卡号
	 */
	public void setBankCardNumber(String bankCardNumber) 
	{
		this.bankCardNumber = bankCardNumber;
	}
	
	/**
	 * 获取：银行卡号
	 */
	public String getBankCardNumber() 
	{
		return bankCardNumber;
	}
	
	/**
	 * 设置：楼号
	 */
	public void setBuild(String build) 
	{
		this.build = build;
	}
	
	/**
	 * 获取：楼号
	 */
	public String getBuild() 
	{
		return build;
	}
	
	/**
	 * 设置：照片
	 */
	public void setPhoto(String photo) 
	{
		this.photo = photo;
	}
	
	/**
	 * 获取：照片
	 */
	public String getPhoto() 
	{
		return photo;
	}
	
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}
	
	/**
	 * 获取：手机号
	 */
	public String getMobile() 
	{
		return mobile;
	}
	
	/**
	 * 设置：监区
	 */
	public void setArea(String area) 
	{
		this.area = area;
	}
	
	/**
	 * 获取：监区
	 */
	public String getArea() 
	{
		return area;
	}
	
	/**
	 * 设置：房间
	 */
	public void setRoom(String room) 
	{
		this.room = room;
	}
	
	/**
	 * 获取：房间
	 */
	public String getRoom() 
	{
		return room;
	}
	
	/**
	 * 设置：床号
	 */
	public void setBed(String bed) 
	{
		this.bed = bed;
	}
	
	/**
	 * 获取：床号
	 */
	public String getBed() 
	{
		return bed;
	}

	/**
	 * 设置：身份
	 */
	public Long getIdentityId() {
		return identityId;
	}

	public void setIdentityId(Long identityId) {
		this.identityId = identityId;
	}

	/**
	 * 设置：密码（用于登录）
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	/**
	 * 获取：密码（用于登录）
	 */
	public String getPassword() 
	{
		return password;
	}
	
	/**
	 * 设置：盐加密
	 */
	public void setSalt(String salt) 
	{
		this.salt = salt;
	}
	
	/**
	 * 获取：盐加密
	 */
	public String getSalt() 
	{
		return salt;
	}
	
	/**
	 * 设置：押金
	 */
	public void setDeposit(BigDecimal deposit) 
	{
		this.deposit = deposit;
	}
	
	/**
	 * 获取：押金
	 */
	public BigDecimal getDeposit() 
	{
		return deposit;
	}
	
	/**
	 * 设置：账户余额
	 */
	public void setBalance(BigDecimal balance) 
	{
		this.balance = balance;
	}
	
	/**
	 * 获取：账户余额
	 */
	public BigDecimal getBalance() 
	{
		return balance;
	}
	
	/**
	 * 设置：当月已经消费
	 */
	public void setAlreadyCost(BigDecimal alreadyCost) 
	{
		this.alreadyCost = alreadyCost;
	}
	
	/**
	 * 获取：当月已经消费
	 */
	public BigDecimal getAlreadyCost() 
	{
		return alreadyCost;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 设置：状态
	 */
	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	/**
	 * 获取：状态
	 */
	public String getStatus() 
	{
		return status;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the recharge
	 */
	public BigDecimal getRecharge() {
		return recharge;
	}

	/**
	 * @param recharge the recharge to set
	 */
	public void setRecharge(BigDecimal recharge) {
		this.recharge = recharge;
	}
	/**
	 * 生成随机盐
	 */
	public void randomSalt()
	{
		// 一个Byte占两个字节，此处生成的3字节，字符串长度为6
		SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
		String hex = secureRandom.nextBytes(3).toHex();
		setSalt(hex);
	}

}
