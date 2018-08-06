package com.information.project.system.modifyPwd.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.system.modifyPwd.mapper.ModifyPwdMapper;
import com.information.project.system.modifyPwd.domain.ModifyPwd;
import com.information.project.system.modifyPwd.service.IModifyPwdService;
import com.information.common.support.Convert;

/**
 * 密码修改申请 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class ModifyPwdServiceImpl implements IModifyPwdService 
{
	@Autowired
	private ModifyPwdMapper modifyPwdMapper;

	/**
     * 查询密码修改申请信息
     * 
     * @param id 密码修改申请ID
     * @return 密码修改申请信息
     */
    @Override
	public ModifyPwd selectModifyPwdById(Long id)
	{
	    return modifyPwdMapper.selectModifyPwdById(id);
	}
	
	/**
     * 查询密码修改申请列表
     * 
     * @param modifyPwd 密码修改申请信息
     * @return 密码修改申请集合
     */
	@Override
	public List<ModifyPwd> selectModifyPwdList(ModifyPwd modifyPwd)
	{
	    return modifyPwdMapper.selectModifyPwdList(modifyPwd);
	}
	
    /**
     * 新增密码修改申请
     * 
     * @param modifyPwd 密码修改申请信息
     * @return 结果
     */
	@Override
	public int insertModifyPwd(ModifyPwd modifyPwd)
	{
	    return modifyPwdMapper.insertModifyPwd(modifyPwd);
	}
	
	/**
     * 修改密码修改申请
     * 
     * @param modifyPwd 密码修改申请信息
     * @return 结果
     */
	@Override
	public int updateModifyPwd(ModifyPwd modifyPwd)
	{
	    return modifyPwdMapper.updateModifyPwd(modifyPwd);
	}

	/**
     * 删除密码修改申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteModifyPwdByIds(String ids)
	{
		return modifyPwdMapper.deleteModifyPwdByIds(Convert.toStrArray(ids));
	}
	
}
