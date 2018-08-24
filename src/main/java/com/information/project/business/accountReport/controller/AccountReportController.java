package com.information.project.business.accountReport.controller;

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
import com.information.project.business.accountReport.domain.AccountReport;
import com.information.project.business.accountReport.service.IAccountReportService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 系统账户报 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-15
 */
@Controller
@RequestMapping("/business/accountReport")
public class AccountReportController extends BaseController
{
    private String prefix = "business/accountReport";
	
	@Autowired
	private IAccountReportService accountReportService;
	
	@RequiresPermissions("business:accountReport:view")
	@GetMapping()
	public String accountReport()
	{
	    return prefix + "/accountReport";
	}
	
	/**
	 * 查询系统账户报列表
	 */
	@RequiresPermissions("business:accountReport:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AccountReport accountReport)
	{
		startPage();
        List<AccountReport> list = accountReportService.selectAccountReportList(accountReport);
		return getDataTable(list);
	}
	
	/**
	 * 新增系统账户报
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存系统账户报
	 */
	@RequiresPermissions("business:accountReport:add")
	@Log(title = "系统账户报", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AccountReport accountReport)
	{		
		return toAjax(accountReportService.insertAccountReport(accountReport));
	}

	/**
	 * 修改系统账户报
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		AccountReport accountReport = accountReportService.selectAccountReportById(id);
		mmap.put("accountReport", accountReport);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存系统账户报
	 */
	@RequiresPermissions("business:accountReport:edit")
	@Log(title = "系统账户报", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AccountReport accountReport)
	{		
		return toAjax(accountReportService.updateAccountReport(accountReport));
	}
	
	/**
	 * 删除系统账户报
	 */
	@RequiresPermissions("business:accountReport:remove")
	@Log(title = "系统账户报", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(accountReportService.deleteAccountReportByIds(ids));
	}
	
}
