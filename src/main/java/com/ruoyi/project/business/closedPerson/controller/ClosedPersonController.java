package com.ruoyi.project.business.closedPerson.controller;

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
import com.ruoyi.project.business.closedPerson.domain.ClosedPerson;
import com.ruoyi.project.business.closedPerson.service.IClosedPersonService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 销户人员管理 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-11-12
 */
@Controller
@RequestMapping("/business/closedPerson")
public class ClosedPersonController extends BaseController
{
    private String prefix = "business/closedPerson";
	
	@Autowired
	private IClosedPersonService closedPersonService;
	
	@RequiresPermissions("business:closedPerson:view")
	@GetMapping()
	public String closedPerson()
	{
	    return prefix + "/closedPerson";
	}
	
	/**
	 * 查询销户人员管理列表
	 */
	@RequiresPermissions("business:closedPerson:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ClosedPerson closedPerson)
	{
		startPage();
        List<ClosedPerson> list = closedPersonService.selectClosedPersonList(closedPerson);
		return getDataTable(list);
	}

	@RequiresPermissions("business:closedPerson:export")
	@Log(title = "导出销户信息）", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(ClosedPerson closedPerson)
	{
		List<ClosedPerson> list = closedPersonService.selectClosedPersonList(closedPerson);
		ExcelUtil<ClosedPerson> util = new ExcelUtil<ClosedPerson>(ClosedPerson.class);
		return util.exportExcel(list, "销户信息");
	}
	
	/**
	 * 新增销户人员管理
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存销户人员管理
	 */
	@RequiresPermissions("business:closedPerson:add")
	@Log(title = "销户人员管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ClosedPerson closedPerson)
	{		
		return toAjax(closedPersonService.insertClosedPerson(closedPerson));
	}

	/**
	 * 修改销户人员管理
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ClosedPerson closedPerson = closedPersonService.selectClosedPersonById(id);
		mmap.put("closedPerson", closedPerson);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存销户人员管理
	 */
	@RequiresPermissions("business:closedPerson:edit")
	@Log(title = "销户人员管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ClosedPerson closedPerson)
	{		
		return toAjax(closedPersonService.updateClosedPerson(closedPerson));
	}
	
	/**
	 * 删除销户人员管理
	 */
	@RequiresPermissions("business:closedPerson:remove")
	@Log(title = "销户人员管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(closedPersonService.deleteClosedPersonByIds(ids));
	}
	
}
