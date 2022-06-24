package com.wu.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wu.entity.PageResult;
import com.wu.mapper.TypeMapper;
import com.wu.pojo.Record;
import com.wu.pojo.Type;
import com.wu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("TypeServiceImpl")
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public Type findTypeNameById(Integer typeId) {
        return typeMapper.findTypeNameById(typeId);
    }

    @Override
    public Page<Type> selectAllType() {
        return typeMapper.selectAllType();
    }

    @Override
    public PageResult selectType(Type type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Type> page=typeMapper.selectType(type);
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public Integer addType(Type type) {
        return typeMapper.addType(type);
    }

    @Override
    public Integer editType(Type type) {
        return typeMapper.editType(type);
    }

    @Override
    public Integer deleteType(Integer typeId) {
        return typeMapper.deleteType(typeId);
    }
}
