package com.information.project.system.identity.service;

import com.information.project.system.identity.domain.Identity;
import java.util.List;

/**
 * 身份管理 服务层
 * 
 * @author LiuNing
 * @date 2018-08-10
 */
public interface IIdentityService 
{
	/**
     * 查询身份管理信息
     * 
     * @param id 身份管理ID
     * @return 身份管理信息
     */
	public Identity selectIdentityById(Long id);
	
	/**
     * 查询身份管理列表
     * 
     * @param identity 身份管理信息
     * @return 身份管理集合
     */
	public List<Identity> selectIdentityList(Identity identity);
	
	/**
     * 新增身份管理
     * 
     * @param identity 身份管理信息
     * @return 结果
     */
	public int insertIdentity(Identity identity);
	
	/**
     * 修改身份管理
     * 
     * @param identity 身份管理信息
     * @return 结果
     */
	public int updateIdentity(Identity identity);
		
	/**
     * 删除身份管理信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIdentityByIds(String ids);

	public List<Identity>  selectPostAll();
}
