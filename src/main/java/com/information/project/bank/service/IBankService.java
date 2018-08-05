package com.information.project.bank.service;

import com.information.project.bank.domain.ReceiveFromBankInfo;
import com.information.project.business.user.domain.BusUserExample;

public interface IBankService {

	public ReceiveFromBankInfo queryUserInfo4Bank(BusUserExample ex);
	
	public ReceiveFromBankInfo queryUserSign(BusUserExample ex);
}
