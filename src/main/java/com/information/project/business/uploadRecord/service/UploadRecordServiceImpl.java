package com.information.project.business.uploadRecord.service;

import com.information.common.constant.Constants;
import com.information.common.utils.security.ShiroUtils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.uploadRecord.mapper.UploadRecordMapper;
import com.information.project.business.person.domain.Person;
import com.information.project.business.person.mapper.PersonMapper;
import com.information.project.business.uploadRecord.domain.BatchRechargeVo;
import com.information.project.business.uploadRecord.domain.UploadRecord;
import com.information.project.business.uploadRecord.service.IUploadRecordService;
import com.information.common.support.Convert;

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
	public List<BatchRechargeVo> saveRecharge(List<BatchRechargeVo> list) {
		List<BatchRechargeVo> failList = new ArrayList<>();
		for (BatchRechargeVo batchRechargeVo : list) {
			if (batchRechargeVo.getNumber()!=null||batchRechargeVo.getAmount()!=null) {
				Person person = new Person();
				person.setNumber(batchRechargeVo.getNumber());
				person.setStatus(Constants.STATUS_ACTIVE);
				List<Person> pList = personMapper.selectPersonList(person);
				if (pList==null||pList.size()==0) {
					failList.add(batchRechargeVo);
				} else {
					person.setBalance(person.getBalance().add(batchRechargeVo.getAmount()));
					//TODO 计算校验字段
					
					personMapper.updatePerson(person);
				}
			}
		}
		return failList;
	}
	
}
