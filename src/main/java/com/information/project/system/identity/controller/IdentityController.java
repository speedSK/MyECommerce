package com.information.project.module.identity.controller;

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
import com.information.project.module.identity.domain.Identity;
import com.information.project.module.identity.service.IIdentityService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 身份管理 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-10
 */
@Controller
@RequestMapping("/module/identity")
public class IdentityController extends BaseController
{
    private String prefix = "module/identity";
	
	@Autowired
	private IIdentityService identityService;
	
	@RequiresPermissions("module:identity:view")
	@GetMapping()
	public String identity()
	{
	    return prefix + "/identity";
	}
	
	/**
	 * 查询身份管理列表
	 */
	@RequiresPermissions("module:identity:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Identity identity)
	{
		startPage();
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
	@RequiresPermissions("module:identity:add")
	@Log(title = "身份管理", action = BusinessType.INSERT)
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
	@RequiresPermissions("module:identity:edit")
	@Log(title = "身份管理", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Identity identity)
	{		
		return toAjax(identityService.updateIdentity(identity));
	}
	
	/**
	 * 删除身份管理
	 */
	@RequiresPermissions("module:identity:remove")
	@Log(title = "身份管理", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(identityService.deleteIdentityByIds(ids));
	}
	
}
