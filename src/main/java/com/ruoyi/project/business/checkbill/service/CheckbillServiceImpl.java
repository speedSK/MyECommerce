package com.ruoyi.project.business.checkbill.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.checkbill.mapper.CheckbillMapper;
import com.ruoyi.project.business.checkbill.domain.Checkbill;
import com.ruoyi.project.business.checkbill.service.ICheckbillService;
import com.ruoyi.common.support.Convert;

/**
 * 银行总账对账 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class CheckbillServiceImpl implements ICheckbillService 
{
	@Autowired
	private CheckbillMapper checkbillMapper;

	/**
     * 查询银行总账对账信息
     * 
     * @param id 银行总账对账ID
     * @return 银行总账对账信息
     */
    @Override
	public Checkbill selectCheckbillById(Long id)
	{
	    return checkbillMapper.selectCheckbillById(id);
	}
	
	/**
     * 查询银行总账对账列表
     * 
     * @param checkbill 银行总账对账信息
     * @return 银行总账对账集合
     */
	@Override
	public List<Checkbill> selectCheckbillList(Checkbill checkbill)
	{
	    return checkbillMapper.selectCheckbillList(checkbill);
	}
	
    /**
     * 新增银行总账对账
     * 
     * @param checkbill 银行总账对账信息
     * @return 结果
     */
	@Override
	public int insertCheckbill(Checkbill checkbill)
	{
	    return checkbillMapper.insertCheckbill(checkbill);
	}
	
	/**
     * 修改银行总账对账
     * 
     * @param checkbill 银行总账对账信息
     * @return 结果
     */
	@Override
	public int updateCheckbill(Checkbill checkbill)
	{
	    return checkbillMapper.updateCheckbill(checkbill);
	}

	/**
     * 删除银行总账对账对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCheckbillByIds(String ids)
	{
		return checkbillMapper.deleteCheckbillByIds(Convert.toStrArray(ids));
	}
	
}
