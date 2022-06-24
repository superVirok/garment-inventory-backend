package com.wu.mapperImpl;/*
 * Created by Virok on 2022/6/20
 */

import com.github.pagehelper.Page;
import com.wu.mapper.TypeMapper;
import com.wu.pojo.Type;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class TypeMapperImpl implements TypeMapper {
    @Autowired
    SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Type findTypeNameById(Integer typeId) {
        return sqlSession.getMapper(TypeMapper.class).findTypeNameById(typeId);
    }

    @Override
    public Page<Type> selectAllType() {
        return sqlSession.getMapper(TypeMapper.class).selectAllType();
    }

    @Override
    public Page<Type> selectType(Type type) {
        return sqlSession.getMapper(TypeMapper.class).selectType(type);
    }

    @Override
    public Integer addType(Type type) {
        return sqlSession.getMapper(TypeMapper.class).addType(type);
    }

    @Override
    public Integer editType(Type type) {
        return sqlSession.getMapper(TypeMapper.class).editType(type);
    }

    @Override
    public Integer deleteType(Integer typeId) {
        return sqlSession.getMapper(TypeMapper.class).deleteType(typeId);
    }
}
