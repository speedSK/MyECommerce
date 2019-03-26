package com.ruoyi.project.business.account.controller;

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
@RequestMapping("/business/account")
public class AccountController extends BaseController
{
    private String prefix = "business/account";

    @Autowired
    private IAccountService accountService;

    @RequiresPermissions("business:account:view")
    @GetMapping()
    public String account()
    {
        return prefix + "/account";
    }

    /**
     * 查询用户账户列表
     */
    @RequiresPermissions("business:account:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Account account)
    {
        startPage();
        List<Account> list = accountService.selectAccountList(account);
        return getDataTable(list);
    }


    /**
     * 导出用户账户列表
     */
    @RequiresPermissions("business:account:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Account account)
    {
        List<Account> list = accountService.selectAccountList(account);
        ExcelUtil<Account> util = new ExcelUtil<Account>(Account.class);
        return util.exportExcel(list, "account");
    }

    /**
     * 新增用户账户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户账户
     */
    @RequiresPermissions("business:account:add")
    @Log(title = "用户账户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Account account)
    {
        return toAjax(accountService.insertAccount(account));
    }

    /**
     * 修改用户账户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
		Account account = accountService.selectAccountById(id);
        mmap.put("account", account);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户账户
     */
    @RequiresPermissions("business:account:edit")
    @Log(title = "用户账户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Account account)
    {
        return toAjax(accountService.updateAccount(account));
    }

    /**
     * 删除用户账户
     */
    @RequiresPermissions("business:account:remove")
    @Log(title = "用户账户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(accountService.deleteAccountByIds(ids));
    }

}
