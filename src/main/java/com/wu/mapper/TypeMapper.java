package com.wu.mapper;

import com.github.pagehelper.Page;
import com.wu.pojo.Record;
import com.wu.pojo.Type;

import java.util.Map;

public interface TypeMapper {
    public Type findTypeNameById(Integer typeId);

    Page<Type> selectAllType();

    Page<Type> selectType(Type type);

    Integer addType(Type type);

    Integer editType(Type type);

    Integer deleteType(Integer typeId);
}
