package com.information.project.business.person.service;

import com.information.project.business.person.domain.Person;
import java.util.List;

/**
 * 业务（犯人） 服务层
 * 
 * @author LiuNing
 * @date 2018-08-09
 */
public interface IPersonService 
{
	/**
     * 查询业务（犯人）信息
     * 
     * @param id 业务（犯人）ID
     * @return 业务（犯人）信息
     */
	public Person selectPersonById(Long id);
	
	/**
     * 查询业务（犯人）列表
     * 
     * @param person 业务（犯人）信息
     * @return 业务（犯人）集合
     */
	public List<Person> selectPersonList(Person person);
	
	/**
     * 新增业务（犯人）
     * 
     * @param person 业务（犯人）信息
     * @return 结果
     */
	public int insertPerson(Person person);
	
	/**
     * 修改业务（犯人）
     * 
     * @param person 业务（犯人）信息
     * @return 结果
     */
	public int updatePerson(Person person);
		
	/**
     * 删除业务（犯人）信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePersonByIds(String ids);
	
}
