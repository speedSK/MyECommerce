package com.ruoyi.project.business.transactionRecord.controller;

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
import com.ruoyi.project.business.transactionRecord.domain.TransactionRecord;
import com.ruoyi.project.business.transactionRecord.service.ITransactionRecordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 银行转账 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
@Controller
@RequestMapping("/business/transactionRecord")
public class TransactionRecordController extends BaseController
{
    private String prefix = "business/transactionRecord";
	
	@Autowired
	private ITransactionRecordService transactionRecordService;
	
	@RequiresPermissions("business:transactionRecord:view")
	@GetMapping()
	public String transactionRecord()
	{
	    return prefix + "/transactionRecord";
	}
	
	/**
	 * 查询银行转账列表
	 */
	@RequiresPermissions("business:transactionRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TransactionRecord transactionRecord)
	{
		startPage();
        List<TransactionRecord> list = transactionRecordService.selectTransactionRecordList(transactionRecord);
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
	@RequiresPermissions("business:transactionRecord:add")
	@Log(title = "银行转账", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TransactionRecord transactionRecord)
	{		
		return toAjax(transactionRecordService.insertTransactionRecord(transactionRecord));
	}

	/**
	 * 修改银行转账
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TransactionRecord transactionRecord = transactionRecordService.selectTransactionRecordById(id);
		mmap.put("transactionRecord", transactionRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存银行转账
	 */
	@RequiresPermissions("business:transactionRecord:edit")
	@Log(title = "银行转账", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TransactionRecord transactionRecord)
	{		
		return toAjax(transactionRecordService.updateTransactionRecord(transactionRecord));
	}
	
	/**
	 * 删除银行转账
	 */
	@RequiresPermissions("business:transactionRecord:remove")
	@Log(title = "银行转账", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(transactionRecordService.deleteTransactionRecordByIds(ids));
	}
	
}
