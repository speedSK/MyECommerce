package com.ruoyi.project.business.goodCategory.mapper;

import com.ruoyi.project.business.goodCategory.domain.GoodCategory;
import java.util.List;	

/**
 * 商品分类 数据层
 * 
 * @author LiuNing
 * @date 2018-08-07
 */
public interface GoodCategoryMapper 
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
     * 删除商品分类
     * 
     * @param id 商品分类ID
     * @return 结果
     */
	public int deleteGoodCategoryById(Long id);
	
	/**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodCategoryByIds(String[] ids);

	/**
	 * 查询分类数量
	 *
	 * @param parentId 分类父ID
	 * @return 结果
	 */
	public int selectCountGoodCategoryByParentId(Long parentId);

	/**
	 * 查询系统所有分类
	 *
	 * @return 分类列表
	 */
	public List<GoodCategory> selectGoodCategoryAll();

	/**
	 * 校验分类名称是否唯一
	 *
	 * @param categoryName 分类名称
	 * @return 结果
	 */
	public GoodCategory checkGoodCategoryNameUnique(String categoryName);
}