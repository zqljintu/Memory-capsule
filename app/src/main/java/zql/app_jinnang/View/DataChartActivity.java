package zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;
import zql.app_jinnang.Prestener.PrestenerImp_datachart;
import zql.app_jinnang.Prestener.Prestener_datachart;
import zql.app_jinnang.R;

public class DataChartActivity extends AppCompatActivity implements DataChartActivityImp {
    private PrestenerImp_datachart prestenerImp_datachart;
    private PieChartView pieChartView;
    private Integer integer0,integer1;
    private List<SliceValue> values = new ArrayList<SliceValue>();
    private List<Integer>typesNum=new ArrayList<>();
    private int notesum;
    private int[] colorData = {Color.parseColor("#f4ea2a"),
            Color.parseColor("#d81e06"),
            Color.parseColor("#1afa29"),
            Color.parseColor("#1296ab"),
            Color.parseColor("#d4237a")};
    private String[] stateChar = {"工作", "学习", "生活", "日记", "旅行"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_chart);
        prestenerImp_datachart=new Prestener_datachart(this);
        prestenerImp_datachart.setBackgroundcolorfromSeting();
        StatusBarUtil.setColor(this,integer0);
        initToolbar();
        initPieChart();
    }    private void initToolbar(){
        Toolbar toolbar_chart=(Toolbar) this.findViewById(R.id.toolbar_chart);
        toolbar_chart.setBackgroundColor(integer0);
        setSupportActionBar(toolbar_chart);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("数据分析");
        toolbar_chart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initPieChart(){//设置饼状图
        typesNum=prestenerImp_datachart.getPieChartListfromData();
        notesum=prestenerImp_datachart.getPieChartSumfromDatatoActivity();
        for (int i = 0; i < 5; ++i) {
            SliceValue sliceValue = new SliceValue((float) typesNum.get(i), colorData[i]);
            values.add(sliceValue);
        }
        pieChartView=(PieChartView)this.findViewById(R.id.piechart);
        pieChartView.setViewportCalculationEnabled(true);
        pieChartView.setChartRotationEnabled(false);
        pieChartView.setAlpha(0.9f);
        pieChartView.setCircleFillRatio(1f);
        pieChartView.setValueSelectionEnabled(true);
        final PieChartData pieChartData=new PieChartData();
        pieChartData.setHasLabels(true);
        pieChartData.setHasLabelsOnlyForSelected(false);
        pieChartData.setHasLabelsOutside(false);
        pieChartData.setHasCenterCircle(true);
        pieChartData.setCenterCircleColor(Color.WHITE);
        pieChartData.setCenterCircleScale(0.5f);
        pieChartData.setCenterText1Color(integer0);
        pieChartData.setCenterText2Color(Color.BLUE);
        pieChartData.setValues(values);
        pieChartView.setPieChartData(pieChartData);
        pieChartView.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int arcIndex, SliceValue value) {
                pieChartData.setCenterText1Color(integer0);
                pieChartData.setCenterText1FontSize(12);
                pieChartData.setCenterText1(stateChar[arcIndex]);
                pieChartData.setCenterText2Color(integer0);
                pieChartData.setCenterText2FontSize(16);
                pieChartData.setCenterText2(value.getValue()+"("+getCenterStringfromData(arcIndex)+")");
            }
            @Override
            public void onValueDeselected() {

            }
        });
    }
    private String getCenterStringfromData(int i){
        String result="";
        int sum=prestenerImp_datachart.getPieChartSumfromDatatoActivity();
        result=String.format("%.2f",(float)typesNum.get(i)*100/sum)+"%";
        return result;
    }

    @Override
    public Application getAddApplication() {
        return this.getApplication();
    }

    @Override
    public Context getAddActivityContext() {
        return this;
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer> colorlist) {
        integer0=colorlist.get(0);
        integer1=colorlist.get(1);
    }
}
