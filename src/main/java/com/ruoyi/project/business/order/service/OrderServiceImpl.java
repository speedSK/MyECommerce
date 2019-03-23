package com.ruoyi.project.business.order.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.business.goods.domain.Goods;
import com.ruoyi.project.business.goods.service.IGoodsService;
import com.ruoyi.project.business.orderDetail.domain.OrderDetail;
import com.ruoyi.project.business.orderDetail.mapper.OrderDetailMapper;
import com.ruoyi.project.business.orderDetail.service.IOrderDetailService;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.order.mapper.OrderMapper;
import com.ruoyi.project.business.order.domain.Order;
import com.ruoyi.project.business.order.service.IOrderService;
import com.ruoyi.common.support.Convert;
import org.springframework.transaction.annotation.Transactional;

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
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private IGoodsService goodsService;
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
                break;
		}
        return updateOrder(order);
	}

	/**
	 * 提交订单
	 * @param goodsId
	 * @param goodsNum
	 * @param user
	 */
	@Transactional
	@Override
	public JSONObject submitOrder(String[] goodsId, Integer[] goodsNum, Person user) {
		//订单提交结果
		JSONObject res = new JSONObject();
		res.put("resCode", Constants.SUCCESS);
		res.put("resMessage", "订单提交成功！");
		try {
			//创建订单
			Order order = new Order();
			order.setOrderCode("1");	//订单编号
			order.setPersonId(user.getId());
			order.setPersonCode(user.getNumber());
			order.setPersonName(user.getName());
			order.setCreateTime(new Date());
			order.setCreateBy(Constants.IDENTITY_TYPE);
			order.setFlag(Constants.ORDER_NORMAL);
			order.setMoney(new BigDecimal(0D));
			orderMapper.insertOrder(order);
			BigDecimal totalMoney = new BigDecimal(0D);
			//订单明细处理
			for(int index=0; index < goodsId.length; index++){
				Goods goods = goodsService.selectGoodsById(Long.valueOf(goodsId[index]));
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderId(order.getId());
				orderDetail.setMerchantId(goods.getMerchantId());
				orderDetail.setMerchantName(goods.getMerchant().getMerchantName());
				orderDetail.setGoodsId(goods.getId());
				orderDetail.setGoodsName(goods.getName());
				orderDetail.setGoodsPrice(goods.getPrice());
				orderDetail.setNum(goodsNum[index]);
				orderDetail.setMoney(BigDecimal.valueOf(ELArithmetic.BigDecimalDelegate.multiply(goods.getPrice(), goodsNum[index]).doubleValue()));
				orderDetail.setFlag(Constants.ORDER_NORMAL);
				orderDetail.setCreateBy(Constants.IDENTITY_TYPE);
				orderDetail.setCreateTime(new Date());
				totalMoney = (BigDecimal.valueOf(ELArithmetic.BigDecimalDelegate.add(totalMoney, orderDetail.getMoney()).doubleValue())) ;
				orderDetailMapper.insertOrderDetail(orderDetail);
			}
			//更新user的已消费金额
			user.setAlreadyCost(BigDecimal.valueOf(ELArithmetic.BigDecimalDelegate.add(totalMoney, user.getAlreadyCost()).doubleValue()));
			user.setBalance(BigDecimal.valueOf(ELArithmetic.BigDecimalDelegate.subtract(user.getBalance(), totalMoney).doubleValue()));
			personService.updatePerson(user);
			//更新订单总金额
			order.setMoney(totalMoney);
			orderMapper.updateOrder(order);
		} catch (NumberFormatException e) {
			res.put("resCode", Constants.FAIL);
			res.put("resMessage", "订单提交失败！");
		}
		return res;
	}

	/**
	 * 获取订单列表
	 */
	@Override
	public List<Order> findOrderList(Map<String, Object> param) {
		return orderMapper.findOrderListForMap(param);
	}

	/**
	 * 获取订单详细列表
	 */
	@Override
	public List<OrderDetail> findOrderDetails(Map<String, Object> param) {
		return orderDetailMapper.findOrderDetailListForMap(param);
	}

}
