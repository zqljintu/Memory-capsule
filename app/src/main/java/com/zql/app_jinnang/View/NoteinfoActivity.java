package com.zql.app_jinnang.View;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.zql.comm.widget.SwipeActivity;

import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import com.zql.app_jinnang.Bean.Means;
import com.zql.app_jinnang.Bean.Noteinfo;
import com.zql.app_jinnang.Prestener.PrestenerImp_noteinfo;
import com.zql.app_jinnang.Prestener.Prestener_noteinfo;
import com.zql.app_jinnang.R;

public class NoteinfoActivity extends SwipeActivity implements NoteinfoActivityImp{
    PrestenerImp_noteinfo prestenerImpNoteinfo;
    private TagGroup tagGroup_noteinfo;
    private TextView textView_noteinfo;
    private ImageView imageview_noteinfo;
    private CoordinatorLayout coordinatorLayout_noteinfo;
    private Integer maincolor;
    private Toolbar toolbar;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteinfo);
        prestenerImpNoteinfo=new Prestener_noteinfo(this);
        initView();
        getintentExtra();
        prestenerImpNoteinfo.setBackgroundcolorfromSeting();
    }
    private void initView(){
        initToolbarSeting();
        initTaggroupView();
        initTextview();
        initImageview();
    }
    private void getintentExtra(){//获取传递过来的信息，并通过PrestenerImpNoteInfo读取显示在NoteinfoActivity上
        Intent mintent=getIntent();
        Bundle bundle=mintent.getExtras();
        Noteinfo noteinfo= (Noteinfo) bundle.getSerializable("noteinfo");
        prestenerImpNoteinfo.readDatatoNoteinfo(noteinfo);
    }
    private void initToolbarSeting(){//toolbard的设置
        toolbar=(Toolbar)this.findViewById(R.id.toolbar_noteinfo);
        coordinatorLayout_noteinfo=(CoordinatorLayout)this.findViewById(R.id.coordinator_noteinfo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.cool_noteinfo);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }
    private void initTaggroupView(){//实例化TagGroup
        tagGroup_noteinfo=(TagGroup)this.findViewById(R.id.taggroup_noteinfo_item);
    }
    private void initTextview(){//实例化TextView
        textView_noteinfo=(TextView)this.findViewById(R.id.text_noteinfo_item);
    }
    private void initImageview(){//实例化ImagView
        imageview_noteinfo=(ImageView)this.findViewById(R.id.noteinfo_imageview);
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
        if (photopath.equals("<图片>")||photopath.equals("null")){
            switch (type){
                case "旅行":
                    imageview_noteinfo.setImageResource(R.drawable.photo_travel);
                    break;
                case "学习":
                    imageview_noteinfo.setImageResource(R.drawable.photo_study);
                    break;
                case "工作":
                    imageview_noteinfo.setImageResource(R.drawable.photo_work);
                    break;
                case "日记":
                    imageview_noteinfo.setImageResource(R.drawable.photo_dilary);
                    break;
                case "生活":
                    imageview_noteinfo.setImageResource(R.drawable.photo_live);
                    break;
                default:
                    imageview_noteinfo.setImageResource(R.drawable.photo_live);
                    break;
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
