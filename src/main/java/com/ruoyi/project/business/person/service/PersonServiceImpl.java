package com.ruoyi.project.business.person.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.IdGen;
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
import com.ruoyi.project.business.settleDate.service.ISettleDateService;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import com.ruoyi.project.business.transactionRecord.domain.TransactionRecord;
import com.ruoyi.project.business.transactionRecord.service.ITransactionRecordService;
import com.ruoyi.project.system.merchant.domain.Merchant;
import com.ruoyi.project.system.merchant.service.IMerchantService;
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
    @Autowired
    private ITradeRecordService tradeRecordService;
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private ISettleDateService settleDateService;
    @Autowired
    private ITransactionRecordService transactionRecordService;

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
        personMapper.insertPerson(person);
        Merchant merchant = merchantService.selectMerchantById(1L);
        merchantService.updateMerchant(merchant);
        merchant.setBalance(merchant.getBalance().add(person.getDeposit()));
        TradeRecord record = new TradeRecord();
        record.setJourno(IdGen.getJourno());
        record.setUserNumber(person.getNumber());
        record.setTxcode("1000");
        record.setTxamt(person.getDeposit());
        record.setToAcc(merchant.getMerchantCode());
        record.setsettleDate(settleDateService.selectSettleDateById(1L).getSettleDate());
        record.setStationCode("1001");
	    return tradeRecordService.insertTradeRecord(record);
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
        BigDecimal oldBalance = info.getBalance();
		info.setBalance(oldBalance.add(person.getRecharge()));
		personMapper.updatePerson(info);
		Merchant merchant = merchantService.selectMerchantById(1L);
		merchant.setBalance(merchant.getBalance().add(person.getRecharge()));
        merchantService.updateMerchant(merchant);
		TradeRecord record = new TradeRecord();
		record.setJourno(IdGen.getJourno());
		record.setUserNumber(person.getNumber());
        record.setBefore(oldBalance);
        record.setAfter(info.getBalance());
		record.setTxcode("1001");
		record.setTxamt(person.getRecharge());
		record.setToAcc(merchant.getMerchantCode());
		record.setsettleDate(settleDateService.selectSettleDateById(1L).getSettleDate());
		record.setStationCode("1001");
		return tradeRecordService.insertTradeRecord(record);
	}

	@Override
	public int saveBankCharge(Person person) {
        int result = 0;
		Person info = personMapper.selectPersonById(person.getId());
        BigDecimal oldBalance = info.getBalance();
        String journo = IdGen.getJourno();
		TransVo vo = new TransVo();
		vo.setYktTxcode("3011");
		vo.setBankTxcode("YKT03");
        vo.setYktNo(info.getNumber());
        vo.setTxamt(StringUtils.yuan2Fen(person.getBankBalance()));
        vo.setYktJourno(journo);
		vo.setUsername(info.getName());
		vo.setIdserial2(info.getIdcard());
		vo.setBankCardNo(info.getBankCardNumber());
		String transResult = TransOfABC.transCommMsg("3011", vo);
		String[] transArray = transResult.split("\\|");
		if (StringUtils.isNotEmpty(transArray[0]) && transArray[0].equals("000000")) {
			info.setBalance(info.getBalance().add(new BigDecimal(person.getBankBalance())));
            personMapper.updatePerson(info);
            TransactionRecord transactionRecord = new TransactionRecord();
            transactionRecord.setCode("1002");
            transactionRecord.setBankCode("YKT03");
            transactionRecord.setTransDate(DateUtils.dateTimeNow("yyyyMMdd"));
            transactionRecord.setTransIdserial(journo);
            transactionRecord.setUserCode(info.getNumber());
            transactionRecord.setUserName(info.getName());
            transactionRecord.setIdNumber(info.getIdcard());
            transactionRecord.setBankNumber(info.getBankCardNumber());
            transactionRecord.setAmount(person.getRecharge().toString());
            transactionRecord.setStatus("0");
            transactionRecordService.insertTransactionRecord(transactionRecord);
            Merchant merchant = merchantService.selectMerchantById(1L);
            merchant.setBalance(merchant.getBalance().add(person.getRecharge()));
            merchantService.updateMerchant(merchant);
            TradeRecord record = new TradeRecord();
            record.setJourno(journo);
            record.setUserNumber(person.getNumber());
            record.setBefore(oldBalance);
            record.setAfter(info.getBalance());
            record.setTxcode("1002");
            record.setTxamt(person.getRecharge());
            record.setToAcc(merchant.getMerchantCode());
            record.setsettleDate(settleDateService.selectSettleDateById(1L).getSettleDate());
            record.setStationCode("1001");
            tradeRecordService.insertTradeRecord(record);
            result = 1;
		}

        return result;
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
            txamt = StringUtils.fen2Yuan(balanceArray[2].trim());
        }
        return txamt;
    }

    @Override
    public String importUser(List<Person> userList, boolean updateSupport) {
        return null;
    }
}
