package com.zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;

import java.util.List;

public interface DataChartActivityImp {
    public Context getAddActivityContext();
    public Application getAddApplication();
    public void setBackgroundcolorfromSeting(List<Integer> colorlist);
}
