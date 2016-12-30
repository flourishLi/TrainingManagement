package com.trainingmanagement.model;

public class TrainWithBLOBs extends Train {
    private byte[] traincontent;

    private byte[] userlist;

    public byte[] getTraincontent() {
        return traincontent;
    }

    public void setTraincontent(byte[] traincontent) {
        this.traincontent = traincontent;
    }

    public byte[] getUserlist() {
        return userlist;
    }

    public void setUserlist(byte[] userlist) {
        this.userlist = userlist;
    }
}