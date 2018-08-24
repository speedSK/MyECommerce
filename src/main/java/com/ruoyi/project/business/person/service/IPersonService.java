package com.ruoyi.project.business.person.service;

import com.ruoyi.project.business.person.domain.Person;
import java.util.List;

/**
 * 人员管理 服务层
 * 
 * @author LiuNing
 * @date 2018-08-09
 */
public interface IPersonService 
{
	/**
     * 查询人员管理信息
     * 
     * @param id 人员管理ID
     * @return 人员管理信息
     */
	public Person selectPersonById(Long id);
	
	/**
     * 查询人员管理列表
     * 
     * @param person 人员管理信息
     * @return 人员管理集合
     */
	public List<Person> selectPersonList(Person person);
	
	/**
     * 新增人员管理
     * 
     * @param person 人员管理信息
     * @return 结果
     */
	public int insertPerson(Person person);
	
	/**
     * 修改人员管理
     * 
     * @param person 人员管理信息
     * @return 结果
     */
	public int updatePerson(Person person);
		
	/**
     * 删除人员管理信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePersonByIds(String ids);

	public int saveCash(Person person);

	public int saveBankCharge(Person person);
	
}
