package com.ruoyi.project.system.identity.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.security.ShiroUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.identity.mapper.IdentityMapper;
import com.ruoyi.project.system.identity.domain.Identity;
import com.ruoyi.project.system.identity.service.IIdentityService;
import com.ruoyi.common.support.Convert;

/**
 * 身份管理 服务层实现
 *
 * @author LiuNing
 * @date 2018-08-10
 */
@Service
public class IdentityServiceImpl implements IIdentityService
{
	@Autowired
	private IdentityMapper identityMapper;

	/**
     * 查询身份管理信息
     *
     * @param id 身份管理ID
     * @return 身份管理信息
     */
    @Override
	public Identity selectIdentityById(Long id)
	{
	    return identityMapper.selectIdentityById(id);
	}

	/**
     * 查询身份管理列表
     *
     * @param identity 身份管理信息
     * @return 身份管理集合
     */
	@Override
	public List<Identity> selectIdentityList(Identity identity)
	{
	    identity.setStatus(Constants.STATUS_ACTIVE);
	    return identityMapper.selectIdentityList(identity);
	}

    /**
     * 新增身份管理
     *
     * @param identity 身份管理信息
     * @return 结果
     */
	@Override
	public int insertIdentity(Identity identity)
	{
	    identity.setStatus(Constants.STATUS_ACTIVE);

        identity.setCreateBy(ShiroUtils.getUserId().toString());
	    return identityMapper.insertIdentity(identity);
	}

	/**
     * 修改身份管理
     *
     * @param identity 身份管理信息
     * @return 结果
     */
	@Override
	public int updateIdentity(Identity identity)
	{
	    identity.setUpdateBy(ShiroUtils.getUserId().toString());
	    return identityMapper.updateIdentity(identity);
	}

	/**
     * 删除身份管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteIdentityByIds(String ids)
	{
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            Identity identity = new Identity();
            identity.setId(Long.valueOf(id));
            //初始化數據信息

            identity.setStatus(Constants.STATUS_REMOVED);

            identity.setUpdateBy(ShiroUtils.getUserId().toString());

            identityMapper.updateIdentity(identity);

        }

        return 1;
	}

	@Override
	public List<Identity> selectPostAll() {
		return identityMapper.selectIdentityAll();
	}

}
