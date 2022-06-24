package com.wu.service;

import com.wu.entity.PageResult;
import com.wu.pojo.Authorization;

import java.util.Map;

public interface AuthorizationService {

    Integer addAuthorization(Authorization authorization);

    /**
     * 根据权限id查询
     * @param userId
     * @return
     */
    Authorization selectByAuthorizationId(Integer userId);


    PageResult selectAuthorization(Map<String,Object> map, Integer pageNum, Integer pageSize);

    Integer deleteAuthorization(Integer userId);


    Integer editAuthorization(Authorization authorization);


}
