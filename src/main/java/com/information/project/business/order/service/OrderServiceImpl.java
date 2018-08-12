package com.information.project.business.order.service;

import com.information.common.constant.Constants;
import com.information.common.utils.security.ShiroUtils;

import java.util.Date;
import java.util.List;

import com.information.project.business.orderDetail.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.order.mapper.OrderMapper;
import com.information.project.business.order.domain.Order;
import com.information.project.business.order.service.IOrderService;
import com.information.common.support.Convert;

/**
 * 订单 服务层实现
 *
 * @author LiuNing
 * @date 2018-08-10
 */
@Service
public class OrderServiceImpl implements IOrderService
{
	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private IOrderDetailService orderDetailService;

	/**
     * 查询订单信息
     *
     * @param id 订单ID
     * @return 订单信息
     */
    @Override
	public Order selectOrderById(Long id)
	{
	    return orderMapper.selectOrderById(id);
	}

	/**
     * 查询订单列表
     *
     * @param order 订单信息
     * @return 订单集合
     */
	@Override
	public List<Order> selectOrderList(Order order)
	{
	    order.setStatus(Constants.STATUS_ACTIVE);
	    return orderMapper.selectOrderList(order);
	}

    /**
     * 新增订单
     *
     * @param order 订单信息
     * @return 结果
     */
	@Override
	public int insertOrder(Order order)
	{
	    order.setStatus(Constants.STATUS_ACTIVE);

        order.setCreateBy(ShiroUtils.getUserId().toString());
	    return orderMapper.insertOrder(order);
	}

	/**
     * 修改订单
     *
     * @param order 订单信息
     * @return 结果
     */
	@Override
	public int updateOrder(Order order)
	{
	    order.setUpdateBy(ShiroUtils.getUserId().toString());
	    return orderMapper.updateOrder(order);
	}

	/**
     * 删除订单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int cancelOrderByIds(String ids)
	{
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

			Order order = orderMapper.selectOrderById(Long.valueOf(id)) ;
			if(Constants.ORDER_0.equals(order.getFlag())){
				order.setId(Long.valueOf(id));
				//初始化數據信息
				order.setFlag(Constants.ORDER_2);
				order.setFinishUser(ShiroUtils.getLoginName());
				order.setFinishTime(new Date());
				order.setUpdateBy(ShiroUtils.getUserId().toString());
				orderMapper.updateOrder(order);

				//更新该订单下指定商品的状态
				orderDetailService.updateFlagByOrderId(id,Constants.ORDER_2);
			}

        }

        return 1;
	}

	@Override
	public int finishOrderByIds(String ids) {

		String [] idsArray = Convert.toStrArray(ids);
		for (String id: idsArray) {

			Order order = orderMapper.selectOrderById(Long.valueOf(id)) ;
			if(Constants.ORDER_0.equals(order.getFlag())){
				order.setId(Long.valueOf(id));
				//初始化數據信息
				order.setFlag(Constants.ORDER_1);
				order.setFinishUser(ShiroUtils.getLoginName());
				order.setFinishTime(new Date());
				order.setUpdateBy(ShiroUtils.getUserId().toString());
				orderMapper.updateOrder(order);

				//更新该订单下指定商品的状态
				orderDetailService.updateFlagByOrderId(id,Constants.ORDER_1);
			}
		}

		return 1;
	}

	@Override
	public int updateMoneyByIds(String ids) {

		String [] idsArray = Convert.toStrArray(ids);
		for (String id: idsArray) {

			Order order = orderMapper.selectOrderById(Long.valueOf(id)) ;
			if(null != order && Constants.ORDER_0.equals(order.getFlag())){

				//更新该订单下指定商品的money
				orderMapper.updateMoneyById(Long.valueOf(id)) ;
			}
		}
		return 1;
	}

}
