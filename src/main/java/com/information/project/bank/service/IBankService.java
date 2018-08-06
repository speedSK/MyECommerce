package com.information.project.bank.service;

import com.information.project.bank.domain.ReceiveFromBankInfo;
import com.information.project.business.user.domain.BusUser;

public interface IBankService {

	public ReceiveFromBankInfo queryUserInfo4Bank(BusUser user);
	
	public ReceiveFromBankInfo queryUserSign(BusUser user);
}
