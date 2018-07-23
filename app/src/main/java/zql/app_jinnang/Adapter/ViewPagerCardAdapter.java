package zql.app_jinnang.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.R;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.MainActivity;
import zql.app_jinnang.View.MainActivityImp;
import zql.app_jinnang.View.NoteinfoActivity;

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
    private UserSeting userSeting;

    public ViewPagerCardAdapter(Context mcontext,List<NoteBean>mlist,MainActivityImp mmainActivityImp){
        this.context=mcontext;
        this.list=new ArrayList<NoteBean>();
        if (!this.list.isEmpty()){
            this.list.clear();
        }
        this.list=mlist;
        this.mainActivityImp=mmainActivityImp;
        userSeting=(UserSeting)mainActivityImp.getMainApplication();
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
        startNoteinfoActivity(imageview_item_viewpagercard,list.get(position));
        textview_item_viewpagercard.setText(Means.getNotetextOnViewPagerCard(noteinfo));
        createtime_item_viewpagercard.setText("——创建于:"+createtime);
        if (photopath.equals("<图片>")){
            imageview_item_viewpagercard.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }else {
            imageview_item_viewpagercard.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        switch (type){
            case "生活":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_live);
                if (photopath.equals("<图片>")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_live);
                }else {
                    Glide.with(mainActivityImp.getActivity_this()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "工作":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_work);
                if (photopath.equals("<图片>")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_work);
                }else {
                    Glide.with(mainActivityImp.getActivity_this()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "日记":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_diary);
                if (photopath.equals("<图片>")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_dilary);
                }else {
                    Glide.with(mainActivityImp.getActivity_this()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "学习":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_study);
                if (photopath.equals("<图片>")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_study);
                }else {
                    Glide.with(mainActivityImp.getActivity_this()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "旅行":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_travel);
                if (photopath.equals("<图片>")){
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

}
