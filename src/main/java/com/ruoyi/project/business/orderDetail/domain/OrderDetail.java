package com.ruoyi.project.business.orderDetail.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情表 bus_order_detail
 * 
 * @author LiuNing
 * @date 2018-08-10
 */
public class OrderDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 订单id */
	@Excel(name="订单编号")
	private Long orderId;
	@Excel(name = "部门名称")
	private String deptName;
	@Excel(name="编号")
	private String personNumber;
	/** 商家id */
	private Long merchantId;
	/** 商家名称 */
	@Excel(name="商户名称")
	private String merchantName;
	/** 商品id */
	private Long goodsId;
	/** 商品名称 */
	@Excel(name="商品名称")
	private String goodsName;
	/** 商品单价 */
	@Excel(name="商品单价")
	private BigDecimal goodsPrice;
	/** 数量 */
	@Excel(name="商品数量")
	private Integer num;
	/** 金额 */
	@Excel(name="商品总金额")
	private BigDecimal money;
	/** 订单状态 */
	@Excel(name="订单状态",readConverterExp = "0=配送中,1=完成,2=取消")
	private String flag;
	/** 状态 */
	private String status;

	@Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
	private Date createTime;

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setOrderId(Long orderId) 
	{
		this.orderId = orderId;
	}

	public Long getOrderId() 
	{
		return orderId;
	}
	public void setMerchantId(Long merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Long getMerchantId() 
	{
		return merchantId;
	}
	public void setMerchantName(String merchantName) 
	{
		this.merchantName = merchantName;
	}

	public String getMerchantName() 
	{
		return merchantName;
	}
	public void setGoodsId(Long goodsId) 
	{
		this.goodsId = goodsId;
	}

	public Long getGoodsId() 
	{
		return goodsId;
	}
	public void setGoodsName(String goodsName) 
	{
		this.goodsName = goodsName;
	}

	public String getGoodsName() 
	{
		return goodsName;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) 
	{
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getGoodsPrice() 
	{
		return goodsPrice;
	}
	public void setNum(Integer num) 
	{
		this.num = num;
	}

	public Integer getNum() 
	{
		return num;
	}
	public void setMoney(BigDecimal money) 
	{
		this.money = money;
	}

	public BigDecimal getMoney() 
	{
		return money;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("merchantId", getMerchantId())
            .append("merchantName", getMerchantName())
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("goodsPrice", getGoodsPrice())
            .append("num", getNum())
            .append("money", getMoney())
            .append("flag", getFlag())
            .append("status", getStatus())
            .toString();
    }
}
