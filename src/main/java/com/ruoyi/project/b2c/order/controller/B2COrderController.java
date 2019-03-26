package com.ruoyi.project.b2c.order.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.el.lang.ELArithmetic.BigDecimalDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.business.goods.domain.Goods;
import com.ruoyi.project.business.goods.service.IGoodsService;
import com.ruoyi.project.business.order.domain.Order;
import com.ruoyi.project.business.order.service.IOrderService;
import com.ruoyi.project.business.orderDetail.domain.OrderDetail;
import com.ruoyi.project.business.orderDetail.service.IOrderDetailService;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import com.ruoyi.project.system.identity.domain.Identity;
import com.ruoyi.project.system.identity.service.IIdentityService;

/**
 * 订单controller
 * @author CoderX
 */
@RequestMapping(value="/b2c/order/")
@Controller
public class B2COrderController extends BaseController {

	private String prefix = "b2c/order";
	
	@Autowired
	private IGoodsService goodsService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IIdentityService identityService;
	
	@Autowired
	private IPersonService personService;

	@Autowired
	private IOrderDetailService orderDetailService;

	@GetMapping("/order")
	public String order()
	{
		return prefix + "/order";
	}

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Order order)
	{
		startPage();
		order.setPersonId(getPerson().getId());
		List<Order> list = orderService.selectOrderList(order);
		return getDataTable(list);
	}

	/**
	 * 查询字典详细
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, ModelMap mmap)
	{
		if(0 != id){
			Order order = orderService.selectOrderById(id);
			mmap.put("order", order);
		}
		return prefix + "/orderDetail";
	}

	/**
	 * 查询订单详情列表
	 */
	@PostMapping("/DetailList")
	@ResponseBody
	public TableDataInfo DetailList(OrderDetail orderDetail)
	{
		startPage();
		List<OrderDetail> list = orderDetailService.selectOrderDetailList(orderDetail);
		return getDataTable(list);
	}
	/**
	 * 结算页面
	 * @return
	 */
	@PostMapping(value="settlePage")
	public String getSettlePage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String[] goodsId, Long[] goodsNum){
		//购物人信息(getPerson是从shiro中获取的，已消费额度不是实时的)
		Person user = personService.selectPersonById(getPerson().getId());
		modelMap.put("user", user);
		//身份信息(从身份信息获取消费限额)
		Identity identity = identityService.selectIdentityById(user.getIdentityId());
		//此次下单限额
		Double quota = BigDecimalDelegate.subtract(identity.getCostTotal(), user.getAlreadyCost()).doubleValue();
		//结算商品列表
		Map<String,Object> param = new HashMap<String,Object>();
		List<String> goodsIds = new ArrayList<String>();
		goodsIds = Arrays.asList(goodsId);
		param.put("goodsIds", goodsIds);
		List<Goods> goodsList= goodsService.findGoodsListForMap(param);
		//设置商品购买数量用于结算页面展示
		for(Goods goods : goodsList){
			for(int i=0; i<goodsId.length; i++){
				if(goodsId[i].equals(goods.getId().toString())){
					goods.setNum(goodsNum[i]);
				}
			}
		}
		modelMap.put("goodsList", goodsList);
		//支付方式列表
		Map<String,Object> payType = new HashMap<String,Object>();
		modelMap.put("payType", payType);
		modelMap.put("quota", quota);
		return "b2c/order/settleInfo";
	}
	
	/**
	 * 提交订单，返回提交结果
	 * @return
	 */
	@PostMapping(value="submitOrder")
	@ResponseBody
	public JSONObject submitOrder(HttpServletRequest request,  HttpServletResponse response,  ModelMap modelMap, String[] goodsId, Integer[] goodsNum){
		//订单提交结果
		JSONObject res = new JSONObject();
		res = orderService.submitOrder(goodsId, goodsNum, getPerson());
		//获取用户最新余额
		Person user = personService.selectPersonById(getPerson().getId());
		res.put("userBalance", user.getBalance());
		return res;
	}
	
	/**
	 * 获取订单列表
	 * @return
	 */
	@RequestMapping(value="orderList")
	public String getOrderList(Model model){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Order> orderList = orderService.findOrderList(paramMap);
		model.addAttribute("orderList", orderList);
		return "b2c/order/orderList";
	}
}
