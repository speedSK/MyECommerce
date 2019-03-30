package com.ruoyi.project.business.person.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
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
import com.ruoyi.project.business.account.domain.Account;
import com.ruoyi.project.business.account.mapper.AccountMapper;
import com.ruoyi.project.business.closedPerson.domain.ClosedPerson;
import com.ruoyi.project.business.closedPerson.mapper.ClosedPersonMapper;
import com.ruoyi.project.business.settleDate.service.ISettleDateService;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import com.ruoyi.project.business.transactionRecord.domain.TransactionRecord;
import com.ruoyi.project.business.transactionRecord.service.ITransactionRecordService;
import com.ruoyi.project.system.config.service.IConfigService;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.dept.service.IDeptService;
import com.ruoyi.project.system.device.service.IDeviceService;
import com.ruoyi.project.system.merchant.domain.Merchant;
import com.ruoyi.project.system.merchant.service.IMerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.person.mapper.PersonMapper;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.common.support.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 人员管理 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-09
 */
@Service
public class PersonServiceImpl implements IPersonService
{
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

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
    private IDeviceService deviceService;
    @Autowired
    private IConfigService configService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private AccountMapper accountMapper;
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
        personMapper.insertAndGetId(person);
        Merchant merchant = merchantService.selectMerchantById(Constants.ACCOUNT_ACTIVE_1_ID);
        merchant.setBalance(merchant.getBalance().add(person.getDeposit()));
        merchantService.updateMerchant(merchant);
        String deviceCode = deviceService.getDeviceCode();
        TradeRecord record = new TradeRecord();
        record.setMerchantCode(merchant.getId().toString());
        record.setJourno(IdGen.getJourno());
        record.setUserNumber(person.getId().toString());
        record.setTxcode(Constants.TX_CODE_DEPOSIT_INCOME);
        record.setTxamt(person.getDeposit());
        record.setToAcc(merchant.getId().toString());
        record.setStationCode(deviceCode);
        record.setCreateBy(ShiroUtils.getLoginName());
        record.setCreateTime(new Date());
        record.setRemark("用户押金");
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
		Merchant merchant = merchantService.selectMerchantById(Constants.ACCOUNT_ACTIVE_1_ID);
		merchant.setBalance(merchant.getBalance().add(person.getRecharge()));
        merchantService.updateMerchant(merchant);
        String deviceCode = deviceService.getDeviceCode();
		TradeRecord record = new TradeRecord();
		record.setJourno(IdGen.getJourno());
		record.setUserNumber(person.getId().toString());
        record.setBefore(oldBalance);
        record.setMerchantCode(merchant.getId().toString());
        record.setAfter(info.getBalance());
		record.setTxcode(Constants.TX_CODE_CASH_RECHARGE);
		record.setTxamt(person.getRecharge());
		record.setToAcc(merchant.getId().toString());
		record.setsettleDate(new Date());
        record.setStationCode(deviceCode);
        record.setCreateBy(ShiroUtils.getLoginName());
		record.setCreateTime(new Date());
		record.setRemark("现金充值");
		return tradeRecordService.insertTradeRecord(record);
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
            TradeRecord tradeRecord = new TradeRecord();
            Merchant merchant = merchantService.selectMerchantById(Constants.ACCOUNT_ACTIVE_2_ID);
            merchant.setBalance(merchant.getBalance().add(person.getBalance()).add(person.getDeposit()));
            merchantService.updateMerchant(merchant);
            tradeRecord.setMerchantCode(merchant.getId().toString());
            String deviceCode = deviceService.getDeviceCode();
            tradeRecord.setToAcc(merchant.getId().toString());
            tradeRecord.setJourno(IdGen.getJourno());
            tradeRecord.setStationCode(deviceCode);
            tradeRecord.setTxamt(person.getBalance().add(person.getDeposit()));
            tradeRecord.setTxcode(Constants.TX_CODE_CLOSE_ACCOUNT);
            tradeRecord.setUserNumber(person.getId().toString());
            tradeRecord.setBefore(person.getBalance());
            tradeRecord.setAfter(new BigDecimal(0));
            tradeRecord.setRemark("销户退款");
            tradeRecord.setCreateBy(ShiroUtils.getLoginName());
            tradeRecord.setCreateTime(new Date());
            tradeRecordService.insertTradeRecord(tradeRecord);
		}
		return 1;
	}

//    @Override
//    public String queryBankBalance(Person person) {
//        String txamt = "0";
//        TransVo vo = new TransVo();
//        vo.setYktTxcode("3021");
//        vo.setBankTxcode("YKT01");
//        vo.setYktNo(person.getNumber());
//        vo.setBankCardNo(person.getBankCardNumber());
//        String queryBalance = TransOfABC.transCommMsg("3021", vo);
//        String[] balanceArray = queryBalance.split("\\|");
//        if (StringUtils.isNotEmpty(balanceArray[0]) && balanceArray[0].equals("000000")) {
//            txamt = StringUtils.fen2Yuan(balanceArray[2].trim());
//        }
//        return txamt;
//    }

    @Override
    public String importUser(List<Person> personList, boolean updateSupport) {
        if (StringUtils.isNull(personList) || personList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String operName = ShiroUtils.getLoginName();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (Person person : personList)
        {
            try
            {
                // 验证是否存在这个用户
                Person p = personMapper.selectPersonByNumber(person.getNumber());
                if (StringUtils.isNull(p))
                {
                    int isParent = deptService.selectDeptCount(person.getDeptId());
                    if (isParent == 0) {
                        person.setPassword(password);
                        person.setCreateBy(operName);
                        this.insertPerson(person);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "、账号 " + person.getNumber() + " 导入成功");
                    } else {
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、账号 " + person.getNumber() + " 不可选择父级部门");
                    }
                }
                else if (updateSupport)
                {
                    person.setUpdateBy(operName);
                    person.setDeposit(null);
                    this.updatePerson(person);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + person.getNumber() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + person.getNumber() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + person.getNumber() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                logger.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public String checkNumberUnique(String number) {
        int count = personMapper.checkNumberUnique(number);
        if (count > 0)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    @Override
    public int resetPersonPwd(Person person) {
        person.randomSalt();
        String newPassWord = passwordService.encryptPassword(person.getNumber(), person.getPassword(), person.getSalt());
        person.setPassword(newPassWord);
        return updatePerson(person);
    }

    @Override
    @Transactional
    public int openAccount(String ids) {
        Account account = accountMapper.selectMacAccount();
        String newAccount = String.format("%010d", Long.parseLong(account.getPersonAccount())+1);
        Person person = personMapper.selectPersonById(Long.parseLong(ids));
        TransVo transVo = new TransVo();
        transVo.setJourno(IdGen.getJourno());
        transVo.setAccNumber(newAccount);
        transVo.setAccName(person.getName());
        String result = TransOfABC.transCommMsg(Constants.BANK_OPEN_CODE, transVo);
        if (result.equals("0000")) {
            person.setBankCardNumber(newAccount);
            person.setUpdateBy(ShiroUtils.getLoginName());
            personMapper.updatePerson(person);
            account = new Account();
            account.setPersonAccount(newAccount);
            account.setPersonId(person.getId());
            account.setCreateBy(ShiroUtils.getLoginName());
            return accountMapper.insertAccount(account);
        }
        return 0;
    }

    @Override
    public List<Person> selectUnopenedList(Person person) {
        return personMapper.selectUnopenedList(person);
    }

    @Override
    public void clearAlreadyCost() {
        personMapper.clearAlreadyCost();
    }

    @Override
    public void bankBatchRecharge(String[] split) {
        Person person = personMapper.selectPersonByBankNumber(split[2]);
        if (StringUtils.isNotNull(person)) {
            BigDecimal recharge = new BigDecimal(split[14]);
            person.setBalance(person.getBalance().add(recharge));
            TradeRecord record = new TradeRecord();
            record.setJourno(IdGen.getJourno());
            Merchant merchant = merchantService.selectMerchantById(Constants.ACCOUNT_ACTIVE_1_ID);
            merchant.setBalance(merchant.getBalance().add(recharge));
            merchantService.updateMerchant(merchant);
            String deviceCode = deviceService.getDeviceCode();
            record.setMerchantCode(merchant.getId().toString());
            record.setJourno(IdGen.getJourno());
            record.setUserNumber(person.getId().toString());
            record.setTxcode(Constants.TX_CODE_BANK_RECHARGE);
            record.setTxamt(recharge);
            record.setToAcc(merchant.getId().toString());
            record.setStationCode(deviceCode);
            record.setCreateBy("system");
            record.setCreateTime(new Date());
            record.setRemark("银行充值");
            TransactionRecord transactionRecord = new TransactionRecord();
            transactionRecord.setCode(Constants.TX_CODE_BANK_RECHARGE);
            transactionRecord.setIdNumber(person.getNumber());
            transactionRecord.setUserName(person.getName());
            transactionRecord.setBankNumber(split[2]);
            transactionRecord.setTransDate(split[4]+" "+split[6]);
            transactionRecord.setAmount(split[14]);
            transactionRecord.setBankCode(split[18]);
            transactionRecord.setCreateBy("system");
            bankRecharge(person, record, transactionRecord);
        }
    }
    @Transactional
    public void bankRecharge(Person person, TradeRecord record, TransactionRecord transactionRecord) {
        personMapper.updatePerson(person);
        tradeRecordService.insertTradeRecord(record);
        transactionRecordService.insertTransactionRecord(transactionRecord);
    }
}
