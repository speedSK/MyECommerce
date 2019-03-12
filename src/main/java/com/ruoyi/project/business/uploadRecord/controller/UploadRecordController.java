package com.ruoyi.project.business.uploadRecord.controller;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.business.uploadRecord.domain.BatchRechargeVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/business/uploadRecord")
public class UploadRecordController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(BatchCostController.class);
    private String prefix = "business/uploadRecord";

    @Autowired
    private IUploadRecordService uploadRecordService;

    @RequiresPermissions("business:uploadRecord:view")
    @GetMapping()
    public String uploadRecord() {
        return prefix + "/uploadRecord";
    }

    /**
     * 查询功能导入记录列表
     */
    @RequiresPermissions("business:uploadRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UploadRecord uploadRecord) {
        startPage();
        uploadRecord.setModule("批量充值导入");
        uploadRecord.setStatus(Constants.STATUS_ACTIVE);
        List<UploadRecord> list = uploadRecordService.selectUploadRecordList(uploadRecord);
        return getDataTable(list);
    }

    /**
     * 删除功能导入记录
     */
    @RequiresPermissions("business:uploadRecord:remove")
    @Log(title = "功能导入删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(uploadRecordService.deleteUploadRecordByIds(ids));
    }

    /**
     * 批量导入数据
     */
    @Log(title = "批量充值导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("business:uploadRecord:import")
	@PostMapping("/importData")
	@ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) {
        try {
            if (!file.isEmpty()) {
                return uploadRecordService.saveRecharge(file);

            }
            return error();
        } catch (Exception e) {
            log.error("批量导入文件失败！", e);
            return error(e.getMessage());
        }
    }

    @RequiresPermissions("business:uploadRecord:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BatchRechargeVo> util = new ExcelUtil<BatchRechargeVo>(BatchRechargeVo.class);
        return util.importTemplateExcel("批量充值");
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
    @RequiresPermissions("business:uploadRecord:export")
    @Log(title = "导出批量充值记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UploadRecord uploadRecord)
    {
        uploadRecord.setModule("批量充值导入");
        uploadRecord.setStatus(Constants.STATUS_ACTIVE);
        List<UploadRecord> list = uploadRecordService.selectUploadRecordList(uploadRecord);
        ExcelUtil<UploadRecord> util = new ExcelUtil<UploadRecord>(UploadRecord.class);
        return util.exportExcel(list, "batchRecharge");
    }
}
