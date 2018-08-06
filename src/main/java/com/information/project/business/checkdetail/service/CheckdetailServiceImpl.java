package com.information.project.business.checkdetail.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.checkdetail.mapper.CheckdetailMapper;
import com.information.project.business.checkdetail.domain.Checkdetail;
import com.information.project.business.checkdetail.service.ICheckdetailService;
import com.information.common.support.Convert;

/**
 * 银行详情对账对账 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class CheckdetailServiceImpl implements ICheckdetailService 
{
	@Autowired
	private CheckdetailMapper checkdetailMapper;

	/**
     * 查询银行详情对账对账信息
     * 
     * @param id 银行详情对账对账ID
     * @return 银行详情对账对账信息
     */
    @Override
	public Checkdetail selectCheckdetailById(Long id)
	{
	    return checkdetailMapper.selectCheckdetailById(id);
	}
	
	/**
     * 查询银行详情对账对账列表
     * 
     * @param checkdetail 银行详情对账对账信息
     * @return 银行详情对账对账集合
     */
	@Override
	public List<Checkdetail> selectCheckdetailList(Checkdetail checkdetail)
	{
	    return checkdetailMapper.selectCheckdetailList(checkdetail);
	}
	
    /**
     * 新增银行详情对账对账
     * 
     * @param checkdetail 银行详情对账对账信息
     * @return 结果
     */
	@Override
	public int insertCheckdetail(Checkdetail checkdetail)
	{
	    return checkdetailMapper.insertCheckdetail(checkdetail);
	}
	
	/**
     * 修改银行详情对账对账
     * 
     * @param checkdetail 银行详情对账对账信息
     * @return 结果
     */
	@Override
	public int updateCheckdetail(Checkdetail checkdetail)
	{
	    return checkdetailMapper.updateCheckdetail(checkdetail);
	}

	/**
     * 删除银行详情对账对账对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCheckdetailByIds(String ids)
	{
		return checkdetailMapper.deleteCheckdetailByIds(Convert.toStrArray(ids));
	}
	
}
