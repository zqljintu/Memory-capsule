package com.zql.lib_capsule.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.comm.data.UserData;
import com.zql.comm.net.HttpData;
import com.zql.comm.net.OnHttpRequestListener;
import com.zql.comm.netbean.request.LoginRequest;
import com.zql.comm.netbean.response.CapsulesResponse;
import com.zql.lib_capsule.R;

public class CapsuleFragment extends BaseLifecycleFragment<CapsulePresnter> implements CapsuleContract.view {

    private Button mButtonGet;

    private TextView mText;

    private HttpData mHttpdata;



    @Override
    protected void initView(View view) {
        mText = find(R.id.capsule);
        mHttpdata = new HttpData();
        mButtonGet = find(R.id.getcapsule);
        mButtonGet.setOnClickListener(v -> {
            mHttpdata.LoadCapsule(new LoginRequest(UserData.getUserName(), UserData.getUserPass()),
                    new OnHttpRequestListener<CapsulesResponse>() {
                        @Override
                        public void onHttpRequestSuccess(CapsulesResponse result) {
                            mText.setText(result.toString());
                        }

                        @Override
                        public void onHttpRequestFailed(String error) {

                        }
                    });
        });
    }

    @Override
    protected CapsulePresnter getPresenter() {
        return new CapsulePresnter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_capsule;
    }
}
