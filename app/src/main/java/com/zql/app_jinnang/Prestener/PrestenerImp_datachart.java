package com.zql.app_jinnang.Prestener;

import java.util.List;

import lecho.lib.hellocharts.model.SliceValue;

public interface PrestenerImp_datachart {
    public void setBackgroundcolorfromSeting();//设置主题色
    public List<SliceValue> getPieChartNumberfromDatatoActivity();//获取数据到界面
    public int getPieChartSumfromDatatoActivity();//获取总数
    public List<Integer> getPieChartListfromData();//获取列表
}
