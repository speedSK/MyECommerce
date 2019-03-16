package com.ruoyi.project.system.merchant.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.merchant.domain.Merchant;
import com.ruoyi.project.system.merchant.service.IMerchantService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 商户 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
@Controller
@RequestMapping("/system/merchant")
public class MerchantController extends BaseController
{
    private String prefix = "system/merchant";
	
	@Autowired
	private IMerchantService merchantService;
	
	@RequiresPermissions("system:merchant:view")
	@GetMapping()
	public String merchant()
	{
	    return prefix + "/merchant";
	}
	
	/**
	 * 查询商户列表
	 */
	@RequiresPermissions("system:merchant:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Merchant merchant)
	{
		startPage();
        List<Merchant> list = merchantService.selectMerchantList(merchant);
		return getDataTable(list);
	}
	
	/**
	 * 新增商户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商户
	 */
	@RequiresPermissions("system:merchant:add")
	@Log(title = "商户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Merchant merchant)
	{		
		return toAjax(merchantService.insertMerchant(merchant));
	}

	/**
	 * 修改商户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Merchant merchant = merchantService.selectMerchantById(id);
		mmap.put("merchant", merchant);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商户
	 */
	@RequiresPermissions("system:merchant:edit")
	@Log(title = "商户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Merchant merchant)
	{		
		return toAjax(merchantService.updateMerchant(merchant));
	}
	
	/**
	 * 删除商户
	 */
	@RequiresPermissions("system:merchant:remove")
	@Log(title = "商户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(merchantService.deleteMerchantByIds(ids));
	}

	@RequiresPermissions("business:person:settle")
	@Log(title = "商户结算", businessType = BusinessType.UPDATE)
	@PostMapping( "/settle")
	@ResponseBody
	public AjaxResult settle(String ids , String visible)
	{
		Merchant merchant = new Merchant();
		merchant.setId(Long.valueOf(ids));
		return toAjax(merchantService.settleMerchant(merchant));
	}
}
