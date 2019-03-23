package com.ruoyi.project.business.goods.service;

import com.ruoyi.project.business.goods.domain.Goods;
import java.util.List;
import java.util.Map;

/**
 * 商品 服务层
 * 
 * @author LiuNing
 * @date 2018-08-08
 */
public interface IGoodsService 
{
	/**
     * 查询商品信息
     * 
     * @param id 商品ID
     * @return 商品信息
     */
	public Goods selectGoodsById(Long id);
	
	/**
     * 查询商品列表
     * 
     * @param goods 商品信息
     * @return 商品集合
     */
	public List<Goods> selectGoodsList(Goods goods);
	
	/**
	 * 根据Map查询商品列表
	 * @param map
	 * @return
	 */
	public List<Goods> findGoodsListForMap(Map<String,Object> map);
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
     * 删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodsByIds(String ids);

	String importGoods(List<Goods> goodsList, boolean updateSupport);

    String checkCodeUnique(String code);
}
