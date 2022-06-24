package com.wu.mapperImpl;
import com.github.pagehelper.Page;
import com.wu.mapper.RecordItemMapper;
import com.wu.pojo.RecordItem;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class RecordItemMapperImpl implements RecordItemMapper {
    @Autowired
    private SqlSessionTemplate sqlSession;
    public void setSqlSession(SqlSessionTemplate sqlSession){
        this.sqlSession=sqlSession;
    }

    @Override
    public Integer addRecordItem(RecordItem recordItem) {
        return sqlSession.getMapper(RecordItemMapper.class).addRecordItem(recordItem);
    }

    @Override
    public RecordItem selectByRecordItemId(Integer recordItemId) {
        return sqlSession.getMapper(RecordItemMapper.class).selectByRecordItemId(recordItemId);
    }

    @Override
    public List<RecordItem> selectRecordItem(Map<String, Object> map) {
        return sqlSession.getMapper(RecordItemMapper.class).selectRecordItem(map);
    }


    @Override
    public Integer deleteRecordItem(Integer recordItemId) {
        return sqlSession.getMapper(RecordItemMapper.class).deleteRecordItem(recordItemId);
    }

    @Override
    public Integer editRecordItem(RecordItem recordItem) {
        return sqlSession.getMapper(RecordItemMapper.class).editRecordItem(recordItem);
    }


}
