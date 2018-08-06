package com.information.project.business.goodCategory.controller;

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
import com.information.framework.aspectj.lang.annotation.Log;
import com.information.framework.aspectj.lang.constant.BusinessType;
import com.information.project.business.goodCategory.domain.GoodCategory;
import com.information.project.business.goodCategory.service.IGoodCategoryService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 商品分类 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-06
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
	
	/**
	 * 查询商品分类列表
	 */
	@RequiresPermissions("business:goodCategory:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(GoodCategory goodCategory)
	{
		startPage();
        List<GoodCategory> list = goodCategoryService.selectGoodCategoryList(goodCategory);
		return getDataTable(list);
	}
	
	/**
	 * 新增商品分类
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品分类
	 */
	@RequiresPermissions("business:goodCategory:add")
	@Log(title = "商品分类", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(GoodCategory goodCategory)
	{		
		return toAjax(goodCategoryService.insertGoodCategory(goodCategory));
	}

	/**
	 * 修改商品分类
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		GoodCategory goodCategory = goodCategoryService.selectGoodCategoryById(id);
		mmap.put("goodCategory", goodCategory);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品分类
	 */
	@RequiresPermissions("business:goodCategory:edit")
	@Log(title = "商品分类", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(GoodCategory goodCategory)
	{		
		return toAjax(goodCategoryService.updateGoodCategory(goodCategory));
	}
	
	/**
	 * 删除商品分类
	 */
	@RequiresPermissions("business:goodCategory:remove")
	@Log(title = "商品分类", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(goodCategoryService.deleteGoodCategoryByIds(ids));
	}
	
}
