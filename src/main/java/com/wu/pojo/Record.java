package com.wu.pojo;/*
 * Created by Virok on 2022/5/31
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("Record")
public class Record {
    private Integer recordId; //出入库记录id
    private String recordCode; //出入库编号
    private String recordWarehouse; //仓库名
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd" ,timezone="GMT+8")
    private Date recordTime; //出入库时间
    private String recordName; //经办人名字
    private String recordOrigin; //来源
    private int recordStatus; //出入库标识 1/0代表出库/入库


    public Record() {
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getRecordWarehouse() {
        return recordWarehouse;
    }

    public void setRecordWarehouse(String recordWarehouse) {
        this.recordWarehouse = recordWarehouse;
    }


    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getRecordOrigin() {
        return recordOrigin;
    }

    public void setRecordOrigin(String recordOrigin) {
        this.recordOrigin = recordOrigin;
    }

    public int getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(int recordStatus) {
        this.recordStatus = recordStatus;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordId=" + recordId +
                ", recordCode='" + recordCode + '\'' +
                ", recordWarehouse='" + recordWarehouse + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", recordName='" + recordName + '\'' +
                ", recordOrigin='" + recordOrigin + '\'' +
                ", recordStatus=" + recordStatus +
                '}';
    }
}
