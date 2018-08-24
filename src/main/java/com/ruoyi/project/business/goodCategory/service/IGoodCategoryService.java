package com.ruoyi.project.business.goodCategory.service;

import com.ruoyi.project.business.goodCategory.domain.GoodCategory;
import java.util.List;
import java.util.Map;

/**
 * 商品分类 服务层
 * 
 * @author LiuNing
 * @date 2018-08-07
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



	/**
	 * 校验分類名称是否唯一
	 *
	 * @param goodCategory 分類信息
	 * @return 结果
	 */
	public String checkGoodCategoryNameUnique(GoodCategory goodCategory);


	/**
	 * 查询所有分類信息
	 *
	 * @return 分類列表
	 */
	public List<Map<String,Object>> goodCategoryTreeData();

	/**
	 * 查询分類数量
	 *
	 * @param parentId 分類父ID
	 * @return 结果
	 */
	public int  selectCountGoodCategoryByParentId(Long parentId);
	
	/**
	 * 删除分類管理信息
	 *
	 * @param id 分類ID
	 * @return 结果
	 */
	public int deleteGoodCategoryById(Long id);

}
