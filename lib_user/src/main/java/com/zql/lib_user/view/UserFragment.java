package com.zql.lib_user.view;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.comm.net.HttpData;
import com.zql.comm.net.OnHttpRequestListener;
import com.zql.comm.netbean.request.LoginRequest;
import com.zql.comm.netbean.response.LoginResponse;
import com.zql.lib_user.R;

public class UserFragment extends BaseLifecycleFragment<UserPresenter> implements UserContract.view {

    private TextView mText;

    private Button mButton;

    private HttpData mHttData;

    @Override
    protected void initView(View view) {
        mButton = find(R.id.button_login);
        mText = find(R.id.text_user);
        mHttData = new HttpData();
        mButton.setOnClickListener(v ->
            mHttData.Login(new LoginRequest("zqlzql","zqlzql"),new OnHttpRequestListener<LoginResponse>() {
            @Override
            public void onHttpRequestSuccess(LoginResponse result) {
                mText.setText(result.toString());
            }

            @Override
            public void onHttpRequestFailed(String error) {
                Log.d("zzzzzzzzzzzz","failed--->" + error);
            }
        }));
    }

    @Override
    protected UserPresenter getPresenter() {
        return new UserPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mHttData){
            mHttData.Destory();
        }
    }
}
