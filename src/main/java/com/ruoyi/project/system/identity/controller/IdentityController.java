package com.ruoyi.project.system.identity.controller;

import java.util.List;

import com.ruoyi.common.constant.Constants;
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
import com.ruoyi.project.system.identity.domain.Identity;
import com.ruoyi.project.system.identity.service.IIdentityService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 身份管理 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-10
 */
@Controller
@RequestMapping("/system/identity")
public class IdentityController extends BaseController
{
    private String prefix = "system/identity";
	
	@Autowired
	private IIdentityService identityService;
	
	@RequiresPermissions("system:identity:view")
	@GetMapping()
	public String identity()
	{
	    return prefix + "/identity";
	}
	
	/**
	 * 查询身份管理列表
	 */
	@RequiresPermissions("system:identity:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Identity identity)
	{
		startPage();
		identity.setStatus(Constants.STATUS_ACTIVE);
        List<Identity> list = identityService.selectIdentityList(identity);
		return getDataTable(list);
	}
	
	/**
	 * 新增身份管理
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存身份管理
	 */
	@RequiresPermissions("system:identity:add")
	@Log(title = "身份管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Identity identity)
	{		
		return toAjax(identityService.insertIdentity(identity));
	}

	/**
	 * 修改身份管理
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Identity identity = identityService.selectIdentityById(id);
		mmap.put("identity", identity);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存身份管理
	 */
	@RequiresPermissions("system:identity:edit")
	@Log(title = "身份管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Identity identity)
	{		
		return toAjax(identityService.updateIdentity(identity));
	}
	
	/**
	 * 删除身份管理
	 */
	@RequiresPermissions("system:identity:remove")
	@Log(title = "身份管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(identityService.deleteIdentityByIds(ids));
	}
	
}
