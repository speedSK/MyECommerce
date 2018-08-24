package com.ruoyi.project.business.goods.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.security.ShiroUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.goods.mapper.GoodsMapper;
import com.ruoyi.project.business.goods.domain.Goods;
import com.ruoyi.project.business.goods.service.IGoodsService;
import com.ruoyi.common.support.Convert;

/**
 * 商品 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-08
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
	    goods.setStatus(Constants.STATUS_ACTIVE);

        goods.setCreateBy(ShiroUtils.getUserId().toString());
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
	    goods.setUpdateBy(ShiroUtils.getUserId().toString());
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
	    String [] idsArray = Convert.toStrArray(ids);
        for (String id: idsArray) {

            Goods goods = new Goods();
            goods.setId(Long.valueOf(id));
            //初始化數據信息

            goods.setStatus(Constants.STATUS_REMOVED);

            goods.setUpdateBy(ShiroUtils.getUserId().toString());

            goodsMapper.updateGoods(goods);

        }

        return 1;
	}
	
}
