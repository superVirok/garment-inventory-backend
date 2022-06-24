package com.wu.pojo;/*
 * Created by Virok on 2022/5/31
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component("RecordItem")
public class RecordItem {
    private Integer recordItemId;
    private Integer recordId;
    private Integer goodsId;
    private String recordCode;
    private Goods goods;//对应商品信息
    private Type type;//对应类别信息
    private Integer recordItemCount; //商品出入库数量
    public RecordItem() {
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public Integer getRecordItemId() {
        return recordItemId;
    }

    public void setRecordItemId(Integer recordItemId) {
        this.recordItemId = recordItemId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getRecordItemCount() {
        return recordItemCount;
    }

    public void setRecordItemCount(Integer recordItemCount) {
        this.recordItemCount = recordItemCount;
    }

    public RecordItem(Integer recordItemId, Integer recordId, Integer goodsId, Goods goods, Type type, Integer recordItemCount) {
        this.recordItemId = recordItemId;
        this.recordId = recordId;
        this.goodsId = goodsId;
        this.goods = goods;
        this.type = type;
        this.recordItemCount = recordItemCount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RecordItem{" +
                "recordItemId=" + recordItemId +
                ", recordId=" + recordId +
                ", goodsId=" + goodsId +
                ", recordCode=" + recordCode +
                ", goods=" + goods +
                ", type=" + type +
                ", recordItemCount=" + recordItemCount +
                '}';
    }
}