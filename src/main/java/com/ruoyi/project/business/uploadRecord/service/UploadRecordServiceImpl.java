package com.ruoyi.project.business.uploadRecord.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.domain.AjaxResult;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public int insertUploadRecord(UploadRecord uploadRecord)
	{
	    uploadRecord.setStatus(Constants.STATUS_ACTIVE);

        uploadRecord.setCreateBy(ShiroUtils.getUserId().toString());
	    return uploadRecordMapper.insertUploadRecord(uploadRecord);
	}
	
	/**
     * 修改功能导入记录
     * 
     * @param uploadRecord 功能导入记录信息
     * @return 结果
     */
	@Override
	public int updateUploadRecord(UploadRecord uploadRecord)
	{
	    uploadRecord.setUpdateBy(ShiroUtils.getUserId().toString());
	    return uploadRecordMapper.updateUploadRecord(uploadRecord);
	}

	/**
     * 删除功能导入记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUploadRecordByIds(String ids)
	{
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            UploadRecord uploadRecord = new UploadRecord();
            uploadRecord.setId(Long.valueOf(id));
            //初始化數據信息

            uploadRecord.setStatus(Constants.STATUS_REMOVED);

            uploadRecord.setUpdateBy(ShiroUtils.getUserId().toString());

            uploadRecordMapper.updateUploadRecord(uploadRecord);

        }

        return 1;
	}

	@Override
	public AjaxResult saveRecharge(MultipartFile file) throws IOException, Exception {
		AjaxResult resultJson = null;
		ExcelUtil<BatchRechargeVo> util = new ExcelUtil<BatchRechargeVo>(BatchRechargeVo.class);
    	List<BatchRechargeVo> list = util.importExcel("批量充值", file.getInputStream());
		List<BatchRechargeVo> failList = new ArrayList<>();
		UploadRecord uploadRecord = new UploadRecord();
		uploadRecord.setModule("批量充值导入");
		uploadRecord.setUploadName(file.getOriginalFilename());
		long successCount = 0;
		long failCount = 0 ;
		for (BatchRechargeVo batchRechargeVo : list) {
			if (batchRechargeVo.getNumber()!=null&&batchRechargeVo.getAmount()!=null) {
				Person person = new Person();
				person.setNumber(batchRechargeVo.getNumber());
				person.setStatus(Constants.STATUS_ACTIVE);
				List<Person> pList = personMapper.selectPersonList(person);
				if (pList==null||pList.size()==0) {
					failCount++;
					batchRechargeVo.setFailure("用户不存在");
					failList.add(batchRechargeVo);
				} else {
					successCount++;
					person = pList.get(0);
					person.setBalance(person.getBalance().add(new BigDecimal(batchRechargeVo.getAmount())));
					//TODO 计算校验字段
					
					personMapper.updatePerson(person);
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
	public AjaxResult saveCost(MultipartFile file) throws IOException, Exception {
		AjaxResult resultJson = null;
		ExcelUtil<BatchRechargeVo> util = new ExcelUtil<BatchRechargeVo>(BatchRechargeVo.class);
    	List<BatchRechargeVo> list = util.importExcel("批量消费", file.getInputStream());
		List<BatchRechargeVo> failList = new ArrayList<>();
		UploadRecord uploadRecord = new UploadRecord();
		uploadRecord.setModule("批量消费导入");
		uploadRecord.setUploadName(file.getOriginalFilename());
		long successCount = 0;
		long failCount = 0 ;
		for (BatchRechargeVo batchRechargeVo : list) {
			if (batchRechargeVo.getNumber()!=null&&batchRechargeVo.getAmount()!=null) {
				Person person = new Person();
				person.setNumber(batchRechargeVo.getNumber());
				person.setStatus(Constants.STATUS_ACTIVE);
				List<Person> pList = personMapper.selectPersonList(person);
				if (pList==null||pList.size()==0) {
					failCount++;
					batchRechargeVo.setFailure("用户不存在");
					failList.add(batchRechargeVo);
				} else {
					successCount++;
					person = pList.get(0);
					person.setBalance(person.getBalance().subtract(new BigDecimal(batchRechargeVo.getAmount())));
					//TODO 计算校验字段
					
					personMapper.updatePerson(person);
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
			resultJson = util.exportExcel(failList, "批量消费");
			uploadRecord.setFailName(resultJson.get("msg").toString());
		} else {
			resultJson = AjaxResult.success();
		}
		uploadRecordMapper.insertUploadRecord(uploadRecord);
		return resultJson;
	}
	
}
