package com.wu.mapperImpl;

import com.github.pagehelper.Page;
import com.wu.mapper.RecordMapper;
import com.wu.pojo.Record;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class RecordMapperImpl implements RecordMapper {
    @Autowired
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession){
        this.sqlSession=sqlSession;
    }

    @Override
    public Integer addRecord(Record record) {
        return sqlSession.getMapper(RecordMapper.class).addRecord(record);
    }

    @Override
    public Record selectByRecordId(Integer recordId) {
        return sqlSession.getMapper(RecordMapper.class).selectByRecordId(recordId);
    }

    @Override
    public Record selectByRecordCode(String recordCode) {
        return sqlSession.getMapper(RecordMapper.class).selectByRecordCode(recordCode);
    }

    @Override
    public Page<Record> selectRecord(Map<String, Object> map) {
        return sqlSession.getMapper(RecordMapper.class).selectRecord(map);
    }

    @Override
    public Integer deleteRecord(Integer recordId) {
        return sqlSession.getMapper(RecordMapper.class).deleteRecord(recordId);
    }

    @Override
    public Integer editRecord(Record record) {
        return sqlSession.getMapper(RecordMapper.class).editRecord(record);
    }

    @Override
    public Page<Record> selectAllRecord(Integer status) {
        return sqlSession.getMapper(RecordMapper.class).selectAllRecord(status);
    }
}
