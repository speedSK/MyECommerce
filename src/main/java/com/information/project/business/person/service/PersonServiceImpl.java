package com.information.project.business.person.service;

import com.information.common.constant.Constants;
import com.information.common.utils.security.ShiroUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.person.mapper.PersonMapper;
import com.information.project.business.person.domain.Person;
import com.information.project.business.person.service.IPersonService;
import com.information.common.support.Convert;

/**
 * 业务（犯人） 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-09
 */
@Service
public class PersonServiceImpl implements IPersonService 
{
	@Autowired
	private PersonMapper personMapper;

	/**
     * 查询业务（犯人）信息
     * 
     * @param id 业务（犯人）ID
     * @return 业务（犯人）信息
     */
    @Override
	public Person selectPersonById(Long id)
	{
	    return personMapper.selectPersonById(id);
	}
	
	/**
     * 查询业务（犯人）列表
     * 
     * @param person 业务（犯人）信息
     * @return 业务（犯人）集合
     */
	@Override
	public List<Person> selectPersonList(Person person)
	{
	    return personMapper.selectPersonList(person);
	}
	
    /**
     * 新增业务（犯人）
     * 
     * @param person 业务（犯人）信息
     * @return 结果
     */
	@Override
	public int insertPerson(Person person)
	{
	    person.setStatus(Constants.STATUS_ACTIVE);

        person.setCreateBy(ShiroUtils.getUserId().toString());
	    return personMapper.insertPerson(person);
	}
	
	/**
     * 修改业务（犯人）
     * 
     * @param person 业务（犯人）信息
     * @return 结果
     */
	@Override
	public int updatePerson(Person person)
	{
	    person.setUpdateBy(ShiroUtils.getUserId().toString());
	    return personMapper.updatePerson(person);
	}

	/**
     * 删除业务（犯人）对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePersonByIds(String ids)
	{
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            Person person = new Person();
            person.setId(Long.valueOf(id));
            //初始化數據信息

            person.setStatus(Constants.STATUS_REMOVED);

            person.setUpdateBy(ShiroUtils.getUserId().toString());

            personMapper.updatePerson(person);

        }

        return 1;
	}
	
}
