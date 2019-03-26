package com.ruoyi.project.business.account.controller;

import java.util.List;

import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
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
import com.ruoyi.project.business.account.domain.Account;
import com.ruoyi.project.business.account.service.IAccountService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 用户账户 信息操作处理
 *
 * @author ruoyi
 * @date 2019-03-26
 */
@Controller
@RequestMapping("/business/openAccount")
public class AccountController extends BaseController
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
