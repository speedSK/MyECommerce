package com.information.project.monitor.job.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.information.project.business.transactionRecord.domain.TransactionRecord;
import com.information.project.business.transactionRecord.service.ITransactionRecordService;

/**
 * 定时任务调度测试
 * 
 * @author LiuNing
 */
@Component("systemTask")
public class SystemTask
{
	@Autowired
	private ITransactionRecordService transactionRecordService;
	
    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
    	List<TransactionRecord> list = transactionRecordService.selectTransactionRecordList(new TransactionRecord());
    	System.out.println(list.size());
    }

}
