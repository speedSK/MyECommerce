package com.information.project.business.goodCategory.service;

import com.information.common.constant.Constants;
import com.information.common.constant.UserConstants;
import com.information.common.utils.StringUtils;
import com.information.common.utils.security.ShiroUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.goodCategory.mapper.GoodCategoryMapper;
import com.information.project.business.goodCategory.domain.GoodCategory;
import com.information.common.support.Convert;

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
	    goodCategory.setStatus(Constants.STATUS_ACTIVE);

        goodCategory.setCreateBy(ShiroUtils.getUserId().toString());
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
	    goodCategory.setUpdateBy(ShiroUtils.getUserId().toString());
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
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            GoodCategory goodCategory = new GoodCategory();
            goodCategory.setId(Long.valueOf(id));
            //初始化數據信息

            goodCategory.setStatus(Constants.STATUS_REMOVED);

            goodCategory.setUpdateBy(ShiroUtils.getUserId().toString());

            goodCategoryMapper.updateGoodCategory(goodCategory);

        }

        return 1;
	}




	@Override
	public String checkGoodCategoryNameUnique(GoodCategory goodCategory) {
		Long goodCategoryId = StringUtils.isNull(goodCategory.getId()) ? -1L : goodCategory.getId();
		GoodCategory info = goodCategoryMapper.checkGoodCategoryNameUnique(goodCategory.getCategoryName());
		if (StringUtils.isNotNull(info) && info.getId().longValue() != goodCategoryId.longValue())
		{
			return UserConstants.MENU_NAME_NOT_UNIQUE;
		}
		return UserConstants.MENU_NAME_UNIQUE;
	}

	@Override
	public List<Map<String, Object>> goodCategoryTreeData() {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<GoodCategory> goodCategoryList = goodCategoryMapper.selectGoodCategoryAll();
		trees = getTrees(goodCategoryList);
		return trees;
	}

	@Override
	public int selectCountGoodCategoryByParentId(Long parentId) {
		return goodCategoryMapper.selectCountGoodCategoryByParentId(parentId);
	}

	@Override
	public int deleteGoodCategoryById(Long id) {
		return goodCategoryMapper.deleteGoodCategoryById(id);
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

			trees.add(deptMap);
		}
		return trees;
	}

}
