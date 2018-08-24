package com.ruoyi.project.business.checkdetail.controller;

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
import com.ruoyi.project.business.checkdetail.domain.Checkdetail;
import com.ruoyi.project.business.checkdetail.service.ICheckdetailService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 银行详情对账对账 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Controller
@RequestMapping("/business/checkdetail")
public class CheckdetailController extends BaseController
{
    private String prefix = "business/checkdetail";
	
	@Autowired
	private ICheckdetailService checkdetailService;
	
	@RequiresPermissions("business:checkdetail:view")
	@GetMapping()
	public String checkdetail()
	{
	    return prefix + "/checkdetail";
	}
	
	/**
	 * 查询银行详情对账对账列表
	 */
	@RequiresPermissions("business:checkdetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Checkdetail checkdetail)
	{
		startPage();
        List<Checkdetail> list = checkdetailService.selectCheckdetailList(checkdetail);
		return getDataTable(list);
	}
	
	/**
	 * 新增银行详情对账对账
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存银行详情对账对账
	 */
	@RequiresPermissions("business:checkdetail:add")
	@Log(title = "银行详情对账对账", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Checkdetail checkdetail)
	{		
		return toAjax(checkdetailService.insertCheckdetail(checkdetail));
	}

	/**
	 * 修改银行详情对账对账
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Checkdetail checkdetail = checkdetailService.selectCheckdetailById(id);
		mmap.put("checkdetail", checkdetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存银行详情对账对账
	 */
	@RequiresPermissions("business:checkdetail:edit")
	@Log(title = "银行详情对账对账", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Checkdetail checkdetail)
	{		
		return toAjax(checkdetailService.updateCheckdetail(checkdetail));
	}
	
	/**
	 * 删除银行详情对账对账
	 */
	@RequiresPermissions("business:checkdetail:remove")
	@Log(title = "银行详情对账对账", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(checkdetailService.deleteCheckdetailByIds(ids));
	}
	
}
