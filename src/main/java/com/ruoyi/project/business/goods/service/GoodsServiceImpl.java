package com.ruoyi.project.business.goods.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.goods.mapper.GoodsMapper;
import com.ruoyi.project.business.goods.domain.Goods;
import com.ruoyi.project.business.goods.service.IGoodsService;
import com.ruoyi.common.support.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-08
 */
@Service
public class GoodsServiceImpl implements IGoodsService 
{
	private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);
	@Autowired
	private GoodsMapper goodsMapper;

	/**
     * 查询商品信息
     * 
     * @param id 商品ID
     * @return 商品信息
     */
    @Override
	public Goods selectGoodsById(Long id)
	{
	    return goodsMapper.selectGoodsById(id);
	}
	
	/**
     * 查询商品列表
     * 
     * @param goods 商品信息
     * @return 商品集合
     */
	@Override
	public List<Goods> selectGoodsList(Goods goods)
	{
		goods.setStatus(Constants.STATUS_ACTIVE);
	    return goodsMapper.selectGoodsList(goods);
	}
	
    /**
     * 新增商品
     * 
     * @param goods 商品信息
     * @return 结果
     */
	@Override
	public int insertGoods(Goods goods)
	{
	    goods.setStatus(Constants.STATUS_ACTIVE);

        goods.setCreateBy(ShiroUtils.getLoginName());
	    return goodsMapper.insertGoods(goods);
	}
	
	/**
     * 修改商品
     * 
     * @param goods 商品信息
     * @return 结果
     */
	@Override
	public int updateGoods(Goods goods)
	{
	    goods.setUpdateBy(ShiroUtils.getLoginName());
	    return goodsMapper.updateGoods(goods);
	}

	/**
     * 删除商品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional
	public int deleteGoodsByIds(String ids)
	{
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {
			goodsMapper.deleteGoodsById(Long.valueOf(id));
        }
        return 1;
	}

	@Override
	public String importGoods(List<Goods> goodsList, boolean updateSupport) {
		if (StringUtils.isNull(goodsList) || goodsList.size() == 0)
		{
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		String operName = ShiroUtils.getLoginName();
		for (Goods goods : goodsList)
		{
			try
			{
				// 验证是否存在这个用户
				Goods g = goodsMapper.selectGoodsByCode(goods.getCode());
				if (StringUtils.isNull(g))
				{
					goods.setCreateBy(operName);
					this.insertGoods(goods);
					successNum++;
					successMsg.append("<br/>" + successNum + "、商品编号 " + goods.getCode() + " 导入成功");
				}
				else if (updateSupport)
				{
					goods.setUpdateBy(operName);
					this.updateGoods(goods);
					successNum++;
					successMsg.append("<br/>" + successNum + "、商品编号 " + goods.getCode() + " 更新成功");
				}
				else
				{
					failureNum++;
					failureMsg.append("<br/>" + failureNum + "、商品编号 " + goods.getCode() + " 已存在");
				}
			}
			catch (Exception e)
			{
				failureNum++;
				String msg = "<br/>" + failureNum + "、商品编号 " + goods.getCode() + " 导入失败：";
				failureMsg.append(msg + e.getMessage());
				logger.error(msg, e);
			}
		}
		if (failureNum > 0)
		{
			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new BusinessException(failureMsg.toString());
		}
		else
		{
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
		}
		return successMsg.toString();
	}

	@Override
	public List<Goods> findGoodsListForMap(Map<String, Object> param) {
		return goodsMapper.findGoodsListForMap(param);
	}

	@Override
	public String checkCodeUnique(String code) {
		Goods goods = goodsMapper.selectGoodsByCode(code);
		if (StringUtils.isNotNull(goods)) {

			return UserConstants.USER_NAME_NOT_UNIQUE;
		}
		return UserConstants.USER_NAME_UNIQUE;
	}

}
