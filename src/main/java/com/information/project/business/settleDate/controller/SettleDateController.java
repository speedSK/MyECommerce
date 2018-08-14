package com.information.project.business.settleDate.controller;

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
import com.information.project.business.settleDate.domain.SettleDate;
import com.information.project.business.settleDate.service.ISettleDateService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 系统结账日期 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-14
 */
@Controller
@RequestMapping("/business/settleDate")
public class SettleDateController extends BaseController
{
    private String prefix = "business/settleDate";
	
	@Autowired
	private ISettleDateService settleDateService;
	
	@RequiresPermissions("business:settleDate:view")
	@GetMapping()
	public String settleDate()
	{
	    return prefix + "/settleDate";
	}
	
	/**
	 * 查询系统结账日期列表
	 */
	@RequiresPermissions("business:settleDate:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SettleDate settleDate)
	{
		startPage();
        List<SettleDate> list = settleDateService.selectSettleDateList(settleDate);
		return getDataTable(list);
	}
	
	/**
	 * 新增系统结账日期
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存系统结账日期
	 */
	@RequiresPermissions("business:settleDate:add")
	@Log(title = "系统结账日期", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SettleDate settleDate)
	{		
		return toAjax(settleDateService.insertSettleDate(settleDate));
	}

	/**
	 * 修改系统结账日期
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SettleDate settleDate = settleDateService.selectSettleDateById(id);
		mmap.put("settleDate", settleDate);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存系统结账日期
	 */
	@RequiresPermissions("business:settleDate:edit")
	@Log(title = "系统结账日期", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SettleDate settleDate)
	{		
		return toAjax(settleDateService.updateSettleDate(settleDate));
	}
	
	/**
	 * 删除系统结账日期
	 */
	@RequiresPermissions("business:settleDate:remove")
	@Log(title = "系统结账日期", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(settleDateService.deleteSettleDateByIds(ids));
	}
	
}
