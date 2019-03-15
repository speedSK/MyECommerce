package com.ruoyi.project.business.goodCategory.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.framework.web.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.goodCategory.mapper.GoodCategoryMapper;
import com.ruoyi.project.business.goodCategory.domain.GoodCategory;
import com.ruoyi.common.support.Convert;

/**
 * 商品分类 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-07
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
		goodCategory.setStatus(Constants.STATUS_ACTIVE);
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
		GoodCategory info = goodCategoryMapper.selectGoodCategoryById(goodCategory.getParentId());
		if (!UserConstants.NORMAL.equals(info.getStatus())) {
			throw new BusinessException("分类停用，不允许新增");
		}
        goodCategory.setCreateBy(ShiroUtils.getLoginName());
		goodCategory.setAncestors(info.getAncestors() + "," + goodCategory.getParentId());
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
		GoodCategory info = goodCategoryMapper.selectGoodCategoryById(goodCategory.getParentId());
		if (StringUtils.isNotNull(info)) {
			String ancestors = info.getAncestors() + "," + info.getId();
			goodCategory.setAncestors(ancestors);
			updateChildren(info.getId(), ancestors);
		}
		goodCategory.setUpdateBy(ShiroUtils.getLoginName());
		int result = goodCategoryMapper.updateGoodCategory(goodCategory);
		if (UserConstants.NORMAL.equals(goodCategory.getStatus())) {
			//修改所有父级分类
			goodCategoryMapper.updateGoodCategoryStatus(goodCategory);
		}
		return result;
	}

	/**
	 * 修改子元素关系
	 *
	 * @param goodCategoryId 分类ID
	 * @param ancestors 元素列表
	 */
	public void updateChildren(Long goodCategoryId, String ancestors)
	{
		GoodCategory category = new GoodCategory();
		category.setParentId(goodCategoryId);
		List<GoodCategory> childrens = goodCategoryMapper.selectGoodCategoryList(category);
		for (GoodCategory children : childrens)
		{
			children.setAncestors(ancestors + "," + category.getParentId());
		}
		if (childrens.size() > 0)
		{
			goodCategoryMapper.updateChildren(childrens);
		}
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
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            GoodCategory goodCategory = new GoodCategory();
            goodCategory.setId(Long.valueOf(id));
            //初始化數據信息

            goodCategory.setStatus(Constants.STATUS_REMOVED);

            goodCategory.setUpdateBy(ShiroUtils.getLoginName());

            goodCategoryMapper.updateGoodCategory(goodCategory);

        }

        return 1;
	}




	@Override
	public String checkGoodCategoryNameUnique(GoodCategory goodCategory) {
		Long goodCategoryId = StringUtils.isNull(goodCategory.getId()) ? -1L : goodCategory.getId();
		GoodCategory info = goodCategoryMapper.checkGoodCategoryNameUnique(goodCategory.getCategoryName(),goodCategory.getParentId());
		if (StringUtils.isNotNull(info) && info.getId().longValue() != goodCategoryId.longValue())
		{
			return UserConstants.MENU_NAME_NOT_UNIQUE;
		}
		return UserConstants.MENU_NAME_UNIQUE;
	}

	@Override
	public int selectGoodCategoryCount(Long parentId) {
		GoodCategory goodCategory = new GoodCategory();
		goodCategory.setParentId(parentId);
		return goodCategoryMapper.selectGoodCategoryCount(goodCategory);
	}

	@Override
	public int deleteGoodCategoryById(Long id) {
		GoodCategory goodCategory = new GoodCategory ();
		goodCategory.setId(id);
		goodCategory.setStatus(Constants.STATUS_REMOVED);
		goodCategory.setUpdateBy(ShiroUtils.getLoginName());
		return goodCategoryMapper.updateGoodCategory(goodCategory);
	}

	@Override
	public boolean checkDeptExistGoods(Long goodCategoryId) {
		int result = goodCategoryMapper.checkDeptExistGoods(goodCategoryId);
		return result > 0 ? true : false;
	}

	@Override
	public List<Ztree> selectGoodCategoryTree(GoodCategory goodCategory) {
		List<GoodCategory> goodCategoryList = goodCategoryMapper.selectGoodCategoryList(goodCategory);
		List<Ztree> ztrees = initZtree(goodCategoryList);
		return ztrees;
	}

	/**
	 * 对象转分类树
	 *
	 * @param goodCategoryList 分类列表
	 * @return 树结构列表
	 */
	public List<Ztree> initZtree(List<GoodCategory> goodCategoryList)
	{
		List<Ztree> ztrees = new ArrayList<Ztree>();
		for (GoodCategory goodCategory : goodCategoryList)
		{
			if (UserConstants.NORMAL.equals(goodCategory.getStatus()))
			{
				Ztree ztree = new Ztree();
				ztree.setId(goodCategory.getId());
				ztree.setpId(goodCategory.getParentId());
				ztree.setName(goodCategory.getCategoryName());
				ztree.setTitle(goodCategory.getCategoryName());
				ztrees.add(ztree);
			}
		}
		return ztrees;
	}


	/**
	 * 对象转菜单树
	 *
	 * @param goodCategoryList 菜单列表
	 * @return
	 */
	public List<Map<String, Object>> getTrees(List<GoodCategory> goodCategoryList)
	{
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (GoodCategory goodCategory : goodCategoryList)
		{
			Map<String, Object> deptMap = new HashMap<String, Object>();
			deptMap.put("id", goodCategory.getId());
			deptMap.put("pId", goodCategory.getParentId());
			deptMap.put("title", goodCategory.getCategoryName());
			deptMap.put("name", goodCategory.getCategoryName());

			trees.add(deptMap);
		}
		return trees;
	}


}
