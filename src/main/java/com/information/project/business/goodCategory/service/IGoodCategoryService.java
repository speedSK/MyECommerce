package com.information.project.business.goodCategory.service;

import com.information.project.business.goodCategory.domain.GoodCategory;
import java.util.List;

/**
 * 商品分类 服务层
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
public interface IGoodCategoryService 
{
	/**
     * 查询商品分类信息
     * 
     * @param id 商品分类ID
     * @return 商品分类信息
     */
	public GoodCategory selectGoodCategoryById(Long id);
	
	/**
     * 查询商品分类列表
     * 
     * @param goodCategory 商品分类信息
     * @return 商品分类集合
     */
	public List<GoodCategory> selectGoodCategoryList(GoodCategory goodCategory);
	
	/**
     * 新增商品分类
     * 
     * @param goodCategory 商品分类信息
     * @return 结果
     */
	public int insertGoodCategory(GoodCategory goodCategory);
	
	/**
     * 修改商品分类
     * 
     * @param goodCategory 商品分类信息
     * @return 结果
     */
	public int updateGoodCategory(GoodCategory goodCategory);
		
	/**
     * 删除商品分类信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodCategoryByIds(String ids);
	
}
