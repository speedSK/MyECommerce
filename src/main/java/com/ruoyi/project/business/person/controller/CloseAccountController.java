package com.ruoyi.project.business.person.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 销户类
 */
@Controller
@RequestMapping("/business/account")
public class CloseAccountController extends BaseController {
    private String prefix = "business/account";

    @Autowired
    private IPersonService personService;

    @RequiresPermissions("business:account:view")
    @GetMapping()
    public String person()
    {
        return prefix + "/account";
    }

    /**
     * 可销户（犯人）列表
     */
    @RequiresPermissions("business:account:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Person person)
    {
        startPage();
        person.setFlag(Constants.PERSON_PREP);
        List<Person> list = personService.selectPersonList(person);
        return getDataTable(list);
    }

    /**
     * 销户业务（犯人）
     */
    @RequiresPermissions("business:account:close")
    @Log(title = "销户（犯人）", businessType = BusinessType.DELETE)
    @PostMapping( "/close")
    @ResponseBody
    public AjaxResult close(String ids)
    {
        return toAjax(personService.deletePersonAccount(ids));
    }

    /**
     * 生成销户单
     */
    @RequiresPermissions("business:account:createPDF")
    @Log(title = "生成销户单", businessType = BusinessType.EXPORT)
    @PostMapping( "/createPDF")
    @ResponseBody
    public AjaxResult createPDF(String ids)
    {
        return toAjax(personService.deletePersonAccount(ids));
    }

}
