package com.information.project.business.operReport.controller;

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
import com.information.project.business.operReport.domain.OperReport;
import com.information.project.business.operReport.service.IOperReportService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 系统操作报 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Controller
@RequestMapping("/business/operReport")
public class OperReportController extends BaseController
{
    private String prefix = "business/operReport";
	
	@Autowired
	private IOperReportService operReportService;
	
	@RequiresPermissions("business:operReport:view")
	@GetMapping()
	public String operReport()
	{
	    return prefix + "/operReport";
	}
	
	/**
	 * 查询系统操作报列表
	 */
	@RequiresPermissions("business:operReport:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OperReport operReport)
	{
		startPage();
        List<OperReport> list = operReportService.selectOperReportList(operReport);
		return getDataTable(list);
	}
	
	/**
	 * 新增系统操作报
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存系统操作报
	 */
	@RequiresPermissions("business:operReport:add")
	@Log(title = "系统操作报", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OperReport operReport)
	{		
		return toAjax(operReportService.insertOperReport(operReport));
	}

	/**
	 * 修改系统操作报
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		OperReport operReport = operReportService.selectOperReportById(id);
		mmap.put("operReport", operReport);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存系统操作报
	 */
	@RequiresPermissions("business:operReport:edit")
	@Log(title = "系统操作报", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OperReport operReport)
	{		
		return toAjax(operReportService.updateOperReport(operReport));
	}
	
	/**
	 * 删除系统操作报
	 */
	@RequiresPermissions("business:operReport:remove")
	@Log(title = "系统操作报", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(operReportService.deleteOperReportByIds(ids));
	}
	
}
