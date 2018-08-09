package com.information.project.bank.service;

import java.util.List;

import com.information.project.business.person.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.information.project.bank.domain.ReceiveFromBankInfo;
import com.information.project.business.person.mapper.PersonMapper;

@Service
public class BankServiceImpl implements IBankService{
	
	@Autowired
	private PersonMapper personMapper;
	
	@Override
	public ReceiveFromBankInfo queryUserInfo4Bank(Person person) {
		ReceiveFromBankInfo receiveFromBankInfo = new ReceiveFromBankInfo();
		try {
			List<Person> list = personMapper.selectPersonList(person);
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
	public ReceiveFromBankInfo queryUserSign(Person person) {
		ReceiveFromBankInfo receiveFromBankInfo = new ReceiveFromBankInfo();
		try {
			List<Person> list = personMapper.selectPersonList(person);
			if (list!=null&&list.size()>0) {
				Person usr = list.get(0);
				if ("1".equals(usr.getAccStatus())) {
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
