package com.information.project.business.user.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.project.business.user.domain.BusUser;
import com.information.project.business.user.service.IPersonService;

/**
 * 用户管理
 * 
 * @author liuning
 *
 */
@Controller
@RequestMapping("/business/person")
public class PersonController extends BaseController{

	private String prefix = "/business/person";
	@Autowired
	private IPersonService personService;

	@RequiresPermissions("business:person:view")
	@GetMapping()
	public String person() {
		return prefix + "/person";
	}

	@RequiresPermissions("business:person:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusUser person) {
		startPage();
		List<BusUser> list = personService.selectPersonList(person);
		return getDataTable(list);
	}
}
