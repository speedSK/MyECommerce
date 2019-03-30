package com.ruoyi.project.business.uploadRecord.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.IdGen;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.project.business.settleDate.service.ISettleDateService;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import com.ruoyi.project.business.uploadRecord.domain.BatchCostVo;
import com.ruoyi.project.system.device.domain.Device;
import com.ruoyi.project.system.device.service.IDeviceService;
import com.ruoyi.project.system.merchant.domain.Merchant;
import com.ruoyi.project.system.merchant.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.project.business.uploadRecord.mapper.UploadRecordMapper;
import com.ruoyi.project.business.person.domain.Person;
import com.ruoyi.project.business.person.mapper.PersonMapper;
import com.ruoyi.project.business.uploadRecord.domain.BatchRechargeVo;
import com.ruoyi.project.business.uploadRecord.domain.UploadRecord;
import com.ruoyi.project.business.uploadRecord.service.IUploadRecordService;
import com.ruoyi.common.support.Convert;

/**
 * 功能导入记录 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-08
 */
@Service
public class UploadRecordServiceImpl implements IUploadRecordService 
{
	@Autowired
	private UploadRecordMapper uploadRecordMapper;
	
	@Autowired
	private PersonMapper personMapper;
    @Autowired
    private ITradeRecordService tradeRecordService;
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private IDeviceService deviceService;

	/**
     * 查询功能导入记录信息
     * 
     * @param id 功能导入记录ID
     * @return 功能导入记录信息
     */
    @Override
	public UploadRecord selectUploadRecordById(Long id)
	{
	    return uploadRecordMapper.selectUploadRecordById(id);
	}
	
	/**
     * 查询功能导入记录列表
     * 
     * @param uploadRecord 功能导入记录信息
     * @return 功能导入记录集合
     */
	@Override
	public List<UploadRecord> selectUploadRecordList(UploadRecord uploadRecord)
	{
	    return uploadRecordMapper.selectUploadRecordList(uploadRecord);
	}
	
    /**
     * 新增功能导入记录
     * 
     * @param uploadRecord 功能导入记录信息
     * @return 结果
     */
	@Override
	@Transactional
	public int insertUploadRecord(UploadRecord uploadRecord)
	{
	    uploadRecord.setStatus(Constants.STATUS_ACTIVE);

        uploadRecord.setCreateBy(ShiroUtils.getLoginName());
	    return uploadRecordMapper.insertUploadRecord(uploadRecord);
	}
	
	/**
     * 修改功能导入记录
     * 
     * @param uploadRecord 功能导入记录信息
     * @return 结果
     */
	@Override
	@Transactional
	public int updateUploadRecord(UploadRecord uploadRecord)
	{
	    uploadRecord.setUpdateBy(ShiroUtils.getLoginName());
	    return uploadRecordMapper.updateUploadRecord(uploadRecord);
	}

	/**
     * 删除功能导入记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional
	public int deleteUploadRecordByIds(String ids)
	{
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            UploadRecord uploadRecord = new UploadRecord();
            uploadRecord.setId(Long.valueOf(id));
            //初始化數據信息

            uploadRecord.setStatus(Constants.STATUS_REMOVED);

            uploadRecord.setUpdateBy(ShiroUtils.getLoginName());

            uploadRecordMapper.updateUploadRecord(uploadRecord);

        }

        return 1;
	}

	@Override
	@Transactional
	public AjaxResult saveRecharge(MultipartFile file) throws IOException, Exception {
		AjaxResult resultJson = null;
		ExcelUtil<BatchRechargeVo> util = new ExcelUtil<BatchRechargeVo>(BatchRechargeVo.class);
    	List<BatchRechargeVo> list = util.importExcel("批量充值", file.getInputStream());
		List<BatchRechargeVo> failList = new ArrayList<>();
		UploadRecord uploadRecord = new UploadRecord();
		uploadRecord.setModule("批量充值导入");
		uploadRecord.setUploadName(file.getOriginalFilename());
		String deviceCode = deviceService.getDeviceCode();
		long successCount = 0;
		long failCount = 0 ;
		for (BatchRechargeVo batchRechargeVo : list) {
			if (batchRechargeVo.getNumber()!=null&&batchRechargeVo.getAmount()!=null) {
				Person person = new Person();
				person.setNumber(batchRechargeVo.getNumber());
				person.setStatus(Constants.STATUS_ACTIVE);
				person.setFlag(Constants.PERSON_ACTIVE);
				List<Person> pList = personMapper.selectPersonList(person);
				if (pList==null||pList.size()==0) {
					failCount++;
					batchRechargeVo.setFailure("用户不存在或状态异常");
					failList.add(batchRechargeVo);
				} else {
					person = pList.get(0);
					if (person.getBalance().add(new BigDecimal(batchRechargeVo.getAmount())).compareTo(new BigDecimal(0)) == -1) {
						failCount++;
						batchRechargeVo.setFailure("余额不能为负");
						failList.add(batchRechargeVo);
					} else {
						successCount++;
						TradeRecord record = new TradeRecord();
						record.setBefore(person.getBalance());
						person.setBalance(person.getBalance().add(new BigDecimal(batchRechargeVo.getAmount())));
						record.setAfter(person.getBalance());
						//TODO 计算校验字段
						personMapper.updatePerson(person);
						Merchant merchant = merchantService.selectMerchantById(Constants.ACCOUNT_ACTIVE_1_ID);
						merchant.setBalance(merchant.getBalance().add(person.getBalance()));
						merchantService.updateMerchant(merchant);
						record.setMerchantCode(merchant.getId().toString());
						record.setJourno(IdGen.getJourno());
						record.setUserNumber(person.getId().toString());
						record.setTxcode(Constants.TX_CODE_IMPORT_RECHARGE);
						record.setTxamt(new BigDecimal(batchRechargeVo.getAmount()));
						record.setToAcc(merchant.getId().toString());
						record.setStationCode(deviceCode);
						record.setCreateBy(ShiroUtils.getLoginName());
						record.setCreateTime(new Date());
						record.setRemark("批量充值");
						tradeRecordService.insertTradeRecord(record);
					}
				}
			} else {
				failCount++;
				batchRechargeVo.setFailure("编号和金额列都不能为空");
				failList.add(batchRechargeVo);
			}
		}
		uploadRecord.setSuccessCount(successCount);
		uploadRecord.setFailCount(failCount);
		uploadRecord.setStatus(Constants.STATUS_ACTIVE);
		
		if (!failList.isEmpty()) {
			resultJson = util.exportExcel(failList, "批量充值");
			uploadRecord.setFailName(resultJson.get("msg").toString());
		} else {
			resultJson = AjaxResult.success();
		}
		uploadRecordMapper.insertUploadRecord(uploadRecord);
		return resultJson;
	}

	@Override
	@Transactional
	public AjaxResult saveCost(MultipartFile file) throws IOException, Exception {
		AjaxResult resultJson = null;
		ExcelUtil<BatchCostVo> util = new ExcelUtil<BatchCostVo>(BatchCostVo.class);
    	List<BatchCostVo> list = util.importExcel("批量消费", file.getInputStream());
		List<BatchCostVo> failList = new ArrayList<>();
		UploadRecord uploadRecord = new UploadRecord();
		uploadRecord.setModule("批量消费导入");
		uploadRecord.setUploadName(file.getOriginalFilename());
		String deviceCode = deviceService.getDeviceCode();
		long successCount = 0;
		long failCount = 0 ;
		for (BatchCostVo batchCostVo : list) {
			if (batchCostVo.getNumber()!=null&&batchCostVo.getAmount()!=null&&batchCostVo.getMerchant()!=null) {
				Person person = new Person();
				person.setNumber(batchCostVo.getNumber());
				person.setStatus(Constants.STATUS_ACTIVE);
				person.setFlag(Constants.PERSON_ACTIVE);
				List<Person> pList = personMapper.selectPersonList(person);
				if (pList==null||pList.size()==0) {
					failCount++;
					batchCostVo.setFailure("用户不存在或状态异常");
					failList.add(batchCostVo);
				} else {
					Merchant merchant = merchantService.selectMerchantById(batchCostVo.getMerchant());
                    if (StringUtils.isNull(merchant)) {
                        failCount++;
                        batchCostVo.setFailure("商户不存在");
                        failList.add(batchCostVo);
                    } else {
                        person = pList.get(0);
                        BigDecimal oldBalance = person.getBalance();
						if (person.getBalance().subtract(new BigDecimal(batchCostVo.getAmount())).compareTo(new BigDecimal(0)) == -1) {
							failCount++;
							batchCostVo.setFailure("余额不能为负");
							failList.add(batchCostVo);
						} else {
							successCount++;
							BigDecimal cost = new BigDecimal(batchCostVo.getAmount());
							person.setBalance(person.getBalance().subtract(cost));
							//TODO 计算校验字段
							personMapper.updatePerson(person);
							merchant.setBalance(merchant.getBalance().add(cost));
							merchantService.updateMerchant(merchant);
							TradeRecord record = new TradeRecord();
							record.setMerchantCode(merchant.getId().toString());
							record.setJourno(IdGen.getJourno());
							record.setUserNumber(person.getId().toString());
							record.setBefore(oldBalance);
							record.setAfter(person.getBalance());
							record.setTxcode(Constants.TX_CODE_IMPORT_COST);
							record.setTxamt(cost);
							record.setToAcc(merchant.getId().toString());
							record.setStationCode(deviceCode);
							record.setCreateBy(ShiroUtils.getLoginName());
							record.setCreateTime(new Date());
							record.setRemark("批量消费");
							tradeRecordService.insertTradeRecord(record);
						}
                    }
				}
			} else {
				failCount++;
				batchCostVo.setFailure("编号、金额和商户名称列都不能为空");
				failList.add(batchCostVo);
			}
		}
		uploadRecord.setSuccessCount(successCount);
		uploadRecord.setFailCount(failCount);
		uploadRecord.setStatus(Constants.STATUS_ACTIVE);
		
		if (!failList.isEmpty()) {
			resultJson = util.exportExcel(failList, "批量消费");
			uploadRecord.setFailName(resultJson.get("msg").toString());
		} else {
			resultJson = AjaxResult.success();
		}
		uploadRecordMapper.insertUploadRecord(uploadRecord);
		return resultJson;
	}
	
}
