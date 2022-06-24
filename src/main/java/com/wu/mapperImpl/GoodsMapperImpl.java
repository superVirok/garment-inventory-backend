package com.wu.mapperImpl;

import com.github.pagehelper.Page;
import com.wu.mapper.GoodsMapper;
import com.wu.pojo.Goods;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class GoodsMapperImpl  implements GoodsMapper {
    @Autowired
    private SqlSessionTemplate sqlSession;
    public void setSqlSession(SqlSessionTemplate sqlSession){
        this.sqlSession=sqlSession;
    }

    @Override
    public Goods findGoodsByCode(String goodsCode) {
        return sqlSession.getMapper(GoodsMapper.class).findGoodsByCode(goodsCode);
    }

    @Override
    public Goods findGoodsById(Integer goodsId) {
        return sqlSession.getMapper(GoodsMapper.class).findGoodsById(goodsId);
    }

    @Override
    public Integer deleteGoods(Integer goodsId) {
        return sqlSession.getMapper(GoodsMapper.class).deleteGoods(goodsId);
    }

    @Override
    public Integer addGoods(Goods goods) {
        return sqlSession.getMapper(GoodsMapper.class).addGoods(goods);
    }

    @Override
    public Integer updateGoods(Goods goods) {
        return sqlSession.getMapper(GoodsMapper.class).updateGoods(goods);
    }

    @Override
    public List<Goods> findGoodsByName(String goodsName) {
        return sqlSession.getMapper(GoodsMapper.class).findGoodsByName(goodsName);
    }

    @Override
    public Page<Map<String,Object>> findGoods(Map<String, Object> map) {
        return sqlSession.getMapper(GoodsMapper.class).findGoods(map);
    }
}
