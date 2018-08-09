package com.information.project.bank.service;

import com.information.project.bank.domain.ReceiveFromBankInfo;
import com.information.project.business.person.domain.Person;

public interface IBankService {

	public ReceiveFromBankInfo queryUserInfo4Bank(Person person);
	
	public ReceiveFromBankInfo queryUserSign(Person person);
}
