package com.ruoyi.project.business.person.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ExportPDF;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import com.ruoyi.project.common.CommonController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 销户类
 */
@Controller
@RequestMapping("/business/closeAccount")
public class CloseAccountController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(CloseAccountController.class);
    private String prefix = "business/closeAccount";

    @Autowired
    private IPersonService personService;

    @RequiresPermissions("business:closeAccount:view")
    @GetMapping()
    public String person()
    {
        return prefix + "/closeAccount";
    }

    /**
     * 可销户（犯人）列表
     */
    @RequiresPermissions("business:closeAccount:list")
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
    @RequiresPermissions("business:closeAccount:close")
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
    @RequiresPermissions("business:closeAccount:createPDF")
    @Log(title = "生成销户单", businessType = BusinessType.EXPORT)
    @GetMapping( "/createPDF/{id}")
    public void createPDF(@PathVariable("id") Long id, HttpServletResponse response, HttpServletRequest request)
    {
        try {
            Person person = personService.selectPersonById(id);
//            String template = ResourceUtils.getURL("classpath:").getPath() + "static/file/销户模板.pdf";
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/file/销户模板.pdf");
            String filePath = RuoYiConfig.getDownloadPath() + person.getNumber() + ".pdf";

            String[] str = {person.getNumber(), person.getName(), person.getDept().getDeptName(), person.getDeposit().add(person.getBalance()).toString(), ShiroUtils.getLoginName(), ShiroUtils.getIp(), DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, person.getUpdateTime())};
            ExportPDF.createPDF(is,filePath,str);

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + CommonController.setFileDownloadHeader(request, person.getNumber() + ".pdf"));
            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (IOException e) {
            log.error("生成pdf出错", e);
        }
    }

}
