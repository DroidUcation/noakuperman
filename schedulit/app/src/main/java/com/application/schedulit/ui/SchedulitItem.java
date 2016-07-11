package com.application.schedulit.ui;

import java.util.List;

/**
 * Created by yosinoa on 28/06/2016.
 */
public class SchedulitItem {

    public enum Type
    {
        View ,
        Edit
    }

    private Type type;
    private String msgText;
    private List<SchedulitContactItem> contacts;
    private int interval;
    private boolean isActive;

    public void setType(Type type){this.type = type;}

    public void setContacts(List<SchedulitContactItem> contacts){this.contacts = contacts;}

    public void setMsgText(String msgText){this.msgText = msgText;}

    public void setActive(boolean isActive){this.isActive =isActive;}

    public void setInterval(int interval){this.interval = interval;}

    public Type getType(){return  this.type;}

    public String getMsgText(){return  this.msgText;}

    public boolean getActive(){return this.isActive;}

    public int getInterval(){return this.interval;}

    public List<SchedulitContactItem> getContacts(){return this.contacts;}
}


