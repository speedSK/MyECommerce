package com.ruoyi.project.business.goods.controller;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.project.system.merchant.service.IMerchantService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.business.goods.domain.Goods;
import com.ruoyi.project.business.goods.service.IGoodsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
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
		goods.setStatus(Constants.STATUS_ACTIVE);
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
	@Log(title = "商品管理", businessType = BusinessType.INSERT)
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
	@Log(title = "商品管理", businessType = BusinessType.UPDATE)
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
	@Log(title = "商品管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(goodsService.deleteGoodsByIds(ids));
	}

    /**
     * 删除商品
     */
    @RequiresPermissions("business:goods:editVisible")
    @Log(title = "商品上/下架", businessType = BusinessType.UPDATE)
    @PostMapping("/editVisible")
    @ResponseBody
    public AjaxResult editVisible(String ids, String visible) {
        Goods goods = new Goods();
        goods.setId(Long.valueOf(ids));
        switch (visible) {
            case "up":
                goods.setVisible(Constants.VISIBLE_TRUE);
                break;
            case "down":
                goods.setVisible(Constants.VISIBLE_FALSE);
                break;
        }
        return toAjax(goodsService.updateGoods(goods));
    }





	/**
	 * 保存图片
	 */
	@Log(title = "商品图片信息", businessType = BusinessType.UPDATE)
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
