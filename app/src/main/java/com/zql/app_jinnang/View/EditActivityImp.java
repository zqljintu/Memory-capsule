package com.zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;

import java.util.List;

public interface EditActivityImp {
    public Context getbasecontext();//获取
    public Application getapplication();
    public void setbackgroundcolor(List<Integer>list);//修改背景色
}
