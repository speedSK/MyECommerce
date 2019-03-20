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
     * @param merchantReport 系统账户报信息
     * @return 系统账户报集合
     */
	@Override
	public List<MerchantReport> selectMerchantReportList(MerchantReport merchantReport)
	{
	    merchantReport.setStatus(Constants.STATUS_ACTIVE);
	    return merchantReportMapper.selectMerchantReportList(merchantReport);
	}

    /**
     * 新增系统账户报
     *
     * @param merchantReport 系统账户报信息
     * @return 结果
     */
	@Override
	public int insertMerchantReport(MerchantReport merchantReport)
	{
	    merchantReport.setStatus(Constants.STATUS_ACTIVE);
	    return merchantReportMapper.insertMerchantReport(merchantReport);
	}

	/**
     * 修改系统账户报
     *
     * @param merchantReport 系统账户报信息
     * @return 结果
     */
	@Override
	public int updateMerchantReport(MerchantReport merchantReport)
	{
	    return merchantReportMapper.updateMerchantReport(merchantReport);
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

            MerchantReport merchantReport = new MerchantReport();
            merchantReport.setId(Long.valueOf(id));
            //初始化數據信息

            merchantReport.setStatus(Constants.STATUS_REMOVED);

            merchantReport.setUpdateBy(ShiroUtils.getLoginName());

            merchantReportMapper.updateMerchantReport(merchantReport);

        }

        return 1;
	}

	@Override
	public List<MerchantReport> selectOut(String params) {
		return merchantReportMapper.selectOuting(params);
	}

	@Override
	public List<MerchantReport> selectInComing(String params) {
		return merchantReportMapper.selectInComing(params);
	}

}
