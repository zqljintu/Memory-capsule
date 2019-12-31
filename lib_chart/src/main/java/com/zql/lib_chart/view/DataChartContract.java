package com.zql.lib_chart.view;

import android.app.Application;
import android.content.Context;

import com.zql.base.ui.mvp.im.IView;

import java.util.List;

import lecho.lib.hellocharts.model.SliceValue;

public interface DataChartContract {
    interface view extends IView {
        Context getAddActivityContext();
        Application getAddApplication();
        void setBackgroundcolorfromSeting(List<Integer> colorlist);
    }

    interface presenter {
        void setBackgroundcolorfromSeting();//设置主题色
        List<SliceValue> getPieChartNumberfromDatatoActivity();//获取数据到界面
        int getPieChartSumfromDatatoActivity();//获取总数
        List<Integer> getPieChartListfromData();//获取列表
    }
}
