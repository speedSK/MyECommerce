package com.information.project.business.person.controller;

import java.util.List;

import com.information.common.utils.file.FileUploadUtils;
import com.information.project.system.post.service.IPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.information.framework.aspectj.lang.annotation.Log;
import com.information.framework.aspectj.lang.constant.BusinessType;
import com.information.project.business.person.domain.Person;
import com.information.project.business.person.service.IPersonService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;
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
	private IPostService postService;


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
		mmap.put("posts", postService.selectPostAll());

		return prefix + "/add";
	}
	
	/**
	 * 新增保存业务（犯人）
	 */
	@RequiresPermissions("business:person:add")
	@Log(title = "业务（犯人）", action = BusinessType.INSERT)
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
		mmap.put("depts", postService.selectPostAll());
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存业务（犯人）
	 */
	@RequiresPermissions("business:person:edit")
	@Log(title = "业务（犯人）", action = BusinessType.UPDATE)
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
	@Log(title = "业务（犯人）", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(personService.deletePersonByIds(ids));
	}


	/**
	 * 保存图片
	 */
	@Log(title = "商品图片信息", action = BusinessType.UPDATE)
	@PostMapping("/updateAvatar")
	@ResponseBody
	public AjaxResult updateAvatar(Person person, @RequestParam("avatarfile") MultipartFile file)
	{
		try
		{
			if (!file.isEmpty())
			{
				String avatar = FileUploadUtils.upload(file);
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


}
