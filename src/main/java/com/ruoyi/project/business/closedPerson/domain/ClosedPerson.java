package com.ruoyi.project.business.closedPerson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销户人员管理表 bus_closed_person
 * 
 * @author ruoyi
 * @date 2018-11-12
 */
public class ClosedPerson extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 编号 */
	private String number;
	/** 姓名 */
	private String name;
	/** 性别 */
	private String sex;
	/** 年龄 */
	private Integer age;
	/** 民族 */
	private Long nation;
	/** 身份证号 */
	private String idcard;
	/** 银行卡号 */
	private String bankCardNumber;
	/** 楼号 */
	private String build;
	/** 照片 */
	private String photo;
	/** 手机号 */
	private String mobile;
	/** 监区 */
	private String area;
	/** 房间 */
	private String room;
	/** 床号 */
	private String bed;
	/** 部门 */
	private Long deptId;
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
	/** 说明 */
	private String remark;
	/** 创建人 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 修改人 */
	private String updateBy;
	/** 修改时间 */
	private Date updateTime;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getNumber() 
	{
		return number;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex() 
	{
		return sex;
	}
	public void setAge(Integer age) 
	{
		this.age = age;
	}

	public Integer getAge() 
	{
		return age;
	}
	public void setNation(Long nation) 
	{
		this.nation = nation;
	}

	public Long getNation() 
	{
		return nation;
	}
	public void setIdcard(String idcard) 
	{
		this.idcard = idcard;
	}

	public String getIdcard() 
	{
		return idcard;
	}
	public void setBankCardNumber(String bankCardNumber) 
	{
		this.bankCardNumber = bankCardNumber;
	}

	public String getBankCardNumber() 
	{
		return bankCardNumber;
	}
	public void setBuild(String build) 
	{
		this.build = build;
	}

	public String getBuild() 
	{
		return build;
	}
	public void setPhoto(String photo) 
	{
		this.photo = photo;
	}

	public String getPhoto() 
	{
		return photo;
	}
	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public String getMobile() 
	{
		return mobile;
	}
	public void setArea(String area) 
	{
		this.area = area;
	}

	public String getArea() 
	{
		return area;
	}
	public void setRoom(String room) 
	{
		this.room = room;
	}

	public String getRoom() 
	{
		return room;
	}
	public void setBed(String bed) 
	{
		this.bed = bed;
	}

	public String getBed() 
	{
		return bed;
	}
	public void setDeptId(Long deptId) 
	{
		this.deptId = deptId;
	}

	public Long getDeptId() 
	{
		return deptId;
	}
	public void setIdentityId(Long identityId) 
	{
		this.identityId = identityId;
	}

	public Long getIdentityId() 
	{
		return identityId;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}
	public void setSalt(String salt) 
	{
		this.salt = salt;
	}

	public String getSalt() 
	{
		return salt;
	}
	public void setDeposit(BigDecimal deposit) 
	{
		this.deposit = deposit;
	}

	public BigDecimal getDeposit() 
	{
		return deposit;
	}
	public void setBalance(BigDecimal balance) 
	{
		this.balance = balance;
	}

	public BigDecimal getBalance() 
	{
		return balance;
	}
	public void setAlreadyCost(BigDecimal alreadyCost) 
	{
		this.alreadyCost = alreadyCost;
	}

	public BigDecimal getAlreadyCost() 
	{
		return alreadyCost;
	}
	public void setFlag(String flag) 
	{
		this.flag = flag;
	}

	public String getFlag() 
	{
		return flag;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("number", getNumber())
            .append("name", getName())
            .append("sex", getSex())
            .append("age", getAge())
            .append("nation", getNation())
            .append("idcard", getIdcard())
            .append("bankCardNumber", getBankCardNumber())
            .append("build", getBuild())
            .append("photo", getPhoto())
            .append("mobile", getMobile())
            .append("area", getArea())
            .append("room", getRoom())
            .append("bed", getBed())
            .append("deptId", getDeptId())
            .append("identityId", getIdentityId())
            .append("password", getPassword())
            .append("salt", getSalt())
            .append("deposit", getDeposit())
            .append("balance", getBalance())
            .append("alreadyCost", getAlreadyCost())
            .append("flag", getFlag())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
