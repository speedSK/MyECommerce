package com.ruoyi.project.business.merchantReport.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.security.ShiroUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.merchantReport.mapper.MerchantReportMapper;
import com.ruoyi.project.business.merchantReport.domain.MerchantReport;
import com.ruoyi.common.support.Convert;

/**
 * 系统账户报 服务层实现
 *
 * @author LiuNing
 * @date 2018-08-15
 */
@Service
public class MerchantReportServiceImpl implements IMerchantReportService
{
	@Autowired
	private MerchantReportMapper merchantReportMapper;

	/**
     * 查询系统账户报信息
     *
     * @param id 系统账户报ID
     * @return 系统账户报信息
     */
    @Override
	public MerchantReport selectMerchantReportById(Long id)
	{
	    return merchantReportMapper.selectMerchantReportById(id);
	}

	/**
     * 查询系统账户报列表
     *
     * @param MerchantReport 系统账户报信息
     * @return 系统账户报集合
     */
	@Override
	public List<MerchantReport> selectMerchantReportList(MerchantReport MerchantReport)
	{
	    MerchantReport.setStatus(Constants.STATUS_ACTIVE);
	    return merchantReportMapper.selectMerchantReportList(MerchantReport);
	}

    /**
     * 新增系统账户报
     *
     * @param MerchantReport 系统账户报信息
     * @return 结果
     */
	@Override
	public int insertMerchantReport(MerchantReport MerchantReport)
	{
	    MerchantReport.setStatus(Constants.STATUS_ACTIVE);

        MerchantReport.setCreateBy(ShiroUtils.getLoginName());
	    return merchantReportMapper.insertMerchantReport(MerchantReport);
	}

	/**
     * 修改系统账户报
     *
     * @param MerchantReport 系统账户报信息
     * @return 结果
     */
	@Override
	public int updateMerchantReport(MerchantReport MerchantReport)
	{
	    MerchantReport.setUpdateBy(ShiroUtils.getLoginName());
	    return merchantReportMapper.updateMerchantReport(MerchantReport);
	}

	/**
     * 删除系统账户报对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMerchantReportByIds(String ids)
	{
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            MerchantReport MerchantReport = new MerchantReport();
            MerchantReport.setId(Long.valueOf(id));
            //初始化數據信息

            MerchantReport.setStatus(Constants.STATUS_REMOVED);

            MerchantReport.setUpdateBy(ShiroUtils.getLoginName());

            merchantReportMapper.updateMerchantReport(MerchantReport);

        }

        return 1;
	}
	
}
