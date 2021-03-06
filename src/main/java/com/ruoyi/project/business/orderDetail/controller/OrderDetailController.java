package com.ruoyi.project.business.orderDetail.controller;

import java.util.List;

import com.ruoyi.project.business.order.domain.Order;
import com.ruoyi.project.business.order.service.IOrderService;
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
import com.ruoyi.project.business.orderDetail.domain.OrderDetail;
import com.ruoyi.project.business.orderDetail.service.IOrderDetailService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 订单详情 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-10
 */
@Controller
@RequestMapping("/business/orderDetail")
public class OrderDetailController extends BaseController
{
    private String prefix = "business/orderDetail";

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IOrderDetailService orderDetailService;


	/**
	 * 查询字典详细
	 */
	@RequiresPermissions("business:orderDetail:list")
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
	 * 修改订单状态
	 */
	@RequiresPermissions("business:order:editVisible")
	@Log(title = "订单状态", businessType = BusinessType.UPDATE)
	@PostMapping( "/editVisible")
	@ResponseBody
	public AjaxResult editVisible(String ids, String visible)
	{
		return toAjax(orderDetailService.updateOrderDetailFlag(ids, visible));
	}
	
}
