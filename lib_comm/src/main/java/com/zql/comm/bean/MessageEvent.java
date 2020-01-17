package com.zql.comm.bean;

import com.zql.base.event.BaseEvent;

public class MessageEvent extends BaseEvent {

    public final static int UPDATE_DATA = 0;

    public final static int UPDATA_COLOR = 1;

    public final static int UPDATE_NETCAPSULE = 2;

    public final static int UPDATE_LOGOUT = 3;

    private int messageevent;
    public MessageEvent(int event){
        this.messageevent=event;
    }

    public void setMessageevent(int messageevent) {
        this.messageevent = messageevent;
    }


    public int getMessageevent() {
        return messageevent;
    }
}
