package com.information.project.business.user.service;

import java.util.List;

import com.information.project.business.user.domain.BusUser;

public interface IPersonService {

	public List<BusUser> selectPersonList(BusUser person);
}
