package com.ruoyi.project.business.checkbill.controller;

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
import com.ruoyi.project.business.checkbill.domain.Checkbill;
import com.ruoyi.project.business.checkbill.service.ICheckbillService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 银行总账对账 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Controller
@RequestMapping("/business/checkbill")
public class CheckbillController extends BaseController
{
    private String prefix = "business/checkbill";
	
	@Autowired
	private ICheckbillService checkbillService;
	
	@RequiresPermissions("business:checkbill:view")
	@GetMapping()
	public String checkbill()
	{
	    return prefix + "/checkbill";
	}
	
	/**
	 * 查询银行总账对账列表
	 */
	@RequiresPermissions("business:checkbill:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Checkbill checkbill)
	{
		startPage();
        List<Checkbill> list = checkbillService.selectCheckbillList(checkbill);
		return getDataTable(list);
	}
	
	/**
	 * 新增银行总账对账
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存银行总账对账
	 */
	@RequiresPermissions("business:checkbill:add")
	@Log(title = "银行总账对账", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Checkbill checkbill)
	{		
		return toAjax(checkbillService.insertCheckbill(checkbill));
	}

	/**
	 * 修改银行总账对账
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Checkbill checkbill = checkbillService.selectCheckbillById(id);
		mmap.put("checkbill", checkbill);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存银行总账对账
	 */
	@RequiresPermissions("business:checkbill:edit")
	@Log(title = "银行总账对账", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Checkbill checkbill)
	{		
		return toAjax(checkbillService.updateCheckbill(checkbill));
	}
	
	/**
	 * 删除银行总账对账
	 */
	@RequiresPermissions("business:checkbill:remove")
	@Log(title = "银行总账对账", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(checkbillService.deleteCheckbillByIds(ids));
	}
	
}
