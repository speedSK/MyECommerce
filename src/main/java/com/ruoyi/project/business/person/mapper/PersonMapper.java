package com.ruoyi.project.business.person.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruoyi.project.business.person.domain.Person;

/**
 * 业务（犯人） 数据层
 * 
 * @author LiuNing
 * @date 2018-08-09
 */
@Repository
public interface PersonMapper 
{
	
	/**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public Person selectPersonByLoginName(String userName);
	
	/**
     * 查询人员管理
     * 
     * @param id 业务（犯人）ID
     * @return 人员管理
     */
	public Person selectPersonById(Long id);

	/**
     * 根据编号查询人员
     *
     * @param number 业务（犯人）编号
     * @return 人员管理
     */
	public Person selectPersonByNumber(String number);
	
	/**
     * 查询业务（犯人）列表
     * 
     * @param person 人员管理
     * @return 业务（犯人）集合
     */
	public List<Person> selectPersonList(Person person);
	
	/**
     * 新增业务（犯人）
     * 
     * @param person 人员管理
     * @return 结果
     */
	public int insertPerson(Person person);


	public Long insertAndGetId(Person person);

	/**
     * 修改业务（犯人）
     * 
     * @param person 人员管理
     * @return 结果
     */
	public int updatePerson(Person person);
	
	/**
     * 删除业务（犯人）
     * 
     * @param id 业务（犯人）ID
     * @return 结果
     */
	public int deletePersonById(Long id);
	
	/**
     * 批量删除业务（犯人）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePersonByIds(String[] ids);

	public int checkNumberUnique(String number);
}