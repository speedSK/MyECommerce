package com.ruoyi.project.business.goodCategory.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.Ztree;
import com.ruoyi.project.business.goodCategory.domain.GoodCategory;
import com.ruoyi.project.business.goodCategory.service.IGoodCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
	 * 获取商品列表局部页面（包含分页栏）
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping(value="categoryList")
	public String getGoodsInfo(HttpServletRequest request, String parentId, HttpServletResponse response, ModelMap modelMap){
			//获取用户信息
//	        Person user = getPerson();
	        startPage();
	        GoodCategory goodCategory  =  new GoodCategory();
	        if(StringUtils.isNotBlank(parentId)){
	        		goodCategory.setParentId(Long.valueOf(parentId));
	        }else{
	        		//分类的根节点parentID为0
	        		goodCategory.setParentId(0L);
	        }
	        List<GoodCategory> goodsCategoryList = goodCategoryService.selectGoodCategoryList(goodCategory);
	        modelMap.put("categoryPage", getDataTable(goodsCategoryList));
	        return "b2c/goods/goodsCategoryTable";
	}

	/**
	 * 删除菜单
	 */
	@Log(title = "分类管理", businessType = BusinessType.DELETE)
	@RequiresPermissions("business:goodCategory:remove")
	@PostMapping("/remove/{goodCategoryId}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("goodCategoryId") Long goodCategoryId)
	{
		if (goodCategoryId == 100) {
			return error(1, "顶级分类,不允许删除");
		}
		if (goodCategoryService.selectGoodCategoryCount(goodCategoryId) > 0) {
			return error(1, "存在子分类,不允许删除");
		}
		if (goodCategoryService.checkDeptExistGoods(goodCategoryId)) {
			return error(1, "分类存在商品,不允许删除");
		}
		return toAjax(goodCategoryService.deleteGoodCategoryById(goodCategoryId));
	}

	/**
	 * 新增
	 */
	@GetMapping("/add/{parentId}")
	public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
	{
		GoodCategory goodCategory = goodCategoryService.selectGoodCategoryById(parentId);
		mmap.put("goodCategory", goodCategory);
		return prefix + "/add";
	}

	/**
	 * 新增保存分类
	 */
	@Log(title = "分类管理", businessType = BusinessType.INSERT)
	@RequiresPermissions("business:goodCategory:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(GoodCategory goodCategory)
	{
		return toAjax(goodCategoryService.insertGoodCategory(goodCategory));
	}

	/**
	 * 修改分类
	 */
	@GetMapping("/edit/{goodCategoryId}")
	public String edit(@PathVariable("goodCategoryId") Long goodCategoryId, ModelMap mmap)
	{
		GoodCategory goodCategory = goodCategoryService.selectGoodCategoryById(goodCategoryId);
		if (StringUtils.isNotNull(goodCategory) && 100L == goodCategoryId)
		{
			goodCategory.setParentName("无");
		}
		mmap.put("goodCategory", goodCategory);
		return prefix + "/edit";
	}

	/**
	 * 修改保存分类
	 */
	@Log(title = "分类管理", businessType = BusinessType.UPDATE)
	@RequiresPermissions("business:goodCategory:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(GoodCategory goodCategory)
	{
		return toAjax(goodCategoryService.updateGoodCategory(goodCategory));
	}

	/**
	 * 选择分类图标
	 */
	@GetMapping("/icon")
	public String icon()
	{
		return prefix + "/icon";
	}

	/**
	 * 校验分类名称
	 */
	@PostMapping("/checkGoodCategoryNameUnique")
	@ResponseBody
	public String checkGoodCategoryNameUnique(GoodCategory goodCategory)
	{
		return goodCategoryService.checkGoodCategoryNameUnique(goodCategory);
	}



	/**
	 * 加载所有分类列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Ztree> treeData()
	{
		List<Ztree> tree = goodCategoryService.selectGoodCategoryTree(new GoodCategory());
		return tree;
	}

	/**
	 * 选择分类树
	 */
	@GetMapping("/selectGoodCategoryTree/{goodCategoryId}")
	public String selectGoodCategoryTree(@PathVariable("goodCategoryId") Long goodCategoryId, ModelMap mmap)
	{
		mmap.put("goodCategory", goodCategoryService.selectGoodCategoryById(goodCategoryId));
		return prefix + "/tree";
	}

}