package com.information.project.system.costTotal.controller;

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
import com.information.project.system.costTotal.domain.CostTotal;
import com.information.project.system.costTotal.service.ICostTotalService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 消费限额 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Controller
@RequestMapping("/system/costTotal")
public class CostTotalController extends BaseController
{
    private String prefix = "system/costTotal";
	
	@Autowired
	private ICostTotalService costTotalService;
	
	@RequiresPermissions("system:costTotal:view")
	@GetMapping()
	public String costTotal()
	{
	    return prefix + "/costTotal";
	}
	
	/**
	 * 查询消费限额列表
	 */
	@RequiresPermissions("system:costTotal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(CostTotal costTotal)
	{
		startPage();
        List<CostTotal> list = costTotalService.selectCostTotalList(costTotal);
		return getDataTable(list);
	}
	
	/**
	 * 新增消费限额
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存消费限额
	 */
	@RequiresPermissions("system:costTotal:add")
	@Log(title = "消费限额", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(CostTotal costTotal)
	{		
		return toAjax(costTotalService.insertCostTotal(costTotal));
	}

	/**
	 * 修改消费限额
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		CostTotal costTotal = costTotalService.selectCostTotalById(id);
		mmap.put("costTotal", costTotal);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存消费限额
	 */
	@RequiresPermissions("system:costTotal:edit")
	@Log(title = "消费限额", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(CostTotal costTotal)
	{		
		return toAjax(costTotalService.updateCostTotal(costTotal));
	}
	
	/**
	 * 删除消费限额
	 */
	@RequiresPermissions("system:costTotal:remove")
	@Log(title = "消费限额", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(costTotalService.deleteCostTotalByIds(ids));
	}
	
}
