package com.information.project.system.modifyPwd.controller;

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
import com.information.project.system.modifyPwd.domain.ModifyPwd;
import com.information.project.system.modifyPwd.service.IModifyPwdService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 密码修改申请 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
@Controller
@RequestMapping("/system/modifyPwd")
public class ModifyPwdController extends BaseController
{
    private String prefix = "system/modifyPwd";
	
	@Autowired
	private IModifyPwdService modifyPwdService;
	
	@RequiresPermissions("system:modifyPwd:view")
	@GetMapping()
	public String modifyPwd()
	{
	    return prefix + "/modifyPwd";
	}
	
	/**
	 * 查询密码修改申请列表
	 */
	@RequiresPermissions("system:modifyPwd:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ModifyPwd modifyPwd)
	{
		startPage();
        List<ModifyPwd> list = modifyPwdService.selectModifyPwdList(modifyPwd);
		return getDataTable(list);
	}


	/**
	 * 密码修改审核申请
	 */
	@RequiresPermissions("system:modifyPwd:review")
	@Log(title = "密码审核申请", action = BusinessType.GRANT)
	@PostMapping( "/review")
	@ResponseBody
	public AjaxResult review(String ids,String agreest ,String des)
	{
		return toAjax(modifyPwdService.reviewModifyPwdByIds(ids,agreest,des));
	}



}
