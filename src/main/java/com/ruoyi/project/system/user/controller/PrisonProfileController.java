package com.ruoyi.project.system.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;

/**
 * 个人信息 业务处理
 *
 * @author CoderX
 */
@Controller
@RequestMapping("/system/user/prisonProfile")
public class PrisonProfileController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(PrisonProfileController.class);

    private String prefix = "system/user/prisonProfile";

    @Autowired
    private IPersonService personService;

    @Autowired
    private DictService dict;

    /**
     * 个人信息
     */
    @GetMapping()
    public String profile(ModelMap mmap)
    {
        Person user = getPerson();
        user.setSex(dict.getLabel("sys_user_sex", user.getSex()));
        mmap.put("user", user);
        return prefix + "/profile";
    }

}