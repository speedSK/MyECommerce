package com.ruoyi.project.business.goods.mapper;

import com.ruoyi.project.business.goods.domain.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 商品 数据层
 * 
 * @author LiuNing
 * @date 2018-08-08
 */
@Repository
public interface GoodsMapper 
{
	/**
     * 查询商品信息
     * 
     * @param id 商品ID
     * @return 商品信息
     */
	public Goods selectGoodsById(Long id);
	
	/**
	 * 根据Map参数获取商品列表
	 * @param param
	 * @return
	 */
	List<Goods> findGoodsListForMap(Map<String, Object> param);

	/**
     * 查询商品列表
     * 
     * @param goods 商品信息
     * @return 商品集合
     */
	public List<Goods> selectGoodsList(Goods goods);
	
	/**
     * 新增商品
     * 
     * @param goods 商品信息
     * @return 结果
     */
	public int insertGoods(Goods goods);
	
	/**
     * 修改商品
     * 
     * @param goods 商品信息
     * @return 结果
     */
	public int updateGoods(Goods goods);
	
	/**
     * 删除商品
     * 
     * @param id 商品ID
     * @return 结果
     */
	public int deleteGoodsById(Long id);
	
	/**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodsByIds(String[] ids);

	/**
	 * 查询商品信息
	 *
	 * @param goodCode 商品编号
	 * @return 商品信息
	 */
	public Goods selectGoodsByCode(String goodCode);
	
}