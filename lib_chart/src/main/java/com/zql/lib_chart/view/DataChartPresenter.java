package com.zql.lib_chart.view;

import com.zql.base.ui.mvp.BasePresenter;
import com.zql.comm.Model.NoteInfoModel;
import com.zql.comm.Model.NoteInfoModelImp;
import com.zql.comm.UserSeting;

import java.util.List;

import lecho.lib.hellocharts.model.SliceValue;

public class DataChartPresenter extends BasePresenter<DataChartContract.view> implements DataChartContract.presenter {
    private UserSeting userSeting;
    private NoteInfoModelImp noteInfoModelImp;


    public DataChartPresenter(DataChartContract.view view) {
        super(view);
        userSeting=(UserSeting)getView().getAddApplication();
        noteInfoModelImp=new NoteInfoModel(getView().getAddActivityContext());
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        getView().setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }

    @Override
    public List<SliceValue> getPieChartNumberfromDatatoActivity() {
        return noteInfoModelImp.getPieChartNumberfromData();
    }

    @Override
    public List<Integer> getPieChartListfromData() {
        return noteInfoModelImp.getPieChartTypeListfromData();
    }

    @Override
    public int getPieChartSumfromDatatoActivity() {
        return noteInfoModelImp.QueryAllNoteSumfromfromData();
    }
}
