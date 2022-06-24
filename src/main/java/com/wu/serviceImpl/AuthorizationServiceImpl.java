package com.wu.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wu.entity.PageResult;
import com.wu.mapper.AuthorizationMapper;
import com.wu.mapper.UserMapper;
import com.wu.pojo.Authorization;
import com.wu.pojo.RecordItem;
import com.wu.pojo.User;
import com.wu.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("AuthorizationServiceImpl")
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    private AuthorizationMapper authorizationMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer addAuthorization(Authorization authorization) {
        return authorizationMapper.addAuthorization(authorization);
    }

    @Override
    public Authorization selectByAuthorizationId(Integer userId) {
        Authorization authorization= authorizationMapper.selectByAuthorizationId(userId);
        User user=userMapper.findById(authorization.getUserId());
        authorization.setUser(user);
        return authorization;
    }


    @Override
    public PageResult selectAuthorization(Map<String, Object> map,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Authorization> list=authorizationMapper.selectAuthorization(map);
        for(Authorization authorization:list){
            User user=userMapper.findById(authorization.getUserId());
            authorization.setUser( user);
        }
        Page<Authorization> page= (Page<Authorization>) list;
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Integer deleteAuthorization(Integer userId) {
        return authorizationMapper.deleteAuthorization(userId);
    }

    @Override
    public Integer editAuthorization(Authorization authorization) {
        return authorizationMapper.editAuthorization(authorization);
    }
}
