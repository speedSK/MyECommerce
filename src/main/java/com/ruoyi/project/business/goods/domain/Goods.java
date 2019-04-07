package com.ruoyi.project.business.goods.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.business.goodCategory.domain.GoodCategory;
import com.ruoyi.project.system.merchant.domain.Merchant;

import java.math.BigDecimal;

/**
 * 商品表 bus_goods
 * 
 * @author LiuNing
 * @date 2018-08-08
 */
public class Goods extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 分类id */
	@Excel(name="分类编号",type = Type.IMPORT)
	private Long categoryId;
	/** 商户id */
	@Excel(name="商户编号",type = Type.IMPORT)
	private Long merchantId;
	/** 编码 */
	@Excel(name="商品编码")
	private String code;
	/** 名称 */
	@Excel(name="商品名称")
	private String name;
	/** 价格 */
	@Excel(name="商品价格")
	private BigDecimal price;
	/** 规格 */
	@Excel(name="商品规格")
	private String spec;
	/** 单位 */
	@Excel(name="商品单位")
	private String unit;
	/** 显示顺序 */
	private Integer orderNum;
	/** 商品数量，仅用于页面展示 **/
	private Long num;
	/** 图片 */
	private String image;
	/** 显示状态 */
	@Excel(name="显示状态",readConverterExp = "0=不显示,1=显示")
	private String visible;
	/** 状态 */
	private String status;

	@Excel(name = "商品分类", targetAttr = "categoryName", type = Type.EXPORT)
	private GoodCategory goodCategory;
	@Excel(name = "商户", targetAttr = "merchantName", type = Type.EXPORT)
	private Merchant merchant;

	public GoodCategory getGoodCategory() {
		return goodCategory;
	}

	public void setGoodCategory(GoodCategory goodCategory) {
		this.goodCategory = goodCategory;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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
	 * 设置：分类id
	 */
	public void setCategoryId(Long categoryId) 
	{
		this.categoryId = categoryId;
	}
	
	/**
	 * 获取：分类id
	 */
	public Long getCategoryId() 
	{
		return categoryId;
	}
	
	/**
	 * 设置：商户id
	 */
	public void setMerchantId(Long merchantId) 
	{
		this.merchantId = merchantId;
	}
	
	/**
	 * 获取：商户id
	 */
	public Long getMerchantId() 
	{
		return merchantId;
	}
	
	/**
	 * 设置：编码
	 */
	public void setCode(String code) 
	{
		this.code = code;
	}
	
	/**
	 * 获取：编码
	 */
	public String getCode() 
	{
		return code;
	}
	
	/**
	 * 设置：名称
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：名称
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) 
	{
		this.price = price;
	}
	
	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() 
	{
		return price;
	}
	public void setSpec(String spec)
	{
		this.spec = spec;
	}

	public String getSpec()
	{
		return spec;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public String getUnit()
	{
		return unit;
	}
	public void setOrderNum(Integer orderNum) 
	{
		this.orderNum = orderNum;
	}
	
	/**
	 * 获取：显示顺序
	 */
	public Integer getOrderNum() 
	{
		return orderNum;
	}
	
	/**
	 * 设置：图片
	 */
	public void setImage(String image) 
	{
		this.image = image;
	}
	
	/**
	 * 获取：图片
	 */
	public String getImage() 
	{
		return image;
	}
	
	/**
	 * 设置：分类状态
	 */
	public void setVisible(String visible) 
	{
		this.visible = visible;
	}
	
	/**
	 * 获取：分类状态
	 */
	public String getVisible() 
	{
		return visible;
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
	/**
	 * 获取：数量
	 */
	public Long getNum() {
		return num;
	}
	/**
	 * 设置：数量
	 */
	public void setNum(Long num) {
		this.num = num;
	}

}
