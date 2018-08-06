package com.information.project.bank.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.information.common.utils.SysConfigUtil;
import com.information.project.bank.TransOfABC;
import com.information.project.bank.domain.ReceiveFromBankInfo;
import com.information.project.business.user.domain.BusUser;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BankServiceImplTest {
	
	@Autowired
	private IBankService bankService;

	@Test
	public void testQueryUserInfo4Bank() throws Exception {
		ReceiveFromBankInfo info = bankService.queryUserInfo4Bank(new BusUser());
		System.out.println(info.getResponsecode());
//		System.out.println(SysConfigUtil.getNodeValue("sys.user.initPassword"));
//		System.out.println(TransOfABC.querySign("123".getBytes()));
	}

//	@Test
//	public void testQueryUserSign() {
//		fail("Not yet implemented");
//	}

}
