package com.wu.service;

import com.github.pagehelper.Page;
import com.wu.entity.PageResult;
import com.wu.pojo.Record;
import com.wu.pojo.Type;

import java.util.Map;

public interface TypeService {
    Type findTypeNameById(Integer typeId);
    Page<Type> selectAllType();

    PageResult selectType(Type type, Integer pageNum, Integer pageSize);

    Integer addType(Type type);
    Integer editType(Type type);
    Integer deleteType(Integer typeId);
}
