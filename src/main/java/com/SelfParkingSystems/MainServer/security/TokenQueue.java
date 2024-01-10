package com.SelfParkingSystems.MainServer.security;

import java.util.Date;

public class TokenQueue {

    private String data=null;
    private String userName=null;
    private Date initialDate=null;
    public TokenQueue next=null;
    public TokenQueue prev=null;

    public TokenQueue() {
    }

    public TokenQueue(String data,String userName, Date initialDate) {
        this.data = data;
        this.userName = userName;
        this.initialDate = initialDate;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
