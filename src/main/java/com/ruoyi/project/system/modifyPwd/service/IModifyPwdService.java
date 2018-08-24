package com.ruoyi.project.system.modifyPwd.service;

import com.ruoyi.project.system.modifyPwd.domain.ModifyPwd;
import java.util.List;

/**
 * 密码修改申请 服务层
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
public interface IModifyPwdService 
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
     * 修改密码修改申请
     * 
     * @param modifyPwd 密码修改申请信息
     * @return 结果
     */
	public int updateModifyPwd(ModifyPwd modifyPwd);
		

}
