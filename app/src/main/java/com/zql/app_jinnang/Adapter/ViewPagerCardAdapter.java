package com.zql.app_jinnang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.zql.app_jinnang.Bean.Means;
import com.zql.app_jinnang.Bean.NoteBean;
import com.zql.app_jinnang.Prestener.PrestenerImp_main;
import com.zql.app_jinnang.R;
import com.zql.app_jinnang.View.MainActivityImp;
import com.zql.app_jinnang.View.NoteinfoActivity;

/**
 * Created by 尽途 on 2018/4/8.
 */

public class ViewPagerCardAdapter extends PagerAdapter {
    private View currentView;
    private MainActivityImp mainActivityImp;
    public static ImageView menu_item_viewpagercard;
    private ImageView imageview_item_viewpagercard,lableview_item_viewpagercard;
    private TextView textview_item_viewpagercard,createtime_item_viewpagercard;
    private CardView viewpager_card;
    private List<NoteBean>list;
    private Context context;
    private LayoutInflater layoutInflater;
    private PrestenerImp_main prestenerImp_main;
    private int mChildCount = 0;

    public ViewPagerCardAdapter(Context mcontext,List<NoteBean>mlist,MainActivityImp mmainActivityImp,PrestenerImp_main mprestenerImp_main){
        this.context=mcontext;
        this.list=mlist;
        this.mainActivityImp=mmainActivityImp;
        this.prestenerImp_main=mprestenerImp_main;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentView=(View)object;
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String type=list.get(position).getNotetype().toString();
        String noteinfo=list.get(position).getNoteinfo().toString();
        String photopath=list.get(position).getPhotopath().toString();
        String createtime=list.get(position).getCreatetime().toString();
        View view=layoutInflater.inflate(R.layout.item_viewpagercard,container,false);
        imageview_item_viewpagercard=(ImageView)view.findViewById(R.id.imageview_item_viewpagercard);
        lableview_item_viewpagercard=(ImageView)view.findViewById(R.id.lableview_item_viewpagercard);
        menu_item_viewpagercard=(ImageView)view.findViewById(R.id.menu_item_viewpagercard);
        textview_item_viewpagercard=(TextView)view.findViewById(R.id.textview_item_viewpagercard);
        createtime_item_viewpagercard=(TextView)view.findViewById(R.id.createtime_item_viewpagercard);
        viewpager_card=(CardView)view.findViewById(R.id.viewPager_card);
        setMenuListener(menu_item_viewpagercard,list.get(position));
        startNoteinfoActivity(viewpager_card,list.get(position));
        textview_item_viewpagercard.setText(Means.getNotetextOnViewPagerCard(noteinfo));
        createtime_item_viewpagercard.setText("——创建于:"+createtime);
        if (photopath.equals("<图片>")||photopath.equals("null")){
            imageview_item_viewpagercard.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }else {
            imageview_item_viewpagercard.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        readNoteonMainbyNotetype(lableview_item_viewpagercard);
        /**
         * 早期开发留下的弊端，必须满足老版本，所以添加photopath.equals("<图片>")||photopath.equals("null")两种状态判断条件
         */
        switch (type){
            case "生活":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_live);
                if (photopath.equals("<图片>")||photopath.equals("null")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_live);
                }else {
                    Glide.with(mainActivityImp.getActivity_this()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "工作":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_work);
                if (photopath.equals("<图片>")||photopath.equals("null")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_work);
                }else {
                    Glide.with(mainActivityImp.getActivity_this()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "日记":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_diary);
                if (photopath.equals("<图片>")||photopath.equals("null")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_dilary);
                }else {
                    Glide.with(mainActivityImp.getActivity_this()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "学习":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_study);
                if (photopath.equals("<图片>")||photopath.equals("null")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_study);
                }else {
                    Glide.with(mainActivityImp.getActivity_this()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "旅行":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_travel);
                if (photopath.equals("<图片>")||photopath.equals("null")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_travel);
                }else {
                    Glide.with(mainActivityImp.getActivity_this()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            default:
                break;
        }
        container.addView(view);
        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    private void setMenuListener(View view, final NoteBean noteBean){//对相应的按钮进行监听
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityImp.openSheetDialog(noteBean);
            }
        });
    }
    private void readNoteonMainbyNotetype(View view){//通过标签来浏览信息
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTypeDilog();
            }
        });
    }
    private void openTypeDilog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View centerview=layoutInflater.inflate(R.layout.dialog_main_type,null);
        final AlertDialog alertDialog=builder.setView(centerview).create();
        final LinearLayout add_dialog_typr_all,add_dialog_type_work,add_dialog_type_study,add_dialog_type_live,add_dialog_type_diary,add_dialog_type_travel;
        add_dialog_typr_all=(LinearLayout)centerview.findViewById(R.id.main_dialog_typenote_all);
        add_dialog_type_work=(LinearLayout)centerview.findViewById(R.id.main_dialog_typenote_work);
        add_dialog_type_diary=(LinearLayout)centerview.findViewById(R.id.main_dialog_typenote_diary);
        add_dialog_type_live=(LinearLayout)centerview.findViewById(R.id.main_dialog_typenote_live);
        add_dialog_type_study=(LinearLayout)centerview.findViewById(R.id.main_dialog_typenote_study);
        add_dialog_type_travel=(LinearLayout)centerview.findViewById(R.id.main_dialog_typenote_travel);
        add_dialog_typr_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestenerImp_main.readNotefromDtabyType(0);
                alertDialog.dismiss();
            }
        });
        add_dialog_type_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestenerImp_main.readNotefromDtabyType(1);//调用接口实现标签加载
                alertDialog.dismiss();
            }
        });
        add_dialog_type_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestenerImp_main.readNotefromDtabyType(2);//调用接口实现标签加载
                alertDialog.dismiss();
            }
        });
        add_dialog_type_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestenerImp_main.readNotefromDtabyType(3);//调用接口实现标签加载
                alertDialog.dismiss();
            }
        });
        add_dialog_type_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestenerImp_main.readNotefromDtabyType(4);//调用接口实现标签加载
                alertDialog.dismiss();
            }
        });

        add_dialog_type_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestenerImp_main.readNotefromDtabyType(5);//调用接口实现标签加载
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void startNoteinfoActivity(View view,final NoteBean noteBean){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent=new Intent(mainActivityImp.getActivity_this(),NoteinfoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
                mintent.putExtras(bundle);
                context.startActivity(mintent);
            }
        });
    }
    public View getPrimaryItem(){
        return currentView;
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
