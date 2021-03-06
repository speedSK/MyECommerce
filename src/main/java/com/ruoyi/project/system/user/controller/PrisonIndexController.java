package com.ruoyi.project.system.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import com.ruoyi.project.system.identity.domain.Identity;
import com.ruoyi.project.system.identity.service.IIdentityService;
import com.ruoyi.project.system.menu.service.IMenuService;

/**
 * 首页 业务处理
 * 
 * @author CoderX
 */
@Controller
public class PrisonIndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;
    
    @Autowired
    private IIdentityService identityService;
    
    @Autowired
    private IPersonService personService;

    @Autowired
    private RuoYiConfig prisonConfig;

    // 系统首页
    @GetMapping("/prisonIndex")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        Person user = personService.selectPersonById(getPerson().getId());
      //身份信息(从身份信息获取消费限额)
  		Identity identity = identityService.selectIdentityById(user.getIdentityId());
        mmap.put("user", user);
        mmap.put("identity", identity);
        mmap.put("copyrightYear", prisonConfig.getCopyrightYear());
        return "prisonIndex";
    }

}
