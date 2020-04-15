package com.zql.lib_capsule.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.base.utils.ToastUtil;
import com.zql.comm.bean.MessageEvent;
import com.zql.comm.data.UserData;
import com.zql.comm.netbean.response.CapsulesResponse;
import com.zql.lib_capsule.R;
import com.zql.lib_capsule.adapter.NetCapsuleAdapter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class CapsuleFragment extends BaseLifecycleFragment<CapsulePresenter> implements CapsuleContract.view {

    private RecyclerView mRecyclerview;

    private NetCapsuleAdapter mNetCapsuleAdapter;

    private SmartRefreshLayout mRefreshLayout;

    private List<CapsulesResponse.ListBean> mData = new ArrayList<>();

    @Override
    protected void initView(View view) {
        mRecyclerview = find(R.id.recycler_net);
        mRefreshLayout = find(R.id.mRefreshLayout);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mNetCapsuleAdapter = new NetCapsuleAdapter(R.layout.item_capsule, mData, bean -> mPresenter.deleteCapsuleFromService(bean.getPk()));
        mRecyclerview.setAdapter(mNetCapsuleAdapter);
        mPresenter.loadCapsuleDataFromService(false);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.loadCapsuleDataFromService(true);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.loadCapsuleDataFromService(false);
            }
        });
    }

    @Override
    protected CapsulePresenter getPresenter() {
        return new CapsulePresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_capsule;
    }

    @Override
    public void setCapsuleDataToView(boolean more,CapsulesResponse data) {
        closeProgress();
        if (more){
            mData.addAll(data.getList());
        }else {
            mData.clear();
            mData.addAll(data.getList());
        }
        mNetCapsuleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        ToastUtil.showToast(message);
    }

    @Override
    public void closeProgress() {
        if (mRefreshLayout != null){
            mRefreshLayout.finishLoadMore();
            mRefreshLayout.finishRefresh();
        }
    }

    private void clearCapsuleData(){
        mData.clear();
        mNetCapsuleAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event){
        if (event.getMessageevent() == MessageEvent.UPDATE_NETCAPSULE){
            mPresenter.loadCapsuleDataFromService(false);
        }else if (event.getMessageevent() == MessageEvent.UPDATE_LOGOUT){
            clearCapsuleData();
        }
    }
}
