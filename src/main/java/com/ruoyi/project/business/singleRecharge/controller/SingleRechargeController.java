package com.ruoyi.project.business.singleRecharge.controller;

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

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;

/**
 * 单笔充值 信息操作处理
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
	private IPersonService personService;

	@RequiresPermissions("business:singleRecharge:view")
	@GetMapping()
	public String singleRecharge()
	{
	    return prefix + "/singleRecharge";
	}

	/**
	 * 现金充值页面
	 */
	@GetMapping("/query/{number}")
    @ResponseBody
	public AjaxResult query(@PathVariable("number") String number)
	{
		Person user = new Person();
		user.setNumber(number);
		user.setStatus(Constants.STATUS_ACTIVE);
		user.setFlag(Constants.PERSON_ACTIVE);
		List<Person> userlist = personService.selectPersonList(user);
		if (userlist!=null&&userlist.size()>0) {
			return success();
		}
		return error("用户不存在或状态异常");
	}

	/**
	 * 现金充值页面
	 */
	@GetMapping("/cash/{number}")
	public String cash(@PathVariable("number") String number, ModelMap mmap)
	{
		Person user = new Person();
		user.setNumber(number);
		user.setFlag(Constants.PERSON_ACTIVE);
		List<Person> userlist = personService.selectPersonList(user);
		if (userlist!=null&&userlist.size()>0) {
			user = userlist.get(0);
			mmap.put("user", user);
		}
	    return prefix + "/cash";
	}

	@PostMapping("/saveCash")
	@ResponseBody
	public AjaxResult saveCash(Person person)
	{		
		return toAjax(personService.saveCash(person));
	}

//	/**
//	 * 银行圈转页面
//	 */
//	@GetMapping("/bankRecharge/{number}")
//	public String bankRecharge(@PathVariable("number") String number, ModelMap mmap)
//	{
//		Person user = new Person();
//		user.setNumber(number);
//		List<Person> userlist = personService.selectPersonList(user);
//		if (userlist!=null&&userlist.size()>0) {
//			user = userlist.get(0);
//			String balance = personService.queryBankBalance(user);
//			user.setBankBalance(balance);
//			mmap.put("user", user);
//		}
//	    return prefix + "/bankRecharge";
//	}
//
//	@PostMapping("/saveBankCharge")
//	@ResponseBody
//	public AjaxResult saveBankCharge(Person person)
//	{
//		return toAjax(personService.saveBankCharge(person));
//	}
}
