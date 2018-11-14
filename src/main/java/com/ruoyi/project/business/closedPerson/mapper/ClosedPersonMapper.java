package com.ruoyi.project.business.closedPerson.mapper;

import com.ruoyi.project.business.closedPerson.domain.ClosedPerson;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 销户人员管理 数据层
 * 
 * @author ruoyi
 * @date 2018-11-12
 */
@Repository
public interface ClosedPersonMapper 
{
	/**
     * 查询销户人员管理信息
     * 
     * @param id 销户人员管理ID
     * @return 销户人员管理信息
     */
	public ClosedPerson selectClosedPersonById(Long id);
	
	/**
     * 查询销户人员管理列表
     * 
     * @param closedPerson 销户人员管理信息
     * @return 销户人员管理集合
     */
	public List<ClosedPerson> selectClosedPersonList(ClosedPerson closedPerson);
	
	/**
     * 新增销户人员管理
     * 
     * @param closedPerson 销户人员管理信息
     * @return 结果
     */
	public int insertClosedPerson(ClosedPerson closedPerson);
	
	/**
     * 修改销户人员管理
     * 
     * @param closedPerson 销户人员管理信息
     * @return 结果
     */
	public int updateClosedPerson(ClosedPerson closedPerson);
	
	/**
     * 删除销户人员管理
     * 
     * @param id 销户人员管理ID
     * @return 结果
     */
	public int deleteClosedPersonById(Long id);
	
	/**
     * 批量删除销户人员管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteClosedPersonByIds(String[] ids);
	
}