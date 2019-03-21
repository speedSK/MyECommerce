package com.ruoyi.project.business.orderDetail.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.IdGen;
import com.ruoyi.common.utils.security.ShiroUtils;

import java.util.Date;
import java.util.List;

import com.ruoyi.project.business.order.domain.Order;
import com.ruoyi.project.business.order.service.IOrderService;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.service.IPersonService;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import com.ruoyi.project.system.device.service.IDeviceService;
import com.ruoyi.project.system.merchant.domain.Merchant;
import com.ruoyi.project.system.merchant.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.orderDetail.mapper.OrderDetailMapper;
import com.ruoyi.project.business.orderDetail.domain.OrderDetail;
import com.ruoyi.common.support.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单详情 服务层实现
 *
 * @author LiuNing
 * @date 2018-08-10
 */
@Service
public class OrderDetailServiceImpl implements IOrderDetailService
{
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@Autowired
	private IOrderService orderService;
    @Autowired
    private IPersonService personService;
	@Autowired
	private IMerchantService merchantService;
	@Autowired
	private ITradeRecordService tradeRecordService;
	@Autowired
	private IDeviceService deviceService;



	/**
     * 查询订单详情信息
     *
     * @param id 订单详情ID
     * @return 订单详情信息
     */
    @Override
	public OrderDetail selectOrderDetailById(Long id)
	{
	    return orderDetailMapper.selectOrderDetailById(id);
	}

	/**
     * 查询订单详情列表
     *
     * @param orderDetail 订单详情信息
     * @return 订单详情集合
     */
	@Override
	public List<OrderDetail> selectOrderDetailList(OrderDetail orderDetail)
	{
	    orderDetail.setStatus(Constants.STATUS_ACTIVE);
	    return orderDetailMapper.selectOrderDetailList(orderDetail);
	}

    /**
     * 新增订单详情
     *
     * @param orderDetail 订单详情信息
     * @return 结果
     */
	@Override
	public int insertOrderDetail(OrderDetail orderDetail)
	{
	    orderDetail.setStatus(Constants.STATUS_ACTIVE);

        orderDetail.setCreateBy(ShiroUtils.getLoginName());
	    return orderDetailMapper.insertOrderDetail(orderDetail);
	}

	/**
     * 修改订单详情
     *
     * @param orderDetail 订单详情信息
     * @return 结果
     */
	@Override
	public int updateOrderDetail(OrderDetail orderDetail)
	{
	    orderDetail.setUpdateBy(ShiroUtils.getLoginName());
	    return orderDetailMapper.updateOrderDetail(orderDetail);
	}

	/**
     * 删除订单详情对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOrderDetailByIds(String ids)
	{
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(Long.valueOf(id));
            //初始化數據信息

            orderDetail.setStatus(Constants.STATUS_REMOVED);

            orderDetail.setUpdateBy(ShiroUtils.getLoginName());

            orderDetailMapper.updateOrderDetail(orderDetail);

        }

        return 1;
	}

	@Override
	public int updateFlagByOrderId(String id,String flag) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId(Long.valueOf(id));
		if (flag.equals(Constants.ORDER_FINISH)) {
			orderDetail.setFlag(Constants.ORDER_NORMAL);
			List<OrderDetail> orderDetailList = orderDetailMapper.selectOrderDetailList(orderDetail);
			for (OrderDetail o : orderDetailList) {
				this.updateOrderDetail(o);
			}
		} else if (flag.equals(Constants.ORDER_CANCEL)) {
			orderDetail.setFlag(Constants.ORDER_NORMAL);
			List<OrderDetail> orderDetailList = orderDetailMapper.selectOrderDetailList(orderDetail);
			for (OrderDetail o : orderDetailList) {
				this.updateOrderDetailFlag(o.getId().toString(), flag);
			}
		}
		return 1;
	}

	@Override
	@Transactional
	public int updateOrderDetailFlag(String id, String flag) {
		TradeRecord record = new TradeRecord();
		record.setJourno(IdGen.getJourno());
        OrderDetail orderDetail = orderDetailMapper.selectOrderDetailById(Long.parseLong(id));
        Order order = orderService.selectOrderById(orderDetail.getOrderId());
        orderDetail.setFlag(Constants.ORDER_CANCEL);
        orderDetail.setUpdateBy(ShiroUtils.getLoginName());
        order.setMoney(order.getMoney().subtract(orderDetail.getMoney()));
        order.setUpdateBy(ShiroUtils.getLoginName());
        this.updateOrderDetail(orderDetail);
        orderService.updateOrder(order);
		Merchant merchant = merchantService.selectMerchantById(orderDetail.getMerchantId());
		merchant.setBalance(merchant.getBalance().subtract(orderDetail.getMoney()));
		merchantService.updateMerchant(merchant);
        Person person = personService.selectPersonById(order.getPersonId());
		record.setBefore(person.getBalance());
        person.setBalance(person.getBalance().add(orderDetail.getMoney()));
		personService.updatePerson(person);
		String deviceCode = deviceService.getDeviceCode();
		record.setUserNumber(person.getNumber());
		record.setOrderCode(order.getOrderCode());
		record.setTxamt(orderDetail.getMoney());
		record.setTxcode("1008");
		record.setFromAcc(merchant.getId().toString());
		record.setAfter(person.getBalance());
		record.setStationCode(deviceCode);
		record.setCreateBy(ShiroUtils.getLoginName());
		record.setRemark("商品退款");
		return tradeRecordService.insertTradeRecord(record);
	}
}
