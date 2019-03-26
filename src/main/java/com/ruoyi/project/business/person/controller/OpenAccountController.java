package com.ruoyi.project.business.person.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import com.ruoyi.project.system.identity.service.IIdentityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 人员开户管理
 * 
 * @author LiuNing
 * @date 2018-08-09
 */
@Controller
@RequestMapping("/business/openAccount")
public class OpenAccountController extends BaseController
{
    private String prefix = "business/openAccount";
	
	@Autowired
	private IPersonService personService;


	@RequiresPermissions("business:openAccount:view")
	@GetMapping()
	public String openAccount()
	{
	    return prefix + "/person";
	}
	
	/**
	 * 查询未开户列表
	 */
	@RequiresPermissions("business:openAccount:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Person person)
	{
		startPage();
        List<Person> list = personService.selectUnopenedList(person);
		return getDataTable(list);
	}
	
	/**
	 * 用户开户
	 */
	@RequiresPermissions("business:openAccount:openAccount")
	@Log(title = "用户开户", businessType = BusinessType.UPDATE)
	@PostMapping( "/openAccount")
	@ResponseBody
	public AjaxResult openAccount(String ids)
	{

		return toAjax(personService.openAccount(ids));
	}

}
