package com.zql.base.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Create by Totoro
 * 2019-11-07 16:47
 **/
public class EventBusUtil {


    public static void register(Object object) {
        EventBus.getDefault().register(object);
    }


    public static void unRegister(Object object) {
        EventBus.getDefault().unregister(object);
    }


    //发送事件
    public static void postEvent(BaseEvent event) {
        EventBus.getDefault().post(event);
    }
}
