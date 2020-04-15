package com.zql.lib_info.view;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jaeger.library.StatusBarUtil;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.Noteinfo;
import com.zql.comm.route.RouteKey;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_info.R;

import java.util.List;

import me.gujun.android.taggroup.TagGroup;

@Route(path = RouteUrl.Url_NoteinfoActivity)
public class NoteinfoActivity extends BaseLifecycleActivity<NoteInfoPresenter> implements NoteInfoContract.view{
    private TagGroup tagGroup_noteinfo;
    private TextView textView_noteinfo;
    private ImageView imageview_noteinfo;
    private CoordinatorLayout coordinatorLayout_noteinfo;
    private int maincolor;
    private Toolbar toolbar;


    @Override
    protected void initView() {
        initToolbarSeting();
        initTaggroupView();
        initTextview();
        initImageview();
        getintentExtra();
        mPresenter.setBackgroundcolorfromSeting();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_noteinfo;
    }

    @Override
    protected NoteInfoPresenter getPresenter() {
        return new NoteInfoPresenter(this);
    }

    /**
     * 获取传递过来的信息，并通过PrestenerImpNoteInfo读取显示在NoteinfoActivity上
     */
    private void getintentExtra(){
        Intent mintent=getIntent();
        Bundle bundle=mintent.getBundleExtra(RouteKey.NOTE_INFO);
        Noteinfo noteinfo= (Noteinfo) bundle.getSerializable(RouteKey.CAPSULE_INFO);
        mPresenter.readDatatoNoteinfo(noteinfo);
    }
    private void initToolbarSeting(){//toolbard的设置
        toolbar = findViewById(R.id.toolbar_noteinfo);
        coordinatorLayout_noteinfo = findViewById(R.id.coordinator_noteinfo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.cool_noteinfo);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }
    private void initTaggroupView(){//实例化TagGroup
        tagGroup_noteinfo = findViewById(R.id.taggroup_noteinfo_item);
    }
    private void initTextview(){//实例化TextView
        textView_noteinfo = findViewById(R.id.text_noteinfo_item);
    }
    private void initImageview(){//实例化ImagView
        imageview_noteinfo = findViewById(R.id.noteinfo_imageview);
    }


    @Override
    public void readNoteinfotoNotetext(String noteinfo) {
        if (noteinfo.isEmpty()){
            textView_noteinfo.setText("无内容");
        }else {
            textView_noteinfo.setText(noteinfo);
            toolbar.setTitle(Means.getNoteTitleOnNoteinfoActivity(noteinfo));
        }
    }

    @Override
    public void readLabelinfotoNoteTagrroup(List<String> tags) {
        if (tags.isEmpty()){
            tagGroup_noteinfo.setTags("无标签");
        }else {
            tagGroup_noteinfo.setTags(tags);
        }
    }

    @Override
    public void readPhotopathtoNoteImageview(String photopath,String type) {
        if (TextUtils.isEmpty(photopath)){
            if (Means.STR_WORK.equals(type)){
                imageview_noteinfo.setImageResource(R.drawable.photo_work);
            }else if (Means.STR_STUDY.equals(type)){
                imageview_noteinfo.setImageResource(R.drawable.photo_study);
            }else if (Means.STR_LIVE.equals(type)){
                imageview_noteinfo.setImageResource(R.drawable.photo_live);
            }else if (Means.STR_DIARY.equals(type)){
                imageview_noteinfo.setImageResource(R.drawable.photo_dilary);
            }else if (Means.STR_TRAYEL.equals(type)){
                imageview_noteinfo.setImageResource(R.drawable.photo_travel);
            }else {
                imageview_noteinfo.setImageResource(R.drawable.photo_live);
            }
        }else {
            Glide.with(this).load(photopath).into(imageview_noteinfo);
        }
    }

    @Override
    public Application getNoteinfoApplication() {
        return getApplication();
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer> colorlist) {
        StatusBarUtil.setColor(this, colorlist.get(0));
        maincolor=colorlist.get(0);
    }
}
