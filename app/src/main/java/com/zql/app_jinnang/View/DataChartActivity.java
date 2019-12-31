package com.zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.zql.comm.widget.SwipeActivity;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;
import com.zql.app_jinnang.Prestener.PrestenerImp_datachart;
import com.zql.app_jinnang.Prestener.Prestener_datachart;
import com.zql.app_jinnang.R;

public class DataChartActivity extends SwipeActivity implements DataChartActivityImp {
    private PrestenerImp_datachart prestenerImp_datachart;
    private RelativeLayout relativeLayout;
    private PieChartView pieChartView;//饼状图
    private ColumnChartView columnChartView;//柱状图
    private Integer integer0,integer1;
    private NiceSpinner niceSpinner;
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
        typesNum=prestenerImp_datachart.getPieChartListfromData();
        notesum=prestenerImp_datachart.getPieChartSumfromDatatoActivity();
        initView();
        initPieChart();
    }
    private void initView(){
        relativeLayout=(RelativeLayout)this.findViewById(R.id.chart_relativelayout);
        initToolbar();
        initNicespinner();
    }
    private void initNicespinner(){//创建一个筛选器
        niceSpinner=(NiceSpinner)findViewById(R.id.chart_spinner);
        List<String> data=new LinkedList<>(Arrays.asList("饼状图","柱状图"));
        niceSpinner.attachDataSource(data);
        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        relativeLayout.removeAllViews();
                        initPieChart();
                        break;
                    case 1:
                        relativeLayout.removeAllViews();
                        initColumnChart();
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void initToolbar(){
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
        pieChartView=new PieChartView(this);
        List<SliceValue> values = new ArrayList<SliceValue>();
        int pieWidth=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,400, getResources().getDisplayMetrics());
        int pieHeigth=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,400, getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams pieChartParams=new RelativeLayout.LayoutParams(pieWidth,pieHeigth);
        pieChartParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        pieChartParams.addRule(RelativeLayout.CENTER_VERTICAL);
        for (int i = 0; i < 5; ++i) {
            SliceValue sliceValue = new SliceValue((float) typesNum.get(i), colorData[i]);
            values.add(sliceValue);
        }
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
        relativeLayout.addView(pieChartView,pieChartParams);
    }
    private void initColumnChart(){//创建柱状图
        columnChartView=new ColumnChartView(this);
        int columnWidth=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,400, getResources().getDisplayMetrics());
        int columnHeigth=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,400, getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams columnChartParams=new RelativeLayout.LayoutParams(columnWidth,columnHeigth);
        columnChartParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        columnChartParams.addRule(RelativeLayout.CENTER_VERTICAL);
        List<Column>columnList=new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i=0;i<5;++i){
            values=new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue((float)typesNum.get(i),colorData[i]));
            Column column=new Column(values);
            column.setHasLabels(false);
            column.setHasLabelsOnlyForSelected(true);
            columnList.add(column);
        }
        ColumnChartData columnChartData=new ColumnChartData(columnList);
        Axis axis=new Axis();
        Axis axiy=new Axis().setHasLines(true);
        List<AxisValue>axisValues=new ArrayList<>();
        for (int j=0;j<5;++j){
            axisValues.add(new AxisValue(j).setLabel(stateChar[j]));
        }
        axis.setValues(axisValues);
        axis.setName("类别");
        axiy.setName("数量");
        columnChartData.setAxisXBottom(axis);
        columnChartData.setAxisYLeft(axiy);
        columnChartView.setColumnChartData(columnChartData);
        relativeLayout.addView(columnChartView,columnChartParams);
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
