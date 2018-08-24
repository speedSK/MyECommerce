package com.ruoyi.project.business.goodCategory.controller;

import java.util.List;
import java.util.Map;
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
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.goodCategory.domain.GoodCategory;
import com.ruoyi.project.business.goodCategory.service.IGoodCategoryService;

/**
 * 菜单信息
 *
 * @author LiuNing
 */
@Controller
@RequestMapping("/business/goodCategory")
public class GoodCategoryController extends BaseController
{

	private String prefix = "business/goodCategory";

	@Autowired
	private IGoodCategoryService goodCategoryService;

	@RequiresPermissions("business:goodCategory:view")
	@GetMapping()
	public String goodCategory()
	{
		return prefix + "/goodCategory";
	}

	@RequiresPermissions("business:goodCategory:list")
	@GetMapping("/list")
	@ResponseBody
	public List<GoodCategory> list(GoodCategory goodCategory)
	{
		List<GoodCategory> goodCategoryList = goodCategoryService.selectGoodCategoryList(goodCategory);
		return goodCategoryList;
	}

	/**
	 * 删除菜单
	 */
	@Log(title = "菜单管理", businessType = BusinessType.DELETE)
	@RequiresPermissions("business:goodCategory:remove")
	@PostMapping("/remove/{goodCategoryId}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("goodCategoryId") Long goodCategoryId)
	{
		if (goodCategoryService.selectCountGoodCategoryByParentId(goodCategoryId) > 0)
		{
			return error(1, "存在子菜单,不允许删除");
		}
		return toAjax(goodCategoryService.deleteGoodCategoryById(goodCategoryId));
	}

	/**
	 * 新增
	 */
	@GetMapping("/add/{parentId}")
	public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
	{
		GoodCategory goodCategory = null;
		if (0L != parentId)
		{
			goodCategory = goodCategoryService.selectGoodCategoryById(parentId);
		}
		else
		{
			goodCategory = new GoodCategory();
			goodCategory.setId(0L);
			goodCategory.setCategoryName("主目录");
		}
		mmap.put("goodCategory", goodCategory);
		return prefix + "/add";
	}

	/**
	 * 新增保存菜单
	 */
	@Log(title = "菜单管理", businessType = BusinessType.INSERT)
	@RequiresPermissions("business:goodCategory:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(GoodCategory goodCategory)
	{
		return toAjax(goodCategoryService.insertGoodCategory(goodCategory));
	}

	/**
	 * 修改菜单
	 */
	@GetMapping("/edit/{goodCategoryId}")
	public String edit(@PathVariable("goodCategoryId") Long goodCategoryId, ModelMap mmap)
	{
		mmap.put("goodCategory", goodCategoryService.selectGoodCategoryById(goodCategoryId));
		return prefix + "/edit";
	}

	/**
	 * 修改保存菜单
	 */
	@Log(title = "菜单管理", businessType = BusinessType.UPDATE)
	@RequiresPermissions("business:goodCategory:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(GoodCategory goodCategory)
	{
		return toAjax(goodCategoryService.updateGoodCategory(goodCategory));
	}

	/**
	 * 选择菜单图标
	 */
	@GetMapping("/icon")
	public String icon()
	{
		return prefix + "/icon";
	}

	/**
	 * 校验菜单名称
	 */
	@PostMapping("/checkGoodCategoryNameUnique")
	@ResponseBody
	public String checkGoodCategoryNameUnique(GoodCategory goodCategory)
	{
		String uniqueFlag = "0";
		if (goodCategory != null)
		{
			uniqueFlag = goodCategoryService.checkGoodCategoryNameUnique(goodCategory);
		}
		return uniqueFlag;
	}



	/**
	 * 加载所有菜单列表树
	 */
	@GetMapping("/goodCategoryTreeData")
	@ResponseBody
	public List<Map<String, Object>> goodCategoryTreeData()
	{
		List<Map<String, Object>> tree = goodCategoryService.goodCategoryTreeData();
		return tree;
	}

	/**
	 * 选择菜单树
	 */
	@GetMapping("/selectGoodCategoryTree/{goodCategoryId}")
	public String selectGoodCategoryTree(@PathVariable("goodCategoryId") Long goodCategoryId, ModelMap mmap)
	{
		mmap.put("treeName", goodCategoryService.selectGoodCategoryById(goodCategoryId).getCategoryName());
		return prefix + "/tree";
	}

}