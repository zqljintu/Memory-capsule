package com.zql.lib_capsule.view;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.base.utils.ToastUtil;
import com.zql.comm.data.UserData;
import com.zql.comm.netbean.response.CapsulesResponse;
import com.zql.lib_capsule.R;
import com.zql.lib_capsule.adapter.NetCapsuleAdapter;

import java.util.ArrayList;
import java.util.List;

public class CapsuleFragment extends BaseLifecycleFragment<CapsulePresenter> implements CapsuleContract.view {

    private RecyclerView mRecyclerview;

    private NetCapsuleAdapter mNetCapsuleAdapter;

    private List<CapsulesResponse.ListBean> mData = new ArrayList<>();

    @Override
    protected void initView(View view) {
        mRecyclerview = find(R.id.recycler_net);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mNetCapsuleAdapter = new NetCapsuleAdapter(R.layout.item_capsule, mData);
        mRecyclerview.setAdapter(mNetCapsuleAdapter);
        if (UserData.getUserIsLogin()){
            mPresenter.loadCapsuleDataFromService();
        }else {
            ToastUtil.showToast("未登录");
        }
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
    public void setCapsuleDataToView(CapsulesResponse data) {
        mData.clear();
        mData.addAll(data.getList());
        mNetCapsuleAdapter.notifyDataSetChanged();
    }
}
