package com.information.project.system.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.information.framework.config.SystemConfig;
import com.information.framework.web.controller.BaseController;
import com.information.project.system.menu.domain.Menu;
import com.information.project.system.menu.service.IMenuService;
import com.information.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author LiuNing
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private SystemConfig prisonConfig;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", prisonConfig.getCopyrightYear());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", prisonConfig.getVersion());
        return "main";
    }

}
