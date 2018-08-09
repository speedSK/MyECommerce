package com.information.project.system.modifyPwd.service;

import com.information.common.constant.Constants;
import com.information.common.utils.StringUtils;
import com.information.common.utils.security.ShiroUtils;
import java.util.List;

import com.information.project.business.person.domain.Person;
import com.information.project.business.person.mapper.PersonMapper;
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
 * @date 2018-08-07
 */
@Service
public class ModifyPwdServiceImpl implements IModifyPwdService 
{
	@Autowired
	private ModifyPwdMapper modifyPwdMapper;


	@Autowired
	private PersonMapper busUserMapper;

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
		modifyPwd.setStatus(Constants.STATUS_ACTIVE);
	    return modifyPwdMapper.selectModifyPwdList(modifyPwd);
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
		if( 0 != modifyPwd.getUserid() &&  !"0".equals(modifyPwd.getAgreest())  &&   !StringUtils.isEmpty(modifyPwd.getNewPwd())   ){
			modifyPwd.setUpdateBy(ShiroUtils.getUserId().toString());
			modifyPwdMapper.updateModifyPwd(modifyPwd);

			//修改密码

			Person buser = new Person();
			buser.setId(modifyPwd.getUserid());
			buser.setPassword(modifyPwd.getNewPwd());
			busUserMapper.updatePerson(buser) ;

		}
		return 1 ;

	}

}
