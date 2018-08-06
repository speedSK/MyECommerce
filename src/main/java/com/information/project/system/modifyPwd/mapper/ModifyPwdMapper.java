package com.information.project.system.modifyPwd.mapper;

import com.information.project.system.modifyPwd.domain.ModifyPwd;
import java.util.List;	

/**
 * 密码修改申请 数据层
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public interface ModifyPwdMapper 
{
	/**
     * 查询密码修改申请信息
     * 
     * @param id 密码修改申请ID
     * @return 密码修改申请信息
     */
	public ModifyPwd selectModifyPwdById(Long id);
	
	/**
     * 查询密码修改申请列表
     * 
     * @param modifyPwd 密码修改申请信息
     * @return 密码修改申请集合
     */
	public List<ModifyPwd> selectModifyPwdList(ModifyPwd modifyPwd);
	
	/**
     * 新增密码修改申请
     * 
     * @param modifyPwd 密码修改申请信息
     * @return 结果
     */
	public int insertModifyPwd(ModifyPwd modifyPwd);
	
	/**
     * 修改密码修改申请
     * 
     * @param modifyPwd 密码修改申请信息
     * @return 结果
     */
	public int updateModifyPwd(ModifyPwd modifyPwd);
	
	/**
     * 删除密码修改申请
     * 
     * @param id 密码修改申请ID
     * @return 结果
     */
	public int deleteModifyPwdById(Long id);
	
	/**
     * 批量删除密码修改申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteModifyPwdByIds(String[] ids);
	
}