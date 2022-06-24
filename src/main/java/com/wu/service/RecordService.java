package com.wu.service;

import com.github.pagehelper.Page;
import com.wu.entity.PageResult;
import com.wu.pojo.Record;

import java.util.Map;

public interface RecordService {
    Integer addRecord(Record record);

    Record selectByRecordId(Integer recordId);
    Record selectByRecordCode(String recordCode);
    /**
     *
     * @param map 模糊查询 出入库时间位于startTime（开始时间 Date数据类型） - endTime(结束时间) 以及recordCode 和recordWarehouse
     * （若还要加模糊查询字段可以自己加）
     * @return
     */
    PageResult selectRecord(Map<String,Object> map, Integer pageNum, Integer pageSize);

    Integer deleteRecord(Integer recordId);

    Integer editRecord(Record record);

    /**
     * 查询指定类别出入库单
     * @param status 0 表示出库 1表示入库
     * @return
     */
    Page<Record> selectAllRecord(Integer status);


}
