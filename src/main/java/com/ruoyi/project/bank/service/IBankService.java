package com.ruoyi.project.bank.service;

import com.ruoyi.project.bank.domain.ReceiveFromBankInfo;
import com.ruoyi.project.business.person.domain.Person;

public interface IBankService {

	public ReceiveFromBankInfo queryUserInfo4Bank(String idserial);
	
	public ReceiveFromBankInfo queryUserSign(String idserial, String idserial2, String bankcdno, String newbankcdno);

}
