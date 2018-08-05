package com.information.project.business.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.information.project.business.user.domain.BusUser;
import com.information.project.business.user.domain.BusUserExample;
import com.information.project.business.user.mapper.BusUserMapper;

@Service
public class PersonServiceImpl implements IPersonService{
	@Autowired
	private BusUserMapper busUserMapper;
	@Override
	public List<BusUser> selectPersonList(BusUser person) {
		BusUserExample ex = new BusUserExample();
		return busUserMapper.selectByExample(ex);
	}

}
