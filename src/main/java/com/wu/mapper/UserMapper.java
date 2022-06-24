package com.wu.mapper;/*
 * Created by Virok on 2022/5/31
 */

import com.github.pagehelper.Page;
import com.wu.pojo.User;

public interface UserMapper {
    public boolean register(User user);

    public Integer addUser(User user);

    public User selectByUserAccount(String userName);

    public User selectByEmail(String email);

    public User getUser(User user);

    public Page<User> selectUser(User user);

    public User findById(Integer id);

    public Integer editUser(User user);

    public Integer deleteUser(Integer id);
}
