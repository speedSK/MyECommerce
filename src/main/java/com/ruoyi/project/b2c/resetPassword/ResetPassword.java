package com.ruoyi.project.b2c.resetPassword;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import com.ruoyi.project.system.modifyPwd.domain.ModifyPwd;
import com.ruoyi.project.system.modifyPwd.service.IModifyPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("b2c/resetPwd")
public class ResetPassword extends BaseController {
    private String prefix = "b2c/resetPwd";
    @Autowired
    private IPersonService personService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private IModifyPwdService modifyPwdService;

    @GetMapping("/resetPwd")
    public String resetPwd(ModelMap mmap)
    {
        Person person = getPerson();
        mmap.put("person", personService.selectPersonById(person.getId()));
        return prefix + "/resetPwd";
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(String oldPassword, String newPassword)
    {
        Person person = getPerson();
        if (StringUtils.isNotEmpty(newPassword) && passwordService.matches(person, oldPassword))
        {
            ModifyPwd modifyPwd = new ModifyPwd();
            modifyPwd.setUserid(person.getId());
            modifyPwd.setName(person.getName());
            modifyPwd.setNumber(person.getNumber());
            modifyPwd.setNewPwd(newPassword);
            modifyPwd.setStatus(Constants.STATUS_ACTIVE);
            return toAjax(modifyPwdService.insertIntoModifyPwd(modifyPwd));
        }
        else
        {
            return error("修改密码失败，旧密码错误");
        }

    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password)
    {
        Person person = getPerson();
        if (passwordService.matches(person, password))
        {
            return true;
        }
        return false;
    }
}
