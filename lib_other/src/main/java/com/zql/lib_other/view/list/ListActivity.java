package com.zql.lib_other.view.list;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jaeger.library.StatusBarUtil;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.MessageEvent;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.route.RouteKey;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_other.R;
import com.zql.lib_other.adapter.RecyclerViewCardAdapter;

import org.angmarch.views.NiceSpinner;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Route(path = RouteUrl.Url_ListActivity)
public class ListActivity extends BaseLifecycleActivity<ListPresenter> implements ListContract.view {
    private Toolbar toolbar_list;

    private RecyclerView recyclerView;

    private RelativeLayout relativeLayout_list;

    private NiceSpinner niceSpinner;

    private int maincolor;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_list);
        initview();
        mPresenter.readNotefromDatatoList(0);
        mPresenter.setBackgroundcolorfromSeting();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_list;
    }

    private void initview(){//具体view实现函数
        initToolBarSeting();
        initRecyclerView();
        initdropView();
    }

    @Override
    protected ListPresenter getPresenter() {
        return new ListPresenter(this);
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
                mPresenter.deleteNotebean(noteBean);
                mPresenter.readNotefromDatatoList(0);
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
                        mPresenter.readNotefromDatatoList(0);
                        break;
                    case 1:
                        mPresenter.readNotefromDatatoList(1);
                        break;
                    case 2:
                        mPresenter.readNotefromDatatoList(2);
                        break;
                    case 3:
                        mPresenter.readNotefromDatatoList(3);
                        break;
                    case 4:
                        mPresenter.readNotefromDatatoList(4);
                        break;
                    case 5:
                        mPresenter.readNotefromDatatoList(5);
                        break;
                    case 6:
                        mPresenter.readNotefromDatatoList(6);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void readAllNotefromData(List<NoteBean> noteBeanList) {//读取数据库内的文件
        setMainBackgroundIcon(noteBeanList.size());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerViewCardAdapter recyclerViewCardAdapter=new RecyclerViewCardAdapter((ArrayList<NoteBean>) noteBeanList,this,mPresenter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewCardAdapter);
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer> colorlist) {
        maincolor = colorlist.get(0);
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
        LinearLayout list_dialog_linear_about,
                list_dialog_linear_hide,
                list_dialog_linear_delete,
                list_dialog_linear_change,
                list_dialog_linear_share;
        LinearLayout main_dialog = dialogview.findViewById(R.id.main_dialog);
        list_dialog_linear_about = dialogview.findViewById(R.id.main_dialog_linear_about);
        list_dialog_linear_hide = dialogview.findViewById(R.id.main_dialog_linear_hide);
        list_dialog_linear_delete = dialogview.findViewById(R.id.main_dialog_linear_delete);
        list_dialog_linear_change = dialogview.findViewById(R.id.main_dialog_linear_change);
        list_dialog_linear_share = dialogview.findViewById(R.id.main_dialog_linear_friendspace);
        main_dialog.setBackgroundColor(maincolor);
        list_dialog_linear_about.setOnClickListener(view -> {
            Bundle bundle=new Bundle();
            bundle.putSerializable(RouteKey.CAPSULE_INFO, Means.changefromNotebean(noteBean));
            ARouter.getInstance().build(RouteUrl.Url_NoteinfoActivity).withBundle(RouteKey.NOTE_INFO, bundle).navigation();
            bottomSheetDialog.dismiss();
        });
        list_dialog_linear_hide.setOnClickListener(view -> {
            initPasswordFileDialog(noteBean);
            bottomSheetDialog.dismiss();
        });
        list_dialog_linear_delete.setOnClickListener(view -> {
            initDeleteDilog(noteBean);
            bottomSheetDialog.dismiss();
        });
        list_dialog_linear_change.setOnClickListener(view -> {
            Bundle bundle=new Bundle();
            bundle.putInt(RouteKey.CAPSULE_TYPE,10);
            bundle.putSerializable(RouteKey.CAPSULE_INFO,Means.changefromNotebean(noteBean));
            ARouter.getInstance().build(RouteUrl.Url_EditActivity).withBundle(RouteKey.EDIT_DATA,bundle).navigation();
            bottomSheetDialog.dismiss();
        });
        list_dialog_linear_share.setOnClickListener(view -> {
            sharedNotetexttoWeChart(noteBean.getNoteinfo());
            bottomSheetDialog.dismiss();
        });
        bottomSheetDialog.setContentView(dialogview);
        bottomSheetDialog.show();
    }
    private void sharedNotetexttoWeChart(String text){//将便签文字分享到微信或者QQ等，主要调用系统分享控件
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        startActivity(Intent.createChooser(intent,"share"));
    }
    private void initPasswordFileDialog(final NoteBean noteBean){//将备忘录加入到私密文件夹中。
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("隐藏");
        builder.setMessage("确认隐藏？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPresenter.changeNotetoPasswordFile(noteBean);
                mPresenter.readNotefromDatatoList(0);
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
    public Application getListApplication() {
        return getApplication();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handEvent(MessageEvent messageEvent){
        switch (messageEvent.getMessageevent()){
            case MessageEvent.UPDATE_DATA:
                mPresenter.readNotefromDatatoList(0);
                break;
            case MessageEvent.UPDATA_COLOR:
                //prestenerImpMain.setBackgroundcolorfromSeting();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setMainBackgroundIcon(int size) {
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.list_empty);
        if (size==0){
            relativeLayout.setVisibility(View.VISIBLE);
        }else {
            relativeLayout.setVisibility(View.GONE);
        }
    }
}
