package com.wu.service;

import com.wu.entity.PageResult;
import com.wu.pojo.RecordItem;

import java.util.Map;

public interface RecordItemService {

    Integer addRecordItem(RecordItem recordItem);

    RecordItem selectByRecordItemId(Integer recordItemId);

    /**
     *
     * @param map 对RecordItemId进行模糊查询
     * @return
     */
    PageResult selectRecordItem(Map<String,Object> map,Integer pageNum,Integer pageSize);

    Integer deleteRecordItem(Integer recordItemId);

    Integer editRecordItem(RecordItem recordItem);


}
