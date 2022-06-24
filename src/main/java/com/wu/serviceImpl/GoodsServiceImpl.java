package com.wu.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wu.entity.PageResult;
import com.wu.mapper.GoodsMapper;
import com.wu.mapper.TypeMapper;
import com.wu.pojo.Goods;
import com.wu.service.GoodsService;
import com.wu.service.TypeService;
import com.wu.untils.MapUntil;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("GoodsServiceImpl")
public class  GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private TypeService typeService;
    public Goods findGoodsByCode(String goodsCode){
        return goodsMapper.findGoodsByCode(goodsCode);
    }

    @Override
    public Goods findGoodsById(Integer goodsId) {
        return goodsMapper.findGoodsById(goodsId);
    }

    @Override
    public Integer deleteGoods(Integer goodsId){
        return goodsMapper.deleteGoods(goodsId);
    }

    @Override
    public Integer addGoods(Goods goods) {
        return goodsMapper.addGoods(goods);
    }

    @Override
    public Integer updateGoods(Goods goods){
        return goodsMapper.updateGoods(goods);
    }



//    @Override
//    public List<Goods> findGoodsByPrice(Price price) {
//        return goodsMapper.findGoodsByPrice(price);
//    }

    @Override
    public PageResult selectGoods(Map<String, Object> map,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
     //   Page<Map<String,Object>> page=new Page<Map<String,Object>>();
         //查得到
        Page<Map<String,Object>> page= goodsMapper.findGoods(map);

//        ArrayList<Map<String,Object>> list1=new ArrayList<>();
//        for(Goods goods : list){
//            System.out.println("yes");
//            Map<String,Object> map1=new HashMap<String,Object>();
//            map1.put("typeName",typeService.findTypeNameById(goods.getTypeId()));
//            System.out.println(typeService.findTypeNameById(goods.getTypeId()).getTypeName());
//            list1.add(MapUntil.transMap(goods,map1));
//           }
        return new PageResult(page.getTotal(),page.getResult());

    }



}