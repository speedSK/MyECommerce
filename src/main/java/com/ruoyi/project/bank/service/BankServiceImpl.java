package com.ruoyi.project.bank.service;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.project.business.person.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.bank.domain.ReceiveFromBankInfo;
import com.ruoyi.project.business.person.mapper.PersonMapper;

@Service
public class BankServiceImpl implements IBankService{
	
	@Autowired
	private PersonMapper personMapper;
	
	@Override
	public ReceiveFromBankInfo queryUserInfo4Bank(String idserial) {
		ReceiveFromBankInfo receiveFromBankInfo = new ReceiveFromBankInfo();
		try {
			Person person = new Person();
            person.setNumber(idserial);
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
    public ReceiveFromBankInfo queryUserSign(String idserial, String idserial2, String bankcdno, String newbankcdno) {
        ReceiveFromBankInfo receiveFromBankInfo = new ReceiveFromBankInfo();
        try {
            Person person = new Person();
            person.setNumber(idserial.trim());
            person.setIdcard(idserial2.trim());
            person.setStatus(Constants.STATUS_ACTIVE);
            if (newbankcdno != null) {
                person.setBankCardNumber(bankcdno.trim());
                bankcdno = newbankcdno;
            }
            List<Person> list = personMapper.selectPersonList(person);
            if (list!=null&&list.size()==1){
                list.get(0).setBankCardNumber(bankcdno.trim());
                int result = personMapper.updatePerson(list.get(0));
                if (result > 0) {
                    receiveFromBankInfo.setResponsecode("000000");
                    receiveFromBankInfo.setResponsename("签约成功");
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
