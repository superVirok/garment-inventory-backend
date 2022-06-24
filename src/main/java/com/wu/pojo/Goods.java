package com.wu.pojo;/*
 * Created by Virok on 2022/5/31
 */

import org.springframework.stereotype.Component;

@Component("Goods")
public class Goods {
    private Integer goodsId; //商品id
    private String goodsCode; //商品编号
    private Integer typeId; //商品类型id
    private Integer goodsStock; //商品库存
    private String goodsName; //商品名
    private String goodsSize; //商品尺寸
    private String goodsColor; //商品颜色
    private float goodsPrice; //商品价格
    private String goodsCompany; //商品来源公司
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Goods() {
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsCompany() {
        return goodsCompany;
    }

    public void setGoodsCompany(String goodsCompany) {
        this.goodsCompany = goodsCompany;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", typeId=" + typeId +
                ", goodsStock=" + goodsStock +
                ", goodsName='" + goodsName + '\'' +
                ", goodsSize='" + goodsSize + '\'' +
                ", goodsColor='" + goodsColor + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsCompany='" + goodsCompany + '\'' +
                '}';
    }
}
