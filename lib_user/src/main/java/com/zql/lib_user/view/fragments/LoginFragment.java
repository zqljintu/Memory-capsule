package com.zql.lib_user.view.fragments;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zql.base.event.EventBusUtil;
import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.base.utils.ToastUtil;
import com.zql.comm.bean.MessageEvent;
import com.zql.comm.data.UserData;
import com.zql.comm.net.HttpData;
import com.zql.comm.net.OnHttpRequestListener;
import com.zql.comm.netbean.request.LoginRequest;
import com.zql.comm.netbean.response.LoginResponse;
import com.zql.lib_user.R;
import com.zql.lib_user.view.UserFragment;

public class LoginFragment extends BaseLifecycleFragment<LoginPresenter> implements LoginContract.view {


    private TextView mText;

    private TextView mTextLogup;

    private Button mButton;

    private HttpData mHttData;

    private EditText mEditUser;

    private EditText mEditPass;

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    protected void initView(View view) {
        mButton = find(R.id.button_login);
        mText = find(R.id.text_user);
        mTextLogup = find(R.id.text_logup);
        initEdit();
        mHttData = new HttpData();
        mTextLogup.setOnClickListener(v -> {
            if (getParentFragment() instanceof UserFragment){
                if (null != getParentFragment()){
                    ((UserFragment)getParentFragment()).initLogupFragment();
                }
            }
        });
        mButton.setOnClickListener(v -> initLogin(mEditUser.getText().toString(),
                mEditPass.getText().toString()));
    }

    private void initLogin(String user, String pass) {
        if (TextUtils.isEmpty(user)){
            ToastUtil.showToast(getString(R.string.user_notnull));
            return;
        }
        if (TextUtils.isEmpty(pass)){
            ToastUtil.showToast(getString(R.string.pass_notnull));
            return;
        }
        mHttData.Login(new LoginRequest(user,pass),new OnHttpRequestListener<LoginResponse>() {
            @Override
            public void onHttpRequestSuccess(LoginResponse result) {
                mText.setText(result.toString());
                if (result.getCode() == LoginResponse.LOGIN_SUCCESS){
                    UserData.setUserIsLogin(true);
                    UserData.setUserLoginToken(result.getToken());
                    ToastUtil.showToast(getString(R.string.logup_success));
                    EventBusUtil.postEvent(new MessageEvent(MessageEvent.UPDATE_NETCAPSULE));
                    if (null != getParentFragment()){
                        if (getParentFragment() instanceof UserFragment){
                            ((UserFragment)getParentFragment()).initLogoutFragment();
                        }
                    }
                }else {
                    ToastUtil.showToast(getString(R.string.logup_fail));
                }
            }

            @Override
            public void onHttpRequestFailed(String error) {
                mText.setText(error);
                Log.d("zzzzzzzzzzzz","failed--->" + error);
            }
        });
    }

    private void initEdit() {
        mEditUser = find(R.id.edit_user);
        mEditPass = find(R.id.edit_pass);
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mHttData){
            mHttData.Destory();
        }
    }
}
