package zql.app_jinnang.View;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import zql.app_jinnang.Adapter.RecyclerViewTimeCardAdapter;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Service.DPCManager;
import cn.aigestudio.datepicker.bizs.decors.DPDecor;
import cn.aigestudio.datepicker.cons.DPMode;
import zql.app_jinnang.Service.DatePicker;
import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Prestener.PrestenerImp_calendar;
import zql.app_jinnang.Prestener.Prestener_calendar;
import zql.app_jinnang.R;
import static zql.app_jinnang.R.color.colorFloatingButton;

public class CalendarActivity extends SwipeActivity implements CalendarActivityImp{
    private PrestenerImp_calendar prestenerImp_calendar;
    private Integer integer0,integer1;
    private DatePicker datePicker;
    private RecyclerView recyclerView;
    @TargetApi(Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        prestenerImp_calendar=new Prestener_calendar(this);
        prestenerImp_calendar.setBackgroundcolorfromSeting();
        prestenerImp_calendar.readNotecreatimeOnCalendar();
        StatusBarUtil.setColor(this,integer0);
        initview();
    }
    private void initview(){
        initrecyclerview();
    }
    private void initrecyclerview(){
        recyclerView=(RecyclerView)this.findViewById(R.id.calendar_recycler);
        prestenerImp_calendar.readNotebeanOnRecycler(Means.getCreatetime());
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
        recyclerView.removeAllViews();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        RecyclerViewTimeCardAdapter recyclerViewTimeCardAdapter=new RecyclerViewTimeCardAdapter((ArrayList<NoteBean>) noteBeanList,this,this);
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
                prestenerImp_calendar.readNotebeanOnRecycler(date);
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
}
