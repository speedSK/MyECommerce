package com.ruoyi.project.business.uploadRecord.controller;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.business.uploadRecord.domain.BatchCostVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.business.uploadRecord.domain.UploadRecord;
import com.ruoyi.project.business.uploadRecord.service.IUploadRecordService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 功能导入记录 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-08
 */
@Controller
@RequestMapping("/business/batchCost")
public class BatchCostController extends BaseController
{
	private static final Logger log = LoggerFactory.getLogger(BatchCostController.class);
    private String prefix = "business/uploadRecord";
	
	@Autowired
	private IUploadRecordService uploadRecordService;
	
	@RequiresPermissions("business:batchCost:view")
	@GetMapping()
	public String batchCost()
	{
	    return prefix + "/batchCost";
	}
	
	/**
	 * 查询功能导入记录列表
	 */
	@RequiresPermissions("business:batchCost:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UploadRecord uploadRecord)
	{
		startPage();
		uploadRecord.setModule("批量消费导入");
		uploadRecord.setStatus(Constants.STATUS_ACTIVE);
        List<UploadRecord> list = uploadRecordService.selectUploadRecordList(uploadRecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 删除功能导入记录
	 */
	@RequiresPermissions("business:batchCost:remove")
	@Log(title = "批量消费导入", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(uploadRecordService.deleteUploadRecordByIds(ids));
	}

	@Log(title = "批量消费导入", businessType = BusinessType.IMPORT)
	@RequiresPermissions("business:batchCost:import")
	@PostMapping("/importData")
	@ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport)
    {
        try
        {
            if (!file.isEmpty())
            {
            	return uploadRecordService.saveCost(file);
            }
            return error();
        }
        catch (Exception e)
        {
            log.error("批量导入文件失败！", e);
            return error(e.getMessage());
        }
    }

	@RequiresPermissions("business:batchCost:view")
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate()
	{
		ExcelUtil<BatchCostVo> util = new ExcelUtil<BatchCostVo>(BatchCostVo.class);
		return util.importTemplateExcel("批量消费");
	}

//	@Log(title = "导入（犯人）管理", businessType = BusinessType.IMPORT)
//	@RequiresPermissions("business:batchCost:import")
//	@PostMapping("/importData")
//	@ResponseBody
//	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
//	{
//		ExcelUtil<UploadRecord> util = new ExcelUtil<UploadRecord>(UploadRecord.class);
//		List<UploadRecord> userList = util.importExcel(file.getInputStream());
//		String message = batchCostService.importUser(userList, updateSupport);
//		return AjaxResult.success(message);
//	}

	/**
	 * 导出批量消费记录
	 */
	@RequiresPermissions("business:batchCost:export")
	@Log(title = "导出批量消费记录", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(UploadRecord uploadRecord)
	{
		uploadRecord.setModule("批量消费导入");
		uploadRecord.setStatus(Constants.STATUS_ACTIVE);
		List<UploadRecord> list = uploadRecordService.selectUploadRecordList(uploadRecord);
		ExcelUtil<UploadRecord> util = new ExcelUtil<UploadRecord>(UploadRecord.class);
		return util.exportExcel(list, "batchCost");
	}
}
