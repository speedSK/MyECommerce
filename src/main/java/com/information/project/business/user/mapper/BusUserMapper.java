package com.information.project.business.user.mapper;

import com.information.project.business.user.domain.BusUser;

import java.util.List;

/**
 * 业务（犯人） 数据层
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public interface BusUserMapper 
{
	/**
     * 查询业务（犯人）信息
     * 
     * @param id 业务（犯人）ID
     * @return 业务（犯人）信息
     */
	public BusUser selectBusUserById(Long id);
	
	/**
     * 查询业务（犯人）列表
     * 
     * @param user 业务（犯人）信息
     * @return 业务（犯人）集合
     */
	public List<BusUser> selectBusUserList(BusUser user);
	
	/**
     * 新增业务（犯人）
     * 
     * @param user 业务（犯人）信息
     * @return 结果
     */
	public int insertBusUser(BusUser user);
	
	/**
     * 修改业务（犯人）
     * 
     * @param user 业务（犯人）信息
     * @return 结果
     */
	public int updateBusUser(BusUser user);
	
	/**
     * 删除业务（犯人）
     * 
     * @param id 业务（犯人）ID
     * @return 结果
     */
	public int deleteBusUserById(Long id);
	
	/**
     * 批量删除业务（犯人）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusUserByIds(String[] ids);
	
}