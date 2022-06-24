package com.wu.mapper;

import com.github.pagehelper.Page;
import com.wu.pojo.RecordItem;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
public interface RecordItemMapper {

    Integer addRecordItem(RecordItem recordItem);

    RecordItem selectByRecordItemId(Integer recordItemId);

    /**
     *
     * @param map 对通过RecordCode模糊查询相关对应的出入库单明细列表
     * @return
     */
    List<RecordItem> selectRecordItem(Map<String,Object> map);

    Integer deleteRecordItem(Integer recordItemId);

    Integer editRecordItem(RecordItem recordItem);




}
