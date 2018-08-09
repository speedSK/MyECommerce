package com.information.project.business.singleRecharge.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.information.framework.web.controller.BaseController;
import com.information.project.business.person.domain.Person;
import com.information.project.business.person.service.IPersonService;

/**
 * 银行总账对账 信息操作处理
 *
 * @author LiuNing
 * @date 2018-08-06
 */
@Controller
@RequestMapping("/business/singleRecharge")
public class SingleRechargeController extends BaseController
{
    private String prefix = "business/singleRecharge";

    @Autowired
	private IPersonService userService;

	@RequiresPermissions("business:singleRecharge:view")
	@GetMapping()
	public String checkbill()
	{
	    return prefix + "/singleRecharge";
	}

	/**
	 * 修改业务（犯人）
	 */
	@GetMapping("/cash/{id}")
	public String cash(@PathVariable("id") Long id, ModelMap mmap)
	{
		Person user = userService.selectPersonById(id);
		mmap.put("user", user);
	    return prefix + "/cash";
	}


	/**
	 * 修改业务（犯人）
	 */
	@GetMapping("/bankRecharge/{id}")
	public String bankRecharge(@PathVariable("id") Long id, ModelMap mmap)
	{
		Person user = userService.selectPersonById(id);
		mmap.put("user", user);
	    return prefix + "/bankRecharge";
	}
}
