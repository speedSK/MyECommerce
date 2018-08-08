package com.information.project.business.goods.controller;

import java.util.List;

import com.information.common.utils.FileUploadUtils;
import com.information.project.system.merchant.service.IMerchantService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.information.framework.aspectj.lang.annotation.Log;
import com.information.framework.aspectj.lang.constant.BusinessType;
import com.information.project.business.goods.domain.Goods;
import com.information.project.business.goods.service.IGoodsService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-08
 */
@Controller
@RequestMapping("/business/goods")
public class GoodsController extends BaseController
{
    private String prefix = "business/goods";
	
	@Autowired
	private IGoodsService goodsService;

	@Autowired
	private IMerchantService merchantService;
	
	@RequiresPermissions("business:goods:view")
	@GetMapping()
	public String goods()
	{
	    return prefix + "/goods";
	}
	
	/**
	 * 查询商品列表
	 */
	@RequiresPermissions("business:goods:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Goods goods)
	{
		startPage();
        List<Goods> list = goodsService.selectGoodsList(goods);
		return getDataTable(list);
	}
	
	/**
	 * 新增商品
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{

		mmap.put("merchants", merchantService.selectMerchantAll());

		return prefix + "/add";
	}
	
	/**
	 * 新增保存商品
	 */
	@RequiresPermissions("business:goods:add")
	@Log(title = "商品", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Goods goods)
	{		
		return toAjax(goodsService.insertGoods(goods));
	}

	/**
	 * 修改商品
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Goods goods = goodsService.selectGoodsById(id);
		mmap.put("goods", goods);
		mmap.put("merchants", merchantService.selectMerchantAll());

	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品
	 */
	@RequiresPermissions("business:goods:edit")
	@Log(title = "商品", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Goods goods)
	{		
		return toAjax(goodsService.updateGoods(goods));
	}
	
	/**
	 * 删除商品
	 */
	@RequiresPermissions("business:goods:remove")
	@Log(title = "商品", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(goodsService.deleteGoodsByIds(ids));
	}





	/**
	 * 保存图片
	 */
	@Log(title = "商品图片信息", action = BusinessType.UPDATE)
	@PostMapping("/updateAvatar")
	@ResponseBody
	public AjaxResult updateAvatar(Goods goods, @RequestParam("avatarfile") MultipartFile file)
	{
		try
		{
			if (!file.isEmpty())
			{
				String avatar = FileUploadUtils.upload(file);
				goods.setImage(avatar) ;
				if (goodsService.updateGoods(goods) > 0)
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
		mmap.put("goods", goodsService.selectGoodsById(id));
		return prefix + "/avatar";
	}



	
}
