package com.information.project.business.operReport.service;

import com.information.common.constant.Constants;
import com.information.common.utils.security.ShiroUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.operReport.mapper.OperReportMapper;
import com.information.project.business.operReport.domain.OperReport;
import com.information.project.business.operReport.service.IOperReportService;
import com.information.common.support.Convert;

/**
 * 系统操作报 服务层实现
 *
 * @author LiuNing
 * @date 2018-08-15
 */
@Service
public class OperReportServiceImpl implements IOperReportService
{
	@Autowired
	private OperReportMapper operReportMapper;

	/**
     * 查询系统操作报信息
     *
     * @param id 系统操作报ID
     * @return 系统操作报信息
     */
    @Override
	public OperReport selectOperReportById(Long id)
	{
	    return operReportMapper.selectOperReportById(id);
	}

	/**
     * 查询系统操作报列表
     *
     * @param operReport 系统操作报信息
     * @return 系统操作报集合
     */
	@Override
	public List<OperReport> selectOperReportList(OperReport operReport)
	{
	    operReport.setStatus(Constants.STATUS_ACTIVE);
	    return operReportMapper.selectOperReportList(operReport);
	}

    /**
     * 新增系统操作报
     *
     * @param operReport 系统操作报信息
     * @return 结果
     */
	@Override
	public int insertOperReport(OperReport operReport)
	{
	    operReport.setStatus(Constants.STATUS_ACTIVE);

        operReport.setCreateBy(ShiroUtils.getUserId().toString());
	    return operReportMapper.insertOperReport(operReport);
	}

	/**
     * 修改系统操作报
     *
     * @param operReport 系统操作报信息
     * @return 结果
     */
	@Override
	public int updateOperReport(OperReport operReport)
	{
	    operReport.setUpdateBy(ShiroUtils.getUserId().toString());
	    return operReportMapper.updateOperReport(operReport);
	}

	/**
     * 删除系统操作报对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOperReportByIds(String ids)
	{
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            OperReport operReport = new OperReport();
            operReport.setId(Long.valueOf(id));
            //初始化數據信息

            operReport.setStatus(Constants.STATUS_REMOVED);

            operReport.setUpdateBy(ShiroUtils.getUserId().toString());

            operReportMapper.updateOperReport(operReport);

        }

        return 1;
	}
	
}
