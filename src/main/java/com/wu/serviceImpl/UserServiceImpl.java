package com.wu.serviceImpl;/*
 * Created by Virok on 2022/5/31
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wu.entity.PageResult;
import com.wu.mapper.UserMapper;
import com.wu.pojo.User;
import com.wu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User login(User user) {
        return userMapper.getUser(user);
    }

    @Override
    public boolean existUserAccount(User user) {
        User u = userMapper.selectByUserAccount(user.getUserAccount());
        return u != null? true:false;
    }

    @Override
    public boolean existEmail(User user) {
        User u = userMapper.selectByEmail(user.getUserEmail());
        return u != null? true:false;
    }

    public Integer addUser(User user){
        user.setUserRole(1);
        return userMapper.addUser(user);
    }

    @Override
    public boolean register(User user) {
        user.setUserRole(1);
        return userMapper.register(user);
    }



    @Override
    public PageResult selectUser(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<User> page = userMapper.selectUser(user);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Integer editUser(User user) {
        return userMapper.editUser(user);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

}

