package com.ruoyi.project.business.order.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.support.Convert;
import com.ruoyi.project.system.merchant.domain.Merchant;
import com.ruoyi.project.system.merchant.service.IMerchantService;
import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.CommonUtils;
import com.ruoyi.common.utils.IdGen;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.business.goods.domain.Goods;
import com.ruoyi.project.business.goods.service.IGoodsService;
import com.ruoyi.project.business.order.domain.Order;
import com.ruoyi.project.business.order.mapper.OrderMapper;
import com.ruoyi.project.business.orderDetail.domain.OrderDetail;
import com.ruoyi.project.business.orderDetail.mapper.OrderDetailMapper;
import com.ruoyi.project.business.orderDetail.service.IOrderDetailService;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import com.ruoyi.project.system.device.service.IDeviceService;

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
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private ITradeRecordService tradeRecordService;
	@Autowired
	private IMerchantService merchantService;

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
	public int updateOrderFlag(String ids, String flag) {
		List<Order> orderList = orderMapper.selectOrderByIds(Convert.toStrArray(ids));
		for (Order order : orderList) {
			updateOrderFlag(order, flag);
		}
		return 1;
	}

	@Transactional
	public void updateOrderFlag(Order order, String flag) {
		order.setUpdateBy(ShiroUtils.getLoginName());
		switch (flag) {
			case "finish":
				order.setFlag(Constants.ORDER_FINISH);
				//更新该订单下指定商品的状态
				orderDetailService.updateFlagByOrderId(order.getId(), Constants.ORDER_FINISH);
				break;
			case "cancel":
				order.setFlag(Constants.ORDER_CANCEL);
				orderDetailService.updateFlagByOrderId(order.getId(), Constants.ORDER_CANCEL);
				break;
		}
		updateOrder(order);
	}

	/**
	 * 提交订单
	 * @param goodsId
	 * @param goodsNum
	 * @param person
	 */
	@Transactional
	@Override
	public JSONObject submitOrder(String[] goodsId, Integer[] goodsNum, Person person) {
		//订单提交结果
		JSONObject res = new JSONObject();
		res.put("resCode", Constants.SUCCESS);
		res.put("resMessage", "订单提交成功！");
		try {
			person = personService.selectPersonById(person.getId());
			//创建订单
			Order order = new Order();
			order.setOrderCode(CommonUtils.getOrderCode(person));	//订单编号
			order.setPersonId(person.getId());
			order.setPersonCode(person.getNumber());
			order.setPersonName(person.getName());
			order.setCreateTime(new Date());
			order.setCreateBy(person.getNumber());
			order.setFlag(Constants.ORDER_NORMAL);
			//TODO 后期优化逻辑
			order.setMoney(new BigDecimal(0));
			orderMapper.insertOrder(order);
			BigDecimal totalMoney = new BigDecimal(0);
			//订单明细处理
			for(int index=0; index < goodsId.length; index++){
				Goods goods = goodsService.selectGoodsById(Long.valueOf(goodsId[index]));
				Merchant merchant = merchantService.selectMerchantById(goods.getMerchantId());
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderId(order.getId());
				orderDetail.setMerchantId(goods.getMerchantId());
				orderDetail.setMerchantName(goods.getMerchant().getMerchantName());
				orderDetail.setGoodsId(goods.getId());
				orderDetail.setGoodsName(goods.getName());
				orderDetail.setGoodsPrice(goods.getPrice());
				orderDetail.setNum(goodsNum[index]);
				//商品总价
				BigDecimal goodSum = goods.getPrice().multiply(new BigDecimal(goodsNum[index]));
				orderDetail.setMoney(goodSum);
				merchant.setBalance(merchant.getBalance().add(goodSum));
				merchantService.updateMerchant(merchant);
				orderDetail.setFlag(Constants.ORDER_NORMAL);
				orderDetail.setCreateBy(person.getNumber());
				orderDetail.setCreateTime(new Date());
				totalMoney = totalMoney.add(goodSum);
				orderDetailMapper.insertOrderDetail(orderDetail);
				//增加流水
				TradeRecord record = new TradeRecord();
				record.setJourno(IdGen.getJourno());
				record.setUserNumber(person.getId().toString());
				record.setMerchantCode(goods.getMerchantId().toString());
				record.setMerchantName(goods.getMerchant().getMerchantName());
				record.setOrderCode(order.getOrderCode());
				record.setTxcode(Constants.TX_CODE_BUY_COST);
				record.setTxamt(goodSum);
				record.setBefore(person.getBalance());
				person.setBalance(person.getBalance().subtract(goodSum));
				person.setAlreadyCost(person.getAlreadyCost().add(goodSum));
				record.setAfter(person.getBalance());
				record.setToAcc(goods.getMerchantId().toString());
				record.setStationCode(deviceService.getDeviceCode());
				record.setRemark("购买商品");
				record.setCreateBy(ShiroUtils.getPerson().getNumber());
				record.setCreateTime(new Date());
				tradeRecordService.insertTradeRecord(record);
			}
			personService.updatePerson(person);
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
