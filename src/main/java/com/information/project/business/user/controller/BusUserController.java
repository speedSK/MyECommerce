package com.information.project.business.user.controller;

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
import com.information.project.business.user.domain.BusUser;
import com.information.project.business.user.service.IBusUserService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 业务（犯人） 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Controller
@RequestMapping("/business/user")
public class BusUserController extends BaseController
{
    private String prefix = "business/user";
	
	@Autowired
	private IBusUserService userService;
	
	@RequiresPermissions("business:user:view")
	@GetMapping()
	public String user()
	{
	    return prefix + "/user";
	}
	
	/**
	 * 查询业务（犯人）列表
	 */
	@RequiresPermissions("business:user:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusUser user)
	{
		startPage();
        List<BusUser> list = userService.selectBusUserList(user);
		return getDataTable(list);
	}
	
	/**
	 * 新增业务（犯人）
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存业务（犯人）
	 */
	@RequiresPermissions("business:user:add")
	@Log(title = "业务（犯人）", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusUser user)
	{		
		return toAjax(userService.insertBusUser(user));
	}

	/**
	 * 修改业务（犯人）
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		BusUser user = userService.selectBusUserById(id);
		mmap.put("user", user);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存业务（犯人）
	 */
	@RequiresPermissions("business:user:edit")
	@Log(title = "业务（犯人）", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusUser user)
	{		
		return toAjax(userService.updateBusUser(user));
	}
	
	/**
	 * 删除业务（犯人）
	 */
	@RequiresPermissions("business:user:remove")
	@Log(title = "业务（犯人）", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(userService.deleteBusUserByIds(ids));
	}
	
}
