package com.information.project.business.rechargeRecord.controller;

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
import com.information.project.business.rechargeRecord.domain.RechargeRecord;
import com.information.project.business.rechargeRecord.service.IRechargeRecordService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 银行转账 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Controller
@RequestMapping("/business/rechargeRecord")
public class RechargeRecordController extends BaseController
{
    private String prefix = "business/rechargeRecord";
	
	@Autowired
	private IRechargeRecordService rechargeRecordService;
	
	@RequiresPermissions("business:rechargeRecord:view")
	@GetMapping()
	public String rechargeRecord()
	{
	    return prefix + "/rechargeRecord";
	}
	
	/**
	 * 查询银行转账列表
	 */
	@RequiresPermissions("business:rechargeRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(RechargeRecord rechargeRecord)
	{
		startPage();
        List<RechargeRecord> list = rechargeRecordService.selectRechargeRecordList(rechargeRecord);
		return getDataTable(list);
	}
	
	/**
	 * 新增银行转账
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存银行转账
	 */
	@RequiresPermissions("business:rechargeRecord:add")
	@Log(title = "银行转账", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(RechargeRecord rechargeRecord)
	{		
		return toAjax(rechargeRecordService.insertRechargeRecord(rechargeRecord));
	}

	/**
	 * 修改银行转账
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		RechargeRecord rechargeRecord = rechargeRecordService.selectRechargeRecordById(id);
		mmap.put("rechargeRecord", rechargeRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存银行转账
	 */
	@RequiresPermissions("business:rechargeRecord:edit")
	@Log(title = "银行转账", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(RechargeRecord rechargeRecord)
	{		
		return toAjax(rechargeRecordService.updateRechargeRecord(rechargeRecord));
	}
	
	/**
	 * 删除银行转账
	 */
	@RequiresPermissions("business:rechargeRecord:remove")
	@Log(title = "银行转账", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(rechargeRecordService.deleteRechargeRecordByIds(ids));
	}
	
}
