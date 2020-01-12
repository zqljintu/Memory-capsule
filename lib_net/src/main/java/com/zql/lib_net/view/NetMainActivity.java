package com.zql.lib_net.view;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.data.CommData;
import com.zql.comm.net.HttpData;
import com.zql.comm.provider.ICapsuleProvider;
import com.zql.comm.provider.IUserProvider;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_net.R;
import com.zql.lib_net.adapter.VpAdapter;

import java.util.ArrayList;

@Route(path = RouteUrl.Url_NetMainActivity)
public class NetMainActivity extends BaseLifecycleActivity<NetMainPresenter> implements NetMainContract.view {

    private HttpData mHttpData;

    private ViewPager mViewPager;

    private BottomNavigationBar mBottomBar;

    private ArrayList<Fragment> mFragments = new ArrayList<>();;

    @Autowired(name = RouteUrl.Url_UserFragment)
    IUserProvider mUserProvider;

    @Autowired(name = RouteUrl.Url_CapsuleFragment)
    ICapsuleProvider mCapsuleProvider;

    private VpAdapter mVpAdapter;



    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_net_main;
    }

    @Override
    protected void initView() {
        mViewPager = find(R.id.net_viewpager);
        mBottomBar = find(R.id.net_bottombar);
        initNav();
        initVp();
        mHttpData = new HttpData();
        find(R.id.text_local).setOnClickListener(v -> {
                    CommData.setLocalVerson();
                    ARouter.getInstance().build(RouteUrl.Url_MainActivity).navigation();
                    finish();
                }
        );
    }

    private void initVp() {
        if (mCapsuleProvider != null && mCapsuleProvider.getCapsuleFragment() != null){
            mFragments.add(mCapsuleProvider.getCapsuleFragment());
        }

        if (mUserProvider != null && mUserProvider.getUserFragment() != null){
            mFragments.add(mUserProvider.getUserFragment());
        }

        mVpAdapter = new VpAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mVpAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initNav() {
        mBottomBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomBar.addItem(new BottomNavigationItem(R.drawable.ic_capsule,getString(R.string.net_capsule)));
        mBottomBar.addItem(new BottomNavigationItem(R.drawable.ic_user,getString(R.string.net_user)));
        mBottomBar.setActiveColor(R.color.colorFloatingButton);
        mBottomBar.setInActiveColor(R.color.colorNormal);
        mBottomBar.initialise();
        mBottomBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        mBottomBar.setPadding(0,0,0,0);

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
