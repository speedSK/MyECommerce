package com.ruoyi.project.business.person.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.project.bank.TransOfABC;
import com.ruoyi.project.bank.domain.TransVo;
import com.ruoyi.project.business.closedPerson.domain.ClosedPerson;
import com.ruoyi.project.business.closedPerson.mapper.ClosedPersonMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.person.mapper.PersonMapper;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.common.support.Convert;

/**
 * 人员管理 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-09
 */
@Service
public class PersonServiceImpl implements IPersonService 
{
	@Autowired
	private PersonMapper personMapper;
	@Autowired
	private ClosedPersonMapper closedPersonMapper;
    @Autowired
    private PasswordService passwordService;

	/**
     * 查询人员管理信息
     * 
     * @param id 人员管理ID
     * @return 人员管理信息
     */
    @Override
	public Person selectPersonById(Long id)
	{
	    return personMapper.selectPersonById(id);
	}
	
	/**
     * 查询人员管理列表
     * 
     * @param person 人员管理信息
     * @return 人员管理集合
     */
	@Override
	public List<Person> selectPersonList(Person person)
	{

		person.setStatus(Constants.STATUS_ACTIVE);
		return personMapper.selectPersonList(person);
	}
	
    /**
     * 新增人员管理
     * 
     * @param person 人员管理信息
     * @return 结果
     */
	@Override
	public int insertPerson(Person person)
	{
	    person.randomSalt();
	    person.setPassword(passwordService.encryptPassword(person.getNumber(), person.getPassword(), person.getSalt()));
        person.setCreateBy(ShiroUtils.getLoginName());
	    person.setStatus(Constants.STATUS_ACTIVE);
	    return personMapper.insertPerson(person);
	}
	
	/**
     * 修改人员管理
     * 
     * @param person 人员管理信息
     * @return 结果
     */
	@Override
	public int updatePerson(Person person)
	{
	    person.setUpdateBy(ShiroUtils.getLoginName());
	    return personMapper.updatePerson(person);
	}

	/**
     * 删除人员管理对象
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
            person.setUpdateBy(ShiroUtils.getLoginName());
            personMapper.updatePerson(person);

        }

        return 1;
	}

	@Override
	public int saveCash(Person person) {
		Person info = personMapper.selectPersonById(person.getId());
		info.setBalance(info.getBalance().add(person.getRecharge()));
		return personMapper.updatePerson(info);
	}

	@Override
	public int saveBankCharge(Person person) {
		Person info = personMapper.selectPersonById(person.getId());
		TransVo vo = new TransVo();
		vo.setYktTxcode("3011");
		vo.setBankTxcode("YKT03");
		vo.setTxamt(person.getBankBalance());
		vo.setYktJourno("");
		vo.setUsername(person.getName());
		vo.setIdserial2(person.getIdcard());
		String transResult = TransOfABC.transCommMsg("3011", vo);
		String[] transArray = transResult.split("\\|");
		if (StringUtils.isNotEmpty(transArray[0]) && transArray[0].equals("000000")) {
			info.setBalance(info.getBalance().add(new BigDecimal(StringUtils.fen2Yuan(person.getBankBalance()))));
		}
		return personMapper.updatePerson(info);
	}

	@Override
	public int deletePersonAccount(String ids) {
		String [] idsArray = Convert.toStrArray(ids);
		for (String id: idsArray) {
			Person person = personMapper.selectPersonById(Long.parseLong(id));
            ClosedPerson closedPerson = new ClosedPerson();
            BeanUtils.copyProperties(person, closedPerson);
            closedPerson.setFlag(Constants.PERSON_CLOSE);
			closedPerson.setUpdateBy(ShiroUtils.getLoginName());
            closedPerson.setUpdateTime(new Date());
            closedPersonMapper.insertClosedPerson(closedPerson);
            personMapper.deletePersonById(Long.parseLong(id));
		}
		return 1;
	}

    @Override
    public String queryBankBalance(Person person) {
        String txamt = "0";
        TransVo vo = new TransVo();
        vo.setYktTxcode("3021");
        vo.setBankTxcode("YKT01");
        vo.setYktNo(person.getNumber());
        vo.setBankCardNo(person.getBankCardNumber());
        String queryBalance = TransOfABC.transCommMsg("3021", vo);
        String[] balanceArray = queryBalance.split("\\|");
        if (StringUtils.isNotEmpty(balanceArray[0]) && balanceArray[0].equals("000000")) {
            txamt = balanceArray[2];
        }
        return txamt;
    }
}
