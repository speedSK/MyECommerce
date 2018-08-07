package com.information.project.system.merchant.controller;

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
import com.information.framework.aspectj.lang.annotation.Log;
import com.information.framework.aspectj.lang.constant.BusinessType;
import com.information.project.system.merchant.domain.Merchant;
import com.information.project.system.merchant.service.IMerchantService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

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
	@Log(title = "商户", action = BusinessType.INSERT)
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
	@Log(title = "商户", action = BusinessType.UPDATE)
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
	@Log(title = "商户", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(merchantService.deleteMerchantByIds(ids));
	}
	
}
