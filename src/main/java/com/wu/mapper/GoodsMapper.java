package com.wu.mapper;


import com.github.pagehelper.Page;
import com.wu.pojo.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
   public Goods findGoodsByCode(String goodsCode);
          Goods findGoodsById(Integer goodsId);
   public Integer deleteGoods(Integer goodsId);

   public Integer addGoods(Goods goods);

   public Integer updateGoods(Goods goods);

   public List<Goods> findGoodsByName(String goodsName);

//   public List<Goods> findGoodsByPrice(Price price);

   public Page<Map<String,Object>> findGoods(Map<String,Object> map);

//    int deleteGood(Integer goodsId);
}