package com.ruoyi.project.business.order.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.business.orderDetail.domain.OrderDetail;
import com.ruoyi.project.business.orderDetail.service.IOrderDetailService;
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
import com.ruoyi.project.business.order.domain.Order;
import com.ruoyi.project.business.order.service.IOrderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 订单 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-10
 */
@Controller
@RequestMapping("/business/order")
public class OrderController extends BaseController
{
    private String prefix = "business/order";
	
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IOrderDetailService orderDetailService;
	
	@RequiresPermissions("business:order:view")
	@GetMapping()
	public String order()
	{
	    return prefix + "/order";
	}
	
	/**
	 * 查询订单列表
	 */
	@RequiresPermissions("business:order:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Order order)
	{
		startPage();
        List<Order> list = orderService.selectOrderList(order);
		return getDataTable(list);
	}
	
	/**
	 * 新增订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单
	 */
	@RequiresPermissions("business:order:add")
	@Log(title = "订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Order order)
	{		
		return toAjax(orderService.insertOrder(order));
	}

	/**
	 * 修改订单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Order order = orderService.selectOrderById(id);
		mmap.put("order", order);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存订单
	 */
	@RequiresPermissions("business:order:edit")
	@Log(title = "订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Order order)
	{		
		return toAjax(orderService.updateOrder(order));
	}
	
	/**
	 * 修改订单状态
	 */
	@RequiresPermissions("business:order:editVisible")
	@Log(title = "订单状态", businessType = BusinessType.UPDATE)
	@PostMapping( "/editVisible")
	@ResponseBody
	public AjaxResult editVisible(String ids, String visible)
	{
        return toAjax(orderService.updateOrderFlag(ids, visible));
	}

	/**
	 * 导出订单列表
	 */
	@RequiresPermissions("business:order:export")
	@Log(title = "导出订单列表", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Order order)
	{
		List<Order> list = orderService.selectOrderList(order);
		ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
		return util.exportExcel(list, "order");
	}

	/**
	 * 导出订单详情
	 */
	@RequiresPermissions("business:order:exportDetail")
	@Log(title = "导出订单详情", businessType = BusinessType.EXPORT)
	@PostMapping("/exportDetail")
	@ResponseBody
	public AjaxResult exportDetail(Order order)
	{
		List<Order> list = orderService.selectOrderList(order);
		List<OrderDetail> orderDetailList = new ArrayList<>();
		if (StringUtils.isNotEmpty(list)) {
			for (Order o : list) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderId(o.getId());
				List<OrderDetail> orderDetails = orderDetailService.selectOrderDetailList(orderDetail);
				if (StringUtils.isNotEmpty(orderDetails)) {
					orderDetailList.addAll(orderDetails);
				}
			}
		}
		ExcelUtil<OrderDetail> util = new ExcelUtil<OrderDetail>(OrderDetail.class);
		return util.exportExcel(orderDetailList, "orderDetail");
	}
}