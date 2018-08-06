package com.information.project.business.goods.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.information.project.business.goods.mapper.GoodsMapper;
import com.information.project.business.goods.domain.Goods;
import com.information.project.business.goods.service.IGoodsService;
import com.information.common.support.Convert;

/**
 * 商品 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class GoodsServiceImpl implements IGoodsService 
{
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
	    return goodsMapper.updateGoods(goods);
	}

	/**
     * 删除商品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGoodsByIds(String ids)
	{
		return goodsMapper.deleteGoodsByIds(Convert.toStrArray(ids));
	}
	
}
