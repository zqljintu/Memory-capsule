package com.zql.lib_other.view.serect;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jaeger.library.StatusBarUtil;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.route.RouteKey;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_other.R;
import com.zql.lib_other.adapter.RecyclerViewSecretCardAdapter;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouteUrl.Url_ListSeActivity)
public class ListSecretActivity extends BaseLifecycleActivity<ListSecretPresenter> implements ListSecretContract.view{
    private Toolbar toolbar_listsecret;

    private RecyclerView recyclerView;

    private int color;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_list_secret;
    }

    @Override
    protected void initView() {
        init();
        mPresenter.readNoteserectfromDatatoList();
        mPresenter.setBackgroundcolorfromSeting();
    }

    @Override
    protected ListSecretPresenter getPresenter() {
        return new ListSecretPresenter(this);
    }

    private void init(){//具体view的实现
        inittoolbarSeting();
        initRecyclerView();
    }
    private void inittoolbarSeting(){//对toolbar的实例化
        toolbar_listsecret = findViewById(R.id.toolbar_list_secret);
        setSupportActionBar(toolbar_listsecret);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_listsecret.setNavigationOnClickListener(view -> finish());
    }
    private void initBottomMenu(final NoteBean noteBean){//Bottomsheetdialog进行实例化
        final BottomSheetDialog bottomSheetDialog  =new BottomSheetDialog(this);
        final View dialogview = LayoutInflater.from(this)
                .inflate(R.layout.activity_secret_dialog,null);
        LinearLayout list_dialog_linear_about,list_dialog_linear_delete;
        LinearLayout main_dialog = dialogview.findViewById(R.id.secret_dialog);
        list_dialog_linear_about = dialogview.findViewById(R.id.secret_dialog_linear_about);
        list_dialog_linear_delete = dialogview.findViewById(R.id.secret_dialog_linear_delete);
        main_dialog.setBackgroundColor(color);
        list_dialog_linear_about.setOnClickListener(view -> {
            Bundle bundle=new Bundle();
            bundle.putSerializable(RouteKey.CAPSULE_INFO, Means.changefromNotebean(noteBean));
            ARouter.getInstance().build(RouteUrl.Url_NoteinfoActivity).withBundle(RouteKey.NOTE_INFO,bundle).navigation();
            bottomSheetDialog.dismiss();
        });
        list_dialog_linear_delete.setOnClickListener(view -> {
            initDeleteDialog(noteBean);
            bottomSheetDialog.dismiss();
        });
        bottomSheetDialog.setContentView(dialogview);
        bottomSheetDialog.show();
    }
    private void initDeleteDialog(final NoteBean noteBean){//加入一个dialog，来询问是否要删除。
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("删除");
        builder.setMessage("确定删除");
        builder.setPositiveButton("确定", (dialogInterface, i) -> {
            mPresenter.deleteNotebeanserect(noteBean);
            mPresenter.readNoteserectfromDatatoList();
        });
        builder.setNegativeButton("取消", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.create().show();
    }

    @Override
    public void opensheetdialog(NoteBean noteBean) {
        initBottomMenu(noteBean);
    }

    private void initRecyclerView(){//对recycerView实例化
        recyclerView = findViewById(R.id.recycler_list_secret);
    }

    @Override
    public void readAllNoteSerectfromData(List<NoteBean> noteBeanList) {
        setMainBackgroundIcon(noteBeanList.size());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false);
        RecyclerViewSecretCardAdapter recyclerViewSecretCardAdapter=new RecyclerViewSecretCardAdapter(
                (ArrayList<NoteBean>) noteBeanList,
                this,
                mPresenter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewSecretCardAdapter);
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer> colorlist) {
        color = colorlist.get(0);
        StatusBarUtil.setColor(this, colorlist.get(0));
        toolbar_listsecret.setBackgroundColor(colorlist.get(0));
    }

    @Override
    public Application getListSerectApplication() {
        return getApplication();
    }

    @Override
    public Context getListSerectActivityContext() {
        return this;
    }
    public void setMainBackgroundIcon(int size) {
        RelativeLayout relativeLayout = findViewById(R.id.listserect_empty);
        if (size==0){
            relativeLayout.setVisibility(View.VISIBLE);
        }else {
            relativeLayout.setVisibility(View.GONE);
        }
    }
}
