package com.information.project.bank.service;

import java.util.List;

import com.information.project.business.user.domain.BusUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.information.project.bank.domain.ReceiveFromBankInfo;
import com.information.project.business.user.domain.BusUserExample;
import com.information.project.business.user.mapper.BusUserMapper;

@Service
public class BankServiceImpl implements IBankService{
	
	@Autowired
	private BusUserMapper busUserMapper;
	
	@Override
	public ReceiveFromBankInfo queryUserInfo4Bank(BusUserExample ex) {
		ReceiveFromBankInfo receiveFromBankInfo = new ReceiveFromBankInfo();
		try {
			List<BusUser> list = busUserMapper.selectByExample(ex);
			if (list!=null&&list.size()>0) {
				receiveFromBankInfo.setResponsecode("000000");
				receiveFromBankInfo.setResponsename("查询成功");
			} else {
				receiveFromBankInfo.setResponsecode("000001");
				receiveFromBankInfo.setResponsename("用户不存在");
			}
		} catch (Exception e) {
			receiveFromBankInfo.setResponsecode("000002");
			receiveFromBankInfo.setResponsename("系统异常");
		}
		return receiveFromBankInfo;
	}

	@Override
	public ReceiveFromBankInfo queryUserSign(BusUserExample ex) {
		ReceiveFromBankInfo receiveFromBankInfo = new ReceiveFromBankInfo();
		try {
			List<BusUser> list = busUserMapper.selectByExample(ex);
			if (list!=null&&list.size()>0) {
				BusUser user = list.get(0);
				if ("1".equals(user.getAccStatus())) {
					receiveFromBankInfo.setResponsecode("000000");
					receiveFromBankInfo.setResponsename("签约成功");
				}else {
					receiveFromBankInfo.setResponsecode("000003");
					receiveFromBankInfo.setResponsename("账户状态异常（冻结）");
				}
			} else {
				receiveFromBankInfo.setResponsecode("000001");
				receiveFromBankInfo.setResponsename("用户不存在");
			}
		} catch (Exception e) {
			receiveFromBankInfo.setResponsecode("000002");
			receiveFromBankInfo.setResponsename("系统异常");
		}
		return receiveFromBankInfo;
	}

}
