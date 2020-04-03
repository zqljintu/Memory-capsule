package com.zql.lib_net.view;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.jaeger.library.StatusBarUtil;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupAnimation;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.base.utils.ScreenUtil;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.data.CommData;
import com.zql.comm.data.UserData;
import com.zql.comm.net.HttpData;
import com.zql.comm.provider.ICapsuleProvider;
import com.zql.comm.provider.IUserProvider;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_net.R;
import com.zql.lib_net.adapter.VpAdapter;
import com.zql.lib_net.menu.TypeMenu;

import java.util.ArrayList;


@Route(path = RouteUrl.Url_NetMainActivity)
public class NetMainActivity extends BaseLifecycleActivity<NetMainPresenter> implements NetMainContract.view {

    private HttpData mHttpData;

    private ViewPager mViewPager;

    private ConstraintLayout mConCapsule, mConMore;

    private ImageView mImgCapsule, mImgMore;

    private TextView mTextCapsule, mTextMore;

    private AddFloatingActionButton mAdd;


    private ArrayList<Fragment> mFragments = new ArrayList<>();;

    @Autowired(name = RouteUrl.Url_UserFragment)
    IUserProvider mUserProvider;

    @Autowired(name = RouteUrl.Url_CapsuleFragment)
    ICapsuleProvider mCapsuleProvider;

    private VpAdapter mVpAdapter;

    private TextView mTextLocal;



    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_net_main;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this,Color.WHITE);
        mViewPager = find(R.id.net_viewpager);
        mAdd = find(R.id.fb_add);
        mTextLocal = find(R.id.text_local);
        initNav();
        initVp();
        mAdd.setOnClickListener(v -> initCenterMenu());
        mHttpData = new HttpData();
        mTextLocal.setOnClickListener(v -> {
                    CommData.setLocalVerson();
                    ARouter.getInstance().build(RouteUrl.Url_MainActivity).navigation();
                    finish();
                }
        );
    }

    private void initCenterMenu() {
        if (!UserData.getUserIsLogin()){
            mViewPager.setCurrentItem(1);
            return;
        }
        int offy = (int) (ScreenUtil.getRealHeight(this) * 0.6f);
        new XPopup.Builder(getContext())
                .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)
                .isCenterHorizontal(true)
                .offsetY(offy)
                .asCustom(new TypeMenu(this,pos -> {startEditActivity(pos, null); }))
                .show();
    }

    private void startEditActivity(int type, NoteBean noteBean){
        Bundle bundle=new Bundle();
        bundle.putInt("type",type);
        if (noteBean!=null){
            bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
        }
        ARouter.getInstance().build(RouteUrl.Url_EditActivity).withBundle("data",bundle).navigation();
    }
    private void initVp() {
        if (mCapsuleProvider != null && mCapsuleProvider.getCapsuleFragment() != null){
            mFragments.add(mCapsuleProvider.getCapsuleFragment());
        }

        if (mUserProvider != null && mUserProvider.getUserFragment() != null){
            mFragments.add(mUserProvider.getUserFragment());
        }

        mVpAdapter = new VpAdapter(getSupportFragmentManager(), mFragments);
        initBottomMenu(mViewPager.getCurrentItem());
        mViewPager.setAdapter(mVpAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initBottomMenu(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private void initNav() {
        mConCapsule = find(R.id.con_capsule);
        mConMore = find(R.id.con_more);
        mImgCapsule = find(R.id.img_capsule);
        mImgMore = find(R.id.img_more);
        mTextCapsule = find(R.id.text_capsule);
        mTextMore = find(R.id.text_more);
        mConCapsule.setOnClickListener(v -> {
            mViewPager.setCurrentItem(0);
            initBottomMenu(0);
        });
        mConMore.setOnClickListener(v -> {
            mViewPager.setCurrentItem(1);
            initBottomMenu(1);
        });
    }

    private void initBottomMenu(int pos){
        if (pos > 1) return;
        if (pos == 0){
            mImgCapsule.setColorFilter(getResources().getColor(R.color.colorFloatingButton));
            mTextCapsule.setTextColor(getResources().getColor(R.color.colorFloatingButton));
            mImgMore.setColorFilter(getResources().getColor(R.color.colorMenuBg));
            mTextMore.setTextColor(getResources().getColor(R.color.colorMenuBg));
            mTextLocal.setVisibility(View.GONE);
        }else {
            mImgCapsule.setColorFilter(getResources().getColor(R.color.colorMenuBg));
            mTextCapsule.setTextColor(getResources().getColor(R.color.colorMenuBg));
            mImgMore.setColorFilter(getResources().getColor(R.color.colorFloatingButton));
            mTextMore.setTextColor(getResources().getColor(R.color.colorFloatingButton));
            mTextLocal.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mHttpData){
            mHttpData.Destory();
        }
    }

    @Override
    protected NetMainPresenter getPresenter() {
        return new NetMainPresenter(this);
    }
}
