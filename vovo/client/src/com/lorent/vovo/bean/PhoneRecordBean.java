/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.vovo.bean;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class PhoneRecordBean {

    private int id;
    private String sender;
    private String reciever;
    private Date createDate;
    private int status;
    private String phoneNumber;
    
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
