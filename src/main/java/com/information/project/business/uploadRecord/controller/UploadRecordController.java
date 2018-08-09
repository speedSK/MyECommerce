package com.information.project.business.uploadRecord.controller;

import java.util.List;
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

import com.information.common.utils.poi.ExcelUtil;
import com.information.framework.aspectj.lang.annotation.Log;
import com.information.framework.aspectj.lang.constant.BusinessType;
import com.information.project.business.uploadRecord.domain.BatchRechargeVo;
import com.information.project.business.uploadRecord.domain.UploadRecord;
import com.information.project.business.uploadRecord.service.IUploadRecordService;
import com.information.project.system.user.controller.ProfileController;
import com.information.project.system.user.domain.User;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 功能导入记录 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-08
 */
@Controller
@RequestMapping("/business/uploadRecord")
public class UploadRecordController extends BaseController
{
	private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    private String prefix = "business/uploadRecord";
	
	@Autowired
	private IUploadRecordService uploadRecordService;
	
	@RequiresPermissions("business:uploadRecord:view")
	@GetMapping()
	public String uploadRecord()
	{
	    return prefix + "/uploadRecord";
	}
	
	/**
	 * 查询功能导入记录列表
	 */
	@RequiresPermissions("business:uploadRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UploadRecord uploadRecord)
	{
		startPage();
        List<UploadRecord> list = uploadRecordService.selectUploadRecordList(uploadRecord);
		return getDataTable(list);
	}
	
	/**
	 * 新增功能导入记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存功能导入记录
	 */
	@RequiresPermissions("business:uploadRecord:add")
	@Log(title = "功能导入记录", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(UploadRecord uploadRecord)
	{		
		return toAjax(uploadRecordService.insertUploadRecord(uploadRecord));
	}

	/**
	 * 修改功能导入记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		UploadRecord uploadRecord = uploadRecordService.selectUploadRecordById(id);
		mmap.put("uploadRecord", uploadRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存功能导入记录
	 */
	@RequiresPermissions("business:uploadRecord:edit")
	@Log(title = "功能导入记录", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(UploadRecord uploadRecord)
	{		
		return toAjax(uploadRecordService.updateUploadRecord(uploadRecord));
	}
	
	/**
	 * 删除功能导入记录
	 */
	@RequiresPermissions("business:uploadRecord:remove")
	@Log(title = "功能导入记录", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(uploadRecordService.deleteUploadRecordByIds(ids));
	}
	
	 /**
     * 批量导入数据
     */
    @Log(title = "批量导入", action = BusinessType.IMPORT)
    @PostMapping("/batchImport")
    @ResponseBody
    public AjaxResult batchImport(User user, @RequestParam("importfile") MultipartFile file)
    {
        try
        {
            if (!file.isEmpty())
            {
            	ExcelUtil<BatchRechargeVo> util = new ExcelUtil<BatchRechargeVo>(BatchRechargeVo.class);
            	List<BatchRechargeVo> list = util.importExcel("批量充值", file.getInputStream());
            	System.out.println(list.size());
            	uploadRecordService.saveRecharge(list);
//                String avatar = FileUploadUtils.upload(file);
//                user.setAvatar(avatar);
//                user.setUserId(1l);
//                if (userService.updateUserInfo(user) > 0)
//                {
//                    setUser(userService.selectUserById(user.getUserId()));
//                    return success();
//                }
            }
            return error();
        }
        catch (Exception e)
        {
            log.error("批量导入文件失败！", e);
            return error(e.getMessage());
        }
    }
}
