package com.wu.service;

import com.wu.entity.PageResult;
import com.wu.pojo.Goods;

import java.util.Map;

public interface  GoodsService {
     Goods findGoodsByCode(String goodsCode);

     Goods findGoodsById(Integer goodsId);

     Integer deleteGoods(Integer goodsId);

     Integer updateGoods(Goods goods);

     Integer addGoods(Goods goods);


     PageResult selectGoods(Map<String,Object> map, Integer pageNum, Integer pageSize);
}