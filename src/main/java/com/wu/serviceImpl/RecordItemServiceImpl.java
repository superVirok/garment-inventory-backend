package com.wu.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wu.entity.PageResult;
import com.wu.mapper.*;
import com.wu.pojo.RecordItem;
import com.wu.service.RecordItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("RecordItemServiceImpl")
public class RecordItemServiceImpl implements RecordItemService {
    @Autowired
    private  RecordItemMapper recordItemMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private RecordMapper recordMapper;
    public  RecordItemServiceImpl(){}
    @Override
    public Integer addRecordItem(RecordItem recordItem) {
        return recordItemMapper.addRecordItem(recordItem);
    }

    @Override
    public RecordItem selectByRecordItemId(Integer recordItemId) {
        return recordItemMapper.selectByRecordItemId(recordItemId);
    }

    @Override
    public PageResult selectRecordItem(Map<String, Object> map,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
       List<RecordItem> list= recordItemMapper.selectRecordItem(map);
       for(RecordItem recordItem:list){
          recordItem.setRecordCode(recordMapper.selectByRecordId(recordItem.getRecordId()).getRecordCode());
          recordItem.setGoods(goodsMapper.findGoodsById(recordItem.getGoodsId()));
          recordItem.setType(typeMapper.findTypeNameById(recordItem.getGoods().getTypeId()));
       }
        Page<RecordItem> page= (Page<RecordItem>) list;
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public Integer deleteRecordItem(Integer recordItemId) {
        return recordItemMapper.deleteRecordItem(recordItemId);
    }

    @Override
    public Integer editRecordItem(RecordItem recordItem) {
        return recordItemMapper.editRecordItem(recordItem);
    }
}
