package com.wu.mapperImpl;

import com.github.pagehelper.Page;
import com.wu.mapper.AuthorizationMapper;
import com.wu.pojo.Authorization;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class AuthorizationMapperImpl implements AuthorizationMapper {
    @Autowired
    private SqlSessionTemplate sqlSession;
    public void setSqlSession(SqlSessionTemplate sqlSession){
        this.sqlSession=sqlSession;
    }


    @Override
    public Integer addAuthorization(Authorization authorization) {
        return sqlSession.getMapper(AuthorizationMapper.class).addAuthorization(authorization);
    }

    @Override
    public Authorization selectByAuthorizationId(Integer authenId) {
        return sqlSession.getMapper(AuthorizationMapper.class).selectByAuthorizationId(authenId);
    }

    @Override
    public List<Authorization> selectAuthorization(Map<String, Object> map) {
        return  sqlSession.getMapper(AuthorizationMapper.class).selectAuthorization(map);
    }

    @Override
    public Integer deleteAuthorization(Integer userId) {
        return sqlSession.getMapper(AuthorizationMapper.class).deleteAuthorization(userId);
    }

    @Override
    public Integer editAuthorization(Authorization authorization) {
        return sqlSession.getMapper(AuthorizationMapper.class).editAuthorization(authorization);
    }
}
