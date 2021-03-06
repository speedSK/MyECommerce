package com.ruoyi.project.business.person.controller;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.system.identity.service.IIdentityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 人员管理操作处理
 * 
 * @author LiuNing
 * @date 2018-08-09
 */
@Controller
@RequestMapping("/business/person")
public class PersonController extends BaseController
{
    private String prefix = "business/person";
	
	@Autowired
	private IPersonService personService;

	@Autowired
	private IIdentityService identityService;


	@RequiresPermissions("business:person:view")
	@GetMapping()
	public String person()
	{
	    return prefix + "/person";
	}
	
	/**
	 * 查询业务（犯人）列表
	 */
	@RequiresPermissions("business:person:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Person person)
	{
		startPage();
        List<Person> list = personService.selectPersonList(person);
		return getDataTable(list);
	}
	
	/**
	 * 新增业务（犯人）
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("identitys", identityService.selectPostAll());

		return prefix + "/add";
	}
	
	/**
	 * 新增保存业务（犯人）
	 */
	@RequiresPermissions("business:person:add")
	@Log(title = "添加人员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Person person)
	{		
		return toAjax(personService.insertPerson(person));
	}

	/**
	 * 修改业务（犯人）
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Person person = personService.selectPersonById(id);
		mmap.put("person", person);
		mmap.put("identitys", identityService.selectPostAll());
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存业务（犯人）
	 */
	@RequiresPermissions("business:person:edit")
	@Log(title = "修改人员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Person person)
	{		
		return toAjax(personService.updatePerson(person));
	}
	
	/**
	 * 删除业务（犯人）
	 */
	@RequiresPermissions("business:person:remove")
	@Log(title = "删除人员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(personService.deletePersonByIds(ids));
	}

	/**
	 * 冻结业务（犯人）
	 */
	@RequiresPermissions("business:person:editFlag")
	@Log(title = "冻结、解冻", businessType = BusinessType.UPDATE)
	@PostMapping( "/editFlag")
	@ResponseBody
	public AjaxResult editFlag(String ids , String visible)
	{
        Person person = new Person();
        person.setId(Long.valueOf(ids));
        switch (visible) {
            case "lock":
                person.setFlag(Constants.PERSON_LOCK);
                break;
            case "unlock":
                person.setFlag(Constants.PERSON_ACTIVE);
                break;
			case "prep":
				person.setFlag(Constants.PERSON_PREP);
				break;
        }
		return toAjax(personService.updatePerson(person));
	}

    @RequiresPermissions("business:person:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<Person> util = new ExcelUtil<Person>(Person.class);
        return util.importTemplateExcel("用户数据");
    }

	@Log(title = "导入管理", businessType = BusinessType.IMPORT)
	@RequiresPermissions("business:person:import")
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
	{
		ExcelUtil<Person> util = new ExcelUtil<Person>(Person.class);
		List<Person> userList = util.importExcel(file.getInputStream());
		String message = personService.importUser(userList, updateSupport);
		return AjaxResult.success(message);
	}

	/**
	 * 导出业务（犯人）列表
	 */
	@RequiresPermissions("business:person:export")
	@Log(title = "导出信息", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Person person)
	{
		List<Person> list = personService.selectPersonList(person);
        ExcelUtil<Person> util = new ExcelUtil<Person>(Person.class);
        return util.exportExcel(list, "person");
	}

	/**
	 * 保存图片
	 */
	@Log(title = "上传照片", businessType = BusinessType.UPDATE)
	@PostMapping("/updateAvatar")
	@ResponseBody
	public AjaxResult updateAvatar(Person person, @RequestParam("avatarfile") MultipartFile file)
	{
		try
		{
			if (!file.isEmpty())
			{
                String avatar = FileUploadUtils.upload(RuoYiConfig.getPhotosPath(), file);
				person.setPhoto(avatar);
				if (personService.updatePerson(person) > 0)
				{
					return success();
				}
			}
			return error();
		}
		catch (Exception e)
		{
			return error(e.getMessage());
		}
	}

	/**
	 * 修改图片
	 */

	@GetMapping("/avatar/{id}")
	public String avatar(@PathVariable("id") Long id, ModelMap mmap)
	{
		mmap.put("person", personService.selectPersonById(id));
		return prefix + "/avatar";
	}

	/**
	 * 校验用户编号
	 */
	@PostMapping("/checkNumberUnique")
	@ResponseBody
	public String checkNumberUnique(Person person)
	{
		return personService.checkNumberUnique(person.getNumber());
	}

	/**
	 * 查询人员详细信息
	 */
//	@RequiresPermissions("system:dict:list")
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, ModelMap mmap)
	{
		Person person = personService.selectPersonById(id);
		mmap.put("person", person);
		mmap.put("identitys", identityService.selectPostAll());
		return prefix + "/detail";
	}

	@RequiresPermissions("business:person:edit")
	@Log(title = "重置密码", businessType = BusinessType.UPDATE)
	@GetMapping("/resetPwd/{id}")
	public String resetPwd(@PathVariable("id") Long id, ModelMap mmap)
	{
		mmap.put("person", personService.selectPersonById(id));
		return prefix + "/resetPwd";
	}

	@RequiresPermissions("business:person:edit")
	@Log(title = "重置密码", businessType = BusinessType.UPDATE)
	@PostMapping("/resetPwd")
	@ResponseBody
	public AjaxResult resetPwd(Person person)
	{
		return toAjax(personService.resetPersonPwd(person));
	}

}
