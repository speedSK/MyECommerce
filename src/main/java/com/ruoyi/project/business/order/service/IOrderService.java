package com.ruoyi.project.business.order.service;

import com.ruoyi.project.business.order.domain.Order;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.business.orderDetail.domain.OrderDetail;
import com.ruoyi.project.business.person.domain.Person;

/**
 * 订单 服务层
 * 
 * @author LiuNing
 * @date 2018-08-10
 */
public interface IOrderService 
{
	/**
     * 查询订单信息
     * 
     * @param id 订单ID
     * @return 订单信息
     */
	public Order selectOrderById(Long id);
	
	/**
     * 查询订单列表
     * 
     * @param order 订单信息
     * @return 订单集合
     */
	public List<Order> selectOrderList(Order order);
	
	/**
     * 新增订单
     * 
     * @param order 订单信息
     * @return 结果
     */
	public int insertOrder(Order order);
	
	/**
     * 修改订单
     * 
     * @param order 订单信息
     * @return 结果
     */
	public int updateOrder(Order order);

	/**
	 * 修改订单状态
	 * @param id
	 * @param flag
	 * @return
	 */
	public int updateOrderFlag(String id, String flag);

	/**
	 * 提交订单
	 * @param goodsId
	 * @param goodsNum
	 * @param user
	 */
	public JSONObject submitOrder(String[] goodsId, Integer[] goodsNum, Person user);

	/**
	 * 获取用户订单列表
	 * @param param
	 * @return List<BusOrder>
	 */
	public List<Order> findOrderList(Map<String, Object> param);

	/**
	 * 查询订单明细
	 * @param param
	 * @return
	 */
	public List<OrderDetail> findOrderDetails(Map<String, Object> param);
}
