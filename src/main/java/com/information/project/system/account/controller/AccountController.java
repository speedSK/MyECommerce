package com.information.project.system.account.controller;

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
import com.information.project.system.account.domain.Account;
import com.information.project.system.account.service.IAccountService;
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
@RequestMapping("/system/account")
public class AccountController extends BaseController
{
    private String prefix = "system/account";
	
	@Autowired
	private IAccountService accountService;
	
	@RequiresPermissions("system:account:view")
	@GetMapping()
	public String account()
	{
	    return prefix + "/account";
	}
	
	/**
	 * 查询商户列表
	 */
	@RequiresPermissions("system:account:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Account account)
	{
		startPage();
        List<Account> list = accountService.selectAccountList(account);
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
	@RequiresPermissions("system:account:add")
	@Log(title = "商户", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Account account)
	{		
		return toAjax(accountService.insertAccount(account));
	}

	/**
	 * 修改商户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Account account = accountService.selectAccountById(id);
		mmap.put("account", account);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商户
	 */
	@RequiresPermissions("system:account:edit")
	@Log(title = "商户", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Account account)
	{		
		return toAjax(accountService.updateAccount(account));
	}
	
	/**
	 * 删除商户
	 */
	@RequiresPermissions("system:account:remove")
	@Log(title = "商户", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(accountService.deleteAccountByIds(ids));
	}
	
}
