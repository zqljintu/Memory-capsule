package com.zql.lib_user.view.fragments;

import android.view.View;

import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.comm.net.HttpData;
import com.zql.comm.net.OnHttpRequestListener;
import com.zql.comm.netbean.request.LogupRequest;
import com.zql.comm.netbean.response.BaseResponse;
import com.zql.lib_user.R;

public class LogupFragment extends BaseLifecycleFragment<LogupPresenter> implements LogupContract.view{

    private HttpData mHttpData;



    public static LogupFragment newInstance(){
        return new LogupFragment();
    }

    @Override
    protected void initView(View view) {
        mHttpData = new HttpData();
        find(R.id.text_logup).setOnClickListener(v -> mHttpData.Logup(new LogupRequest()
                                .setUsername("zqlswb")
                                .setPassword("zqlswb")
                                .setEmail("zqlswb@qq.com")
                                .setSex("男"),
                                new OnHttpRequestListener<BaseResponse>() {
                    @Override
                    public void onHttpRequestSuccess(BaseResponse result) {

                    }

                    @Override
                    public void onHttpRequestFailed(String error) {

                    }
            }));
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
