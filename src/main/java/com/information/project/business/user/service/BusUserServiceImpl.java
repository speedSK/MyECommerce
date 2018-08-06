package com.information.project.business.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.user.mapper.BusUserMapper;
import com.information.project.business.user.domain.BusUser;
import com.information.project.business.user.service.IBusUserService;
import com.information.common.support.Convert;

/**
 * 业务（犯人） 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class BusUserServiceImpl implements IBusUserService
{
	@Autowired
	private BusUserMapper userMapper;

	/**
     * 查询业务（犯人）信息
     * 
     * @param id 业务（犯人）ID
     * @return 业务（犯人）信息
     */
    @Override
	public BusUser selectBusUserById(Long id)
	{
	    return userMapper.selectBusUserById(id);
	}
	
	/**
     * 查询业务（犯人）列表
     * 
     * @param user 业务（犯人）信息
     * @return 业务（犯人）集合
     */
	@Override
	public List<BusUser> selectBusUserList(BusUser user)
	{
	    return userMapper.selectBusUserList(user);
	}
	
    /**
     * 新增业务（犯人）
     * 
     * @param user 业务（犯人）信息
     * @return 结果
     */
	@Override
	public int insertBusUser(BusUser user)
	{
	    return userMapper.insertBusUser(user);
	}
	
	/**
     * 修改业务（犯人）
     * 
     * @param user 业务（犯人）信息
     * @return 结果
     */
	@Override
	public int updateBusUser(BusUser user)
	{
	    return userMapper.updateBusUser(user);
	}

	/**
     * 删除业务（犯人）对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusUserByIds(String ids)
	{
		return userMapper.deleteBusUserByIds(Convert.toStrArray(ids));
	}
	
}
