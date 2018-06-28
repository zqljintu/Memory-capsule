package zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import zql.app_jinnang.Adapter.RecyclerViewCardAdapter;
import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Prestener.PrestenerImp_list;
import zql.app_jinnang.Prestener.Prestener_list;
import zql.app_jinnang.R;

public class ListActivity extends AppCompatActivity implements ListActivityImp {
    private Toolbar toolbar_list;
    private PrestenerImp_list prestenerImp_list;
    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout_list;
    private NiceSpinner niceSpinner;
    private static final int EDIT=0;
    private static final int CHANGE=1;
    private static final int REQUEST_UPDATE=2;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        prestenerImp_list=new Prestener_list(this);
        initView();
        prestenerImp_list.readNotefromDatatoList(0);
        prestenerImp_list.setBackgroundcolorfromSeting();
    }
    private void initView(){//具体view实现函数
        initToolBarSeting();
        initRecyclerView();
        initdropView();
    }
    private void initToolBarSeting(){//对ToolBar的具体实现
        toolbar_list=(Toolbar)this.findViewById(R.id.toolbar_list);
        relativeLayout_list=(RelativeLayout)this.findViewById(R.id.relativeLayout_list);
        setSupportActionBar(toolbar_list);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_list.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initRecyclerView(){//对recyclerview进行实例化
        recyclerView=(RecyclerView)this.findViewById(R.id.recycler_list);
    }
    private void initDeleteDilog(final NoteBean noteBean){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("删除");
        builder.setMessage("确定删除？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                prestenerImp_list.deleteNotebean(noteBean);
                prestenerImp_list.readNotefromDatatoList(0);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
    private void initdropView(){
        niceSpinner=(NiceSpinner)findViewById(R.id.list_spinner);
        List<String> data=new LinkedList<>(Arrays.asList("全部","工作","学习","生活","日记","旅行"));
        niceSpinner.attachDataSource(data);
        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        prestenerImp_list.readNotefromDatatoList(0);
                        break;
                    case 1:
                        prestenerImp_list.readNotefromDatatoList(1);
                        break;
                    case 2:
                        prestenerImp_list.readNotefromDatatoList(2);
                        break;
                    case 3:
                        prestenerImp_list.readNotefromDatatoList(3);
                        break;
                    case 4:
                        prestenerImp_list.readNotefromDatatoList(4);
                        break;
                    case 5:
                        prestenerImp_list.readNotefromDatatoList(5);
                        break;
                    case 6:
                        prestenerImp_list.readNotefromDatatoList(6);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void readAllNotefromData(List<NoteBean> noteBeanList) {//读取数据库内的文件
        recyclerView.removeAllViews();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerViewCardAdapter recyclerViewCardAdapter=new RecyclerViewCardAdapter((ArrayList<NoteBean>) noteBeanList,this,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewCardAdapter);
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer> colorlist) {
        StatusBarUtil.setColor(this, colorlist.get(0));
        toolbar_list.setBackgroundColor(colorlist.get(0));
        niceSpinner.setBackgroundColor(colorlist.get(0));
    }

    @Override
    public Context getListActivityConent() {
        return this;
    }

    @Override
    public void opensheeetdialog(NoteBean noteBean) {
        initBottomMenu(noteBean);
    }

    private void initBottomMenu(final NoteBean noteBean){//Bottomsheetdialog进行实例化
        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(ListActivity.this);
        final View dialogview= LayoutInflater.from(ListActivity.this)
                .inflate(R.layout.activity_main_dialog,null);
        LinearLayout list_dialog_linear_about,list_dialog_linear_hide,list_dialog_linear_delete,list_dialog_linear_change;
        list_dialog_linear_about=(LinearLayout)dialogview.findViewById(R.id.main_dialog_linear_about);
        list_dialog_linear_hide=(LinearLayout)dialogview.findViewById(R.id.main_dialog_linear_hide);
        list_dialog_linear_delete=(LinearLayout)dialogview.findViewById(R.id.main_dialog_linear_delete);
        list_dialog_linear_change=(LinearLayout)dialogview.findViewById(R.id.main_dialog_linear_change);
        list_dialog_linear_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent=new Intent(ListActivity.this,NoteinfoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
                mintent.putExtras(bundle);
                startActivity(mintent);
                bottomSheetDialog.dismiss();
            }
        });
        list_dialog_linear_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        list_dialog_linear_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDeleteDilog(noteBean);
                bottomSheetDialog.dismiss();
            }
        });
        list_dialog_linear_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addintent=new Intent(ListActivity.this,AddActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("addtype",CHANGE);
                bundle.putSerializable("noteinfo",Means.changefromNotebean(noteBean));
                addintent.putExtra("data",bundle);
                startActivityForResult(addintent,REQUEST_UPDATE);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(dialogview);
        bottomSheetDialog.show();
    }
    private void initPasswordFileDialog(final NoteBean noteBean){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("隐藏");
        builder.setMessage("确认隐藏？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                prestenerImp_list.changeNotetoPasswordFile(noteBean);
                prestenerImp_list.readNotefromDatatoList(0);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_UPDATE){
            prestenerImp_list.readNotefromDatatoList(0);
        }
    }

    @Override
    public Application getListApplication() {
        return getApplication();
    }
}
