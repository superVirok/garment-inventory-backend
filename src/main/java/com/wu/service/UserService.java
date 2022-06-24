package com.wu.service;/*
 * Created by Virok on 2022/5/31
 */

import com.wu.entity.PageResult;
import com.wu.pojo.User;

public interface UserService {

    public User login(User user);

    public boolean existUserAccount(User user);

    public boolean existEmail(User user);

    public boolean register(User user);

    public Integer addUser(User user);

    public PageResult selectUser(User user, Integer pageNum, Integer pageSize);

    public Integer editUser(User user);

    public User findById(Integer id);

    public Integer deleteUser(Integer id);
}
