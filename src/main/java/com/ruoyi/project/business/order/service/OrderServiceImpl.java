package com.ruoyi.project.business.order.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;

import java.util.Date;
import java.util.List;

import com.ruoyi.project.business.orderDetail.service.IOrderDetailService;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.order.mapper.OrderMapper;
import com.ruoyi.project.business.order.domain.Order;
import com.ruoyi.project.business.order.service.IOrderService;
import com.ruoyi.common.support.Convert;

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
    @Autowired
    private IPersonService personService;

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

        order.setCreateBy(ShiroUtils.getLoginName());
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
	    order.setUpdateBy(ShiroUtils.getLoginName());
	    return orderMapper.updateOrder(order);
	}

	@Override
	public int updateOrderFlag(String id, String flag) {
        Order order = orderMapper.selectOrderById(Long.parseLong(id));
		order.setUpdateBy(ShiroUtils.getLoginName());
		switch (flag) {
			case "finish":
				order.setFlag(Constants.ORDER_FINISH);
				//更新该订单下指定商品的状态
				orderDetailService.updateFlagByOrderId(id,Constants.ORDER_FINISH);
				break;
			case "cancel":
				order.setFlag(Constants.ORDER_CANCEL);
				orderDetailService.updateFlagByOrderId(id,Constants.ORDER_CANCEL);
				Person person = personService.selectPersonById(order.getPersonId());
                if (person != null) {
                    person.setBalance(person.getBalance().add(order.getMoney()));
                    personService.updatePerson(person);
                }
                break;
		}
        return updateOrder(order);
	}
}
