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
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.zql.base.utils.TimeUtils;
import com.zql.comm.bean.Means;
import com.zql.comm.netbean.response.CapsulesResponse;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_capsule.R;

import java.util.Calendar;
import java.util.List;

public class NetCapsuleAdapter extends BaseQuickAdapter<CapsulesResponse.ListBean, BaseViewHolder> {


    public NetCapsuleAdapter(int layoutResId, @Nullable List<CapsulesResponse.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CapsulesResponse.ListBean item) {
        ConstraintLayout concenter = helper.itemView.findViewById(R.id.con_center);
        ImageView imgmenu = helper.itemView.findViewById(R.id.img_menu);
        helper.setText(R.id.text_content, item.getFields().getCapsule_content());
        helper.setText(R.id.text_day, showCreateTimeDay(item.getFields().getCapsule_create_time()));
        helper.setText(R.id.text_month, showCreateTimeMonth(item.getFields().getCapsule_create_time()));
        helper.setText(R.id.text_week_day, showCreateTimeWeekDay(item.getFields().getCapsule_create_time()));
        helper.setImageResource(R.id.img_type, getImageTypeRSId(item.getFields().getCapsule_type()));
        concenter.setOnClickListener(v -> detailCapsuleItem(item));
        imgmenu.setOnClickListener(v -> showCenterListMenu(helper, item));
    }

    private void showCenterListMenu(@NonNull BaseViewHolder helper, CapsulesResponse.ListBean item){
        new XPopup.Builder(helper.itemView.getContext())
                .asCenterList("",new String[]{"详情","修改","删除","分享"},
                        (i, s) -> {
                            if (i == 0){
                                detailCapsuleItem(item);
                            }else if (i == 1){

                            }else if (i == 2){

                            }else if (i == 3){

                            }else {

                            }
                        })
                .show();
    }


    private void detailCapsuleItem(CapsulesResponse.ListBean item){
        Bundle bundle=new Bundle();
        bundle.putSerializable("noteinfo", Means.changefromNetbean(item));
        ARouter.getInstance().build(RouteUrl.Url_NoteinfoActivity).withBundle("info",bundle).navigation();
    }

    private void editCapsuleItem(){

    }

    private void deleteCapsuleItem(){

    }

    private void shareCapsuleItem(){

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
        int wd = calendar.get(Calendar.DAY_OF_WEEK);
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
}
