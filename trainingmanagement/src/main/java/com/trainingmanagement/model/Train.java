package com.trainingmanagement.model;

import java.sql.Timestamp;
import java.util.Date;

public class Train {
    private Integer trainid;

    private Integer userid;

    private Timestamp data;

    private String trainname;

    private String trainlocation;

    public Integer getTrainid() {
        return trainid;
    }

    public void setTrainid(Integer trainid) {
        this.trainid = trainid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public String getTrainname() {
        return trainname;
    }

    public void setTrainname(String trainname) {
        this.trainname = trainname == null ? null : trainname.trim();
    }

    public String getTrainlocation() {
        return trainlocation;
    }

    public void setTrainlocation(String trainlocation) {
        this.trainlocation = trainlocation == null ? null : trainlocation.trim();
    }
}