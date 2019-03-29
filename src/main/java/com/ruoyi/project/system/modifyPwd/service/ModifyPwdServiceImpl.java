package com.ruoyi.project.system.modifyPwd.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import java.util.List;

import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.modifyPwd.mapper.ModifyPwdMapper;
import com.ruoyi.project.system.modifyPwd.domain.ModifyPwd;
import com.ruoyi.project.system.modifyPwd.service.IModifyPwdService;

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
    private PasswordService passwordService;


	@Autowired
	private PersonMapper personMapper;

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
    public int updateModifyPwd(ModifyPwd modifyPwd) {
        if (!"0".equals(modifyPwd.getAgreest()) && !StringUtils.isEmpty(modifyPwd.getNewPwd())) {
            modifyPwd.setUpdateBy(ShiroUtils.getLoginName());
            modifyPwdMapper.updateModifyPwd(modifyPwd);
            if ("1".equals(modifyPwd.getAgreest())) {
                //修改密码
                Person person = new Person();
                person.setId(modifyPwd.getUserid());
                person.randomSalt();
                person.setPassword(passwordService.encryptPassword(modifyPwd.getNumber(), modifyPwd.getNewPwd(), person.getSalt()));
                person.setUpdateBy(ShiroUtils.getLoginName());
                personMapper.updatePerson(person);
            }

        }
        return 1;
    }

    @Override
    public int insertIntoModifyPwd(ModifyPwd modifyPwd) {
        return modifyPwdMapper.insertModifyPwd(modifyPwd);
    }

}
