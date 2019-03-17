package com.ruoyi.project.business.merchantReport.controller;

import java.util.List;

import com.ruoyi.common.utils.poi.ExcelUtil;
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
import com.ruoyi.project.business.merchantReport.domain.MerchantReport;
import com.ruoyi.project.business.merchantReport.service.IMerchantReportService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 系统账户报表 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-15
 */
@Controller
@RequestMapping("/business/MerchantReport")
public class MerchantReportController extends BaseController
{
    private String prefix = "business/MerchantReport";
	
	@Autowired
	private IMerchantReportService MerchantReportService;
	
	@RequiresPermissions("business:MerchantReport:view")
	@GetMapping()
	public String MerchantReport()
	{
	    return prefix + "/MerchantReport";
	}
	
	/**
	 * 查询系统账户报列表
	 */
	@RequiresPermissions("business:MerchantReport:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MerchantReport MerchantReport)
	{
		startPage();
        List<MerchantReport> list = MerchantReportService.selectMerchantReportList(MerchantReport);
		return getDataTable(list);
	}

	/**
	 * 查询系统账户报列表
	 */
    @Log(title = "系统账户报", businessType = BusinessType.EXPORT)
	@RequiresPermissions("business:MerchantReport:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(MerchantReport MerchantReport)
	{
		List<MerchantReport> list = MerchantReportService.selectMerchantReportList(MerchantReport);
        ExcelUtil<MerchantReport> util = new ExcelUtil<MerchantReport>(MerchantReport.class);
        return util.exportExcel(list, "MerchantReport");
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
	@RequiresPermissions("business:MerchantReport:add")
	@Log(title = "系统账户报", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MerchantReport MerchantReport)
	{		
		return toAjax(MerchantReportService.insertMerchantReport(MerchantReport));
	}

	/**
	 * 修改系统账户报
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		MerchantReport MerchantReport = MerchantReportService.selectMerchantReportById(id);
		mmap.put("MerchantReport", MerchantReport);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存系统账户报
	 */
	@RequiresPermissions("business:MerchantReport:edit")
	@Log(title = "系统账户报", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MerchantReport MerchantReport)
	{		
		return toAjax(MerchantReportService.updateMerchantReport(MerchantReport));
	}
	
	/**
	 * 删除系统账户报
	 */
	@RequiresPermissions("business:MerchantReport:remove")
	@Log(title = "系统账户报", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(MerchantReportService.deleteMerchantReportByIds(ids));
	}
	
}
