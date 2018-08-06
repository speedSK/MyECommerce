package com.information.project.business.orderDetail.controller;

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
import com.information.project.business.orderDetail.domain.OrderDetail;
import com.information.project.business.orderDetail.service.IOrderDetailService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 订单详情 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Controller
@RequestMapping("/business/orderDetail")
public class OrderDetailController extends BaseController
{
    private String prefix = "business/orderDetail";
	
	@Autowired
	private IOrderDetailService orderDetailService;
	
	@RequiresPermissions("business:orderDetail:view")
	@GetMapping()
	public String orderDetail()
	{
	    return prefix + "/orderDetail";
	}
	
	/**
	 * 查询订单详情列表
	 */
	@RequiresPermissions("business:orderDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OrderDetail orderDetail)
	{
		startPage();
        List<OrderDetail> list = orderDetailService.selectOrderDetailList(orderDetail);
		return getDataTable(list);
	}
	
	/**
	 * 新增订单详情
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单详情
	 */
	@RequiresPermissions("business:orderDetail:add")
	@Log(title = "订单详情", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OrderDetail orderDetail)
	{		
		return toAjax(orderDetailService.insertOrderDetail(orderDetail));
	}

	/**
	 * 修改订单详情
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		OrderDetail orderDetail = orderDetailService.selectOrderDetailById(id);
		mmap.put("orderDetail", orderDetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存订单详情
	 */
	@RequiresPermissions("business:orderDetail:edit")
	@Log(title = "订单详情", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OrderDetail orderDetail)
	{		
		return toAjax(orderDetailService.updateOrderDetail(orderDetail));
	}
	
	/**
	 * 删除订单详情
	 */
	@RequiresPermissions("business:orderDetail:remove")
	@Log(title = "订单详情", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(orderDetailService.deleteOrderDetailByIds(ids));
	}
	
}
