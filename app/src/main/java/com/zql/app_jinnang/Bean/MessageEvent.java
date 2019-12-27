package com.zql.app_jinnang.Bean;

public class MessageEvent {
    public final static int UPDATE_DATA=0;
    public final static int UPDATA_COLOR=1;

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
