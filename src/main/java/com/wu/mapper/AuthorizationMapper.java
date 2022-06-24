package com.wu.mapper;

import com.github.pagehelper.Page;
import com.wu.pojo.Authorization;

import java.util.List;
import java.util.Map;

public interface AuthorizationMapper {

    Integer addAuthorization(Authorization authorization);

    /**
     * 根据权限id查询
     * @param userId
     * @return
     */
    Authorization selectByAuthorizationId(Integer userId);


    /**
     *
     * @param map 根据用户id userId 以及对应权限进行模糊查询
     * @return
     */
    List<Authorization> selectAuthorization(Map<String,Object> map);

    Integer deleteAuthorization(Integer userId);


    Integer editAuthorization(Authorization authorization);


}
