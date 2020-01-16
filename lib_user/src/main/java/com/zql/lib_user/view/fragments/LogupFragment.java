package com.zql.lib_user.view.fragments;

import android.view.View;
import android.widget.EditText;

import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.comm.net.HttpData;
import com.zql.comm.net.OnHttpRequestListener;
import com.zql.comm.netbean.request.LogupRequest;
import com.zql.comm.netbean.response.LoginResponse;
import com.zql.lib_user.R;
import com.zql.lib_user.view.UserFragment;

public class LogupFragment extends BaseLifecycleFragment<LogupPresenter> implements LogupContract.view{

    private HttpData mHttpData;

    private EditText mEditUser, mEditPass, mEditEmail, mEditSex;



    public static LogupFragment newInstance(){
        return new LogupFragment();
    }

    @Override
    protected void initView(View view) {
        mEditUser = find(R.id.edit_user);
        mEditPass = find(R.id.edit_pass);
        mEditEmail = find(R.id.edit_email);
        mEditSex = find(R.id.edit_sex);


        mHttpData = new HttpData();
        find(R.id.text_logup).setOnClickListener(v -> mHttpData.Logup(new LogupRequest()
                                .setUsername("zqlswc")
                                .setPassword("zqlswc")
                                .setEmail("zqlswc@qq.com")
                                .setSex("女"),
                                new OnHttpRequestListener<LoginResponse>() {
                    @Override
                    public void onHttpRequestSuccess(LoginResponse result) {

                    }

                    @Override
                    public void onHttpRequestFailed(String error) {

                    }
            }));
        find(R.id.text_cancle).setOnClickListener(v -> {
            if (getParentFragment() instanceof UserFragment){
                ((UserFragment) getParentFragment()).initLoginFragment();
            }
        });
    }



    @Override
    protected LogupPresenter getPresenter() {
        return new LogupPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_logup;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mHttpData){
            mHttpData.Destory();
        }
    }
}
