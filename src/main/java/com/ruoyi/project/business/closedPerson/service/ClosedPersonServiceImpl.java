package com.ruoyi.project.business.closedPerson.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.closedPerson.mapper.ClosedPersonMapper;
import com.ruoyi.project.business.closedPerson.domain.ClosedPerson;
import com.ruoyi.project.business.closedPerson.service.IClosedPersonService;
import com.ruoyi.common.support.Convert;

/**
 * 销户人员管理 服务层实现
 * 
 * @author ruoyi
 * @date 2018-11-12
 */
@Service
public class ClosedPersonServiceImpl implements IClosedPersonService 
{
	@Autowired
	private ClosedPersonMapper closedPersonMapper;

	/**
     * 查询销户人员管理信息
     * 
     * @param id 销户人员管理ID
     * @return 销户人员管理信息
     */
    @Override
	public ClosedPerson selectClosedPersonById(Long id)
	{
	    return closedPersonMapper.selectClosedPersonById(id);
	}
	
	/**
     * 查询销户人员管理列表
     * 
     * @param closedPerson 销户人员管理信息
     * @return 销户人员管理集合
     */
	@Override
	public List<ClosedPerson> selectClosedPersonList(ClosedPerson closedPerson)
	{
	    return closedPersonMapper.selectClosedPersonList(closedPerson);
	}
	
    /**
     * 新增销户人员管理
     * 
     * @param closedPerson 销户人员管理信息
     * @return 结果
     */
	@Override
	public int insertClosedPerson(ClosedPerson closedPerson)
	{
	    return closedPersonMapper.insertClosedPerson(closedPerson);
	}
	
	/**
     * 修改销户人员管理
     * 
     * @param closedPerson 销户人员管理信息
     * @return 结果
     */
	@Override
	public int updateClosedPerson(ClosedPerson closedPerson)
	{
	    return closedPersonMapper.updateClosedPerson(closedPerson);
	}

	/**
     * 删除销户人员管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteClosedPersonByIds(String ids)
	{
		return closedPersonMapper.deleteClosedPersonByIds(Convert.toStrArray(ids));
	}
	
}
