package com.zql.lib_calendar.view;

import android.app.Application;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jaeger.library.StatusBarUtil;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.route.RouteUrl;
import com.zql.comm.widget.DatePicke.DatePicker;
import com.zql.lib_calendar.R;
import com.zql.lib_calendar.adapter.RecyclerViewTimeCardAdapter;


import java.util.ArrayList;
import java.util.List;

import cn.aigestudio.datepicker.bizs.calendars.DPCManager;
import cn.aigestudio.datepicker.bizs.decors.DPDecor;
import cn.aigestudio.datepicker.cons.DPMode;

@Route(path = RouteUrl.Url_CalendarActivity)
public class CalendarActivity extends BaseLifecycleActivity<CalendarPresenter> implements CalendarContract.view{

    private Integer integer0,integer1;

    private DatePicker datePicker;

    private RecyclerView recyclerView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void initView() {
        mPresenter.setBackgroundcolorfromSeting();
        mPresenter.readNotecreatimeOnCalendar();
        StatusBarUtil.setColor(this,integer0);
        initrecyclerview();
    }

    @Override
    protected CalendarPresenter getPresenter() {
        return new CalendarPresenter(this);
    }

    private void initrecyclerview(){
        recyclerView=(RecyclerView)this.findViewById(R.id.calendar_recycler);
        mPresenter.readNotebeanOnRecycler(Means.getCreatetime());
    }
    private void getIntentExtra(){
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer> colorlist) {
        integer0=colorlist.get(0);
        integer1=colorlist.get(1);
    }

    @Override
    public void readNotebeansfromDatabycreatetime(List<NoteBean> noteBeanList) {
       setMainBackgroundIcon(noteBeanList.size());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        RecyclerViewTimeCardAdapter recyclerViewTimeCardAdapter=new RecyclerViewTimeCardAdapter((ArrayList<NoteBean>) noteBeanList,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewTimeCardAdapter);
    }

    @Override
    public void initCalendarViewandgetCreattime(List<String> mlist) {
        DPCManager.getInstance().setDecorTR(mlist);
        datePicker=(DatePicker)this.findViewById(R.id.calendar_datapicker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            datePicker.getChildAt(0).setBackgroundColor(integer0);
            datePicker.getChildAt(1).setBackgroundColor(integer0);
        }
        datePicker.setDate(Means.getTheYearoncalendar(),Means.getTheMonthoncalendar());
        datePicker.setMode(DPMode.SINGLE);
        datePicker.setDPDecor(new DPDecor(){
            @Override
            public void drawDecorTR(Canvas canvas, Rect rect, Paint paint) {
                paint.setColor(integer0);
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, paint);
            }

        });
        datePicker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                mPresenter.readNotebeanOnRecycler(date);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return false;
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public Context getCalendarActivity() {
        return this;
    }

    @Override
    public Application getCalendarApplication() {
        return getApplication();
    }

    public void setMainBackgroundIcon(int size) {
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.calendar_empty);
        if (size==0){
            relativeLayout.setVisibility(View.VISIBLE);
        }else {
            relativeLayout.setVisibility(View.GONE);
        }
    }
}
