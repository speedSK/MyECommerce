package com.information.project.business.goodCategory.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.goodCategory.mapper.GoodCategoryMapper;
import com.information.project.business.goodCategory.domain.GoodCategory;
import com.information.project.business.goodCategory.service.IGoodCategoryService;
import com.information.common.support.Convert;

/**
 * 商品分类 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class GoodCategoryServiceImpl implements IGoodCategoryService 
{
	@Autowired
	private GoodCategoryMapper goodCategoryMapper;

	/**
     * 查询商品分类信息
     * 
     * @param id 商品分类ID
     * @return 商品分类信息
     */
    @Override
	public GoodCategory selectGoodCategoryById(Long id)
	{
	    return goodCategoryMapper.selectGoodCategoryById(id);
	}
	
	/**
     * 查询商品分类列表
     * 
     * @param goodCategory 商品分类信息
     * @return 商品分类集合
     */
	@Override
	public List<GoodCategory> selectGoodCategoryList(GoodCategory goodCategory)
	{
	    return goodCategoryMapper.selectGoodCategoryList(goodCategory);
	}
	
    /**
     * 新增商品分类
     * 
     * @param goodCategory 商品分类信息
     * @return 结果
     */
	@Override
	public int insertGoodCategory(GoodCategory goodCategory)
	{
	    return goodCategoryMapper.insertGoodCategory(goodCategory);
	}
	
	/**
     * 修改商品分类
     * 
     * @param goodCategory 商品分类信息
     * @return 结果
     */
	@Override
	public int updateGoodCategory(GoodCategory goodCategory)
	{
	    return goodCategoryMapper.updateGoodCategory(goodCategory);
	}

	/**
     * 删除商品分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGoodCategoryByIds(String ids)
	{
		return goodCategoryMapper.deleteGoodCategoryByIds(Convert.toStrArray(ids));
	}
	
}
