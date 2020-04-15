package com.zql.lib_capsule.adapter;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxj.xpopup.XPopup;
import com.zql.base.utils.TimeUtils;
import com.zql.comm.bean.Means;
import com.zql.comm.netbean.response.CapsulesResponse;
import com.zql.comm.route.RouteKey;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_capsule.R;

import java.util.Calendar;
import java.util.List;

public class NetCapsuleAdapter extends BaseQuickAdapter<CapsulesResponse.ListBean, BaseViewHolder> {

    private OnMenuClickListener mlistener;


    public NetCapsuleAdapter(int layoutResId, @Nullable List<CapsulesResponse.ListBean> data, OnMenuClickListener listener) {
        super(layoutResId, data);
        this.mlistener = listener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CapsulesResponse.ListBean item) {
        ConstraintLayout concapsule = helper.itemView.findViewById(R.id.con_capsule);
        ImageView imgmenu = helper.itemView.findViewById(R.id.img_menu);
        helper.setText(R.id.text_content, Means.getNotetexOnCapsuleCard(item.getFields().getCapsule_content()));
        helper.setText(R.id.text_day, showCreateTimeDay(item.getFields().getCapsule_create_time()));
        helper.setText(R.id.text_month, showCreateTimeMonth(item.getFields().getCapsule_create_time()));
        helper.setText(R.id.text_week_day, showCreateTimeWeekDay(item.getFields().getCapsule_create_time()));
        helper.setImageResource(R.id.img_type, getImageTypeRSId(item.getFields().getCapsule_type()));
        helper.setText(R.id.text_size, item.getFields().getCapsule_content().length() + "字");
        if (Means.isEmpty(item.getFields().getCapsule_date())){
            helper.setGone(R.id.img_time,false);
        }else {
            helper.setVisible(R.id.img_time,true);
        }
        if (Means.isEmpty(item.getFields().getCapsule_time())){
            helper.setGone(R.id.img_clock,false);
        }else {
            helper.setVisible(R.id.img_clock,true);
        }
        if (Means.isEmpty(item.getFields().getCapsule_person())){
            helper.setGone(R.id.img_people,false);
        }else {
            helper.setVisible(R.id.img_people,true);
        }
        if (Means.isEmpty(item.getFields().getCapsule_location())){
            helper.setGone(R.id.img_location,false);
        }else {
            helper.setVisible(R.id.img_location,true);
        }
        if (Means.isEmpty(item.getFields().getCapsule_image())){
            helper.setGone(R.id.img_photo,false);
        }else {
            helper.setVisible(R.id.img_photo,true);
        }
        concapsule.setOnClickListener(v -> detailCapsuleItem(item));
        imgmenu.setOnClickListener(v -> showCenterListMenu(helper, item));
    }

    private void showCenterListMenu(@NonNull BaseViewHolder helper, CapsulesResponse.ListBean item){
        new XPopup.Builder(helper.itemView.getContext())
                .asCenterList("",new String[]{"详情","修改","删除","分享"},
                        (i, s) -> {
                            if (i == 0){
                                detailCapsuleItem(item);
                            }else if (i == 1){
                                editCapsuleItem(item);
                            }else if (i == 2){
                                if (null != mlistener){
                                    mlistener.onDeleteMenuItemClick(item);
                                }
                            }else if (i == 3){
                                shareCapsuleItem(item);
                            }else {

                            }
                        })
                .show();
    }


    private void detailCapsuleItem(CapsulesResponse.ListBean item){
        Bundle bundle=new Bundle();
        bundle.putSerializable(RouteKey.CAPSULE_INFO, Means.changefromNetbean(item));
        ARouter.getInstance().build(RouteUrl.Url_NoteinfoActivity).withBundle(RouteKey.NOTE_INFO,bundle).navigation();
    }

    private void editCapsuleItem(CapsulesResponse.ListBean item){
        Bundle bundle=new Bundle();
        bundle.putInt(RouteKey.CAPSULE_TYPE,10);
        bundle.putSerializable(RouteKey.CAPSULE_INFO,Means.changefromNetbean(item));
        ARouter.getInstance().build(RouteUrl.Url_EditActivity).withBundle(RouteKey.EDIT_DATA,bundle).navigation();
    }

    private void shareCapsuleItem(CapsulesResponse.ListBean item){

    }

    private String showCreateTimeDay(String createtime){
        Calendar calendar = TimeUtils.getDataFromUTCTimeZone(createtime);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10){
            return "0" + day;
        }
        return String.valueOf(day);
    }

    private String showCreateTimeMonth(String createtime){
        Calendar calendar = TimeUtils.getDataFromUTCTimeZone(createtime);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10){
            return "0" + month + "月";
        }
        return month + "月";
    }

    private String showCreateTimeWeekDay(String createtime){
        String[] weeks = {"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar calendar = TimeUtils.getDataFromUTCTimeZone(createtime);
        int wd = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weeks[wd];
    }

    private int getImageTypeRSId(String type){
        if (type.equals(Means.STR_WORK)){
            return R.drawable.icon_work;
        }else if (type.equals(Means.STR_STUDY)){
            return R.drawable.icon_study;
        }else if (type.equals(Means.STR_DIARY)){
            return R.drawable.icon_diary;
        }else if (type.equals(Means.STR_LIVE)){
            return R.drawable.icon_live;
        }else if (type.equals(Means.STR_TRAYEL)){
            return R.drawable.icon_travel;
        }else {
            return R.drawable.icon_study;
        }
    }

    public interface OnMenuClickListener{
        void onDeleteMenuItemClick(CapsulesResponse.ListBean bean);
    }
}
