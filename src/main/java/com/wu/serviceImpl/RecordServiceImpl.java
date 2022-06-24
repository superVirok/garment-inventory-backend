package com.wu.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wu.entity.PageResult;
import com.wu.mapper.RecordMapper;
import com.wu.pojo.Record;
import com.wu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("RecordServiceImpl")
@Transactional
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;


    @Override
    public Integer addRecord(Record record) {
        return recordMapper.addRecord(record);
    }

    @Override
    public Record selectByRecordId(Integer recordId) {
        return recordMapper.selectByRecordId(recordId);
    }

    @Override
    public Record selectByRecordCode(String recordCode) {
        return recordMapper.selectByRecordCode(recordCode);
    }

    @Override
    public PageResult selectRecord(Map<String,Object> map,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Record> page=recordMapper.selectRecord(map);
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public Integer deleteRecord(Integer recordId) {
        return recordMapper.deleteRecord(recordId);
    }

    @Override
    public Integer editRecord(Record record) {
        return recordMapper.editRecord(record);
    }

    @Override
    public Page<Record> selectAllRecord(Integer status) {
        return recordMapper.selectAllRecord(status);
    }

}
