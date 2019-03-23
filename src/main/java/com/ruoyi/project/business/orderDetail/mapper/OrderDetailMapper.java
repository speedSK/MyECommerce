package com.ruoyi.project.business.orderDetail.mapper;

import com.ruoyi.project.business.orderDetail.domain.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 订单详情 数据层
 * 
 * @author LiuNing
 * @date 2018-08-10
 */
@Repository
public interface OrderDetailMapper 
{
	/**
     * 查询订单详情信息
     * 
     * @param id 订单详情ID
     * @return 订单详情信息
     */
	public OrderDetail selectOrderDetailById(Long id);
	
	/**
     * 查询订单详情列表
     * 
     * @param orderDetail 订单详情信息
     * @return 订单详情集合
     */
	public List<OrderDetail> selectOrderDetailList(OrderDetail orderDetail);
	
	/**
     * 新增订单详情
     * 
     * @param orderDetail 订单详情信息
     * @return 结果
     */
	public int insertOrderDetail(OrderDetail orderDetail);
	
	/**
     * 修改订单详情
     * 
     * @param orderDetail 订单详情信息
     * @return 结果
     */
	public int updateOrderDetail(OrderDetail orderDetail);
	
	/**
     * 删除订单详情
     * 
     * @param id 订单详情ID
     * @return 结果
     */
	public int deleteOrderDetailById(Long id);
	
	/**
     * 批量删除订单详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderDetailByIds(String[] ids);

	public  int updateFlagByOrderId(OrderDetail orderDetail);

	/**
	 * 获取订单明细列表
	 * @param param
	 * @return
	 */
	public List<OrderDetail> findOrderDetailListForMap(Map<String, Object> param);
}