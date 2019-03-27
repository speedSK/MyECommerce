package com.ruoyi.project.b2c.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruoyi.common.utils.GoodsConstant;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.business.goodCategory.domain.GoodCategory;
import com.ruoyi.project.business.goodCategory.service.IGoodCategoryService;
import com.ruoyi.project.business.goods.domain.Goods;
import com.ruoyi.project.business.goods.service.IGoodsService;
import com.ruoyi.project.business.person.domain.Person;

@Controller
@RequestMapping(value="/b2c/goods/")
public class B2CGoodsController extends BaseController {

	@Autowired
	IGoodsService goodsService;
	
	@Autowired
	IGoodCategoryService goodCategoryService;
	
	/**
	 * 进入商品页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="goodsList")
	public String getGoodsList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		//用户信息
		Person user = getPerson();
		//获取商品分类root的Id
		GoodCategory goodCategory = new GoodCategory();
  		goodCategory.setParentId(0L);
  		Long rootGoodCategoryId = goodCategoryService.selectGoodCategoryList(goodCategory).get(0).getId();
  		modelMap.put("rootGoodCategoryId", rootGoodCategoryId);
		modelMap.put("user", user);
		return "b2c/goods/goodsList";
	}
	/**
	 * 获取商品列表局部页面（包含分页栏）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="goodsInfo")
	public String getGoodsInfo(HttpServletRequest request, String categoryId, HttpServletResponse response, ModelMap modelMap){
			//获取用户信息
	        Person user = getPerson();
	        startPage();
	        Map<String, Object> param = new HashMap<String, Object>();
	        if(StringUtils.isNotBlank(categoryId)){
	        		param.put("belongCategoryId", categoryId);
	        }
	        param.put("visible", GoodsConstant.GOODS_VISIBLE_UP);
	        List<Goods> list = goodsService.findGoodsListForMap(param);
	        modelMap.put("goodsPage", getDataTable(list));
	        return "b2c/goods/goodsTable";
	}
	
}
