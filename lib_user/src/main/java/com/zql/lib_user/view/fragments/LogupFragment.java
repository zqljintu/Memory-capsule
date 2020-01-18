package com.zql.lib_user.view.fragments;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zql.base.event.EventBusUtil;
import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.base.utils.RegularUtil;
import com.zql.base.utils.ToastUtil;
import com.zql.comm.bean.MessageEvent;
import com.zql.comm.data.UserData;
import com.zql.comm.net.HttpData;
import com.zql.comm.net.OnHttpRequestListener;
import com.zql.comm.netbean.request.LogupRequest;
import com.zql.comm.netbean.response.LoginResponse;
import com.zql.lib_user.R;
import com.zql.lib_user.view.UserFragment;

import org.angmarch.views.NiceSpinner;
import org.apache.commons.lang3.event.EventUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LogupFragment extends BaseLifecycleFragment<LogupPresenter> implements LogupContract.view{

    private HttpData mHttpData;

    private EditText mEditUser, mEditPass, mEditEmail;

    private NiceSpinner mSpinerSex;

    private EditText mEditPassConfirm;

    private TextView mTextSubmit;

    private TextView mTextCancle;

    private String mSex = "男";



    public static LogupFragment newInstance(){
        return new LogupFragment();
    }

    @Override
    protected void initView(View view) {
        mEditUser = find(R.id.edit_user);
        mEditPass = find(R.id.edit_pass);
        mEditPassConfirm = find(R.id.edit_pass_confirm);
        mEditEmail = find(R.id.edit_email);
        mSpinerSex = find(R.id.spiner_sex);
        mTextCancle = find(R.id.text_cancle);
        mTextSubmit = find(R.id.text_logup_submit);
        List<String> data=new LinkedList<>(Arrays.asList("男","女"));
        mSpinerSex.attachDataSource(data);
        mHttpData = new HttpData();
        initData();
    }

    private void initData() {
        mTextSubmit.setOnClickListener(v -> initSubmit());
        mTextCancle.setOnClickListener(v -> {
            if (getParentFragment() instanceof UserFragment){
                ((UserFragment) getParentFragment()).initLoginFragment();
            }
        });
        mSpinerSex.addOnItemClickListener((parent, view, position, id) -> {
            if (position == 0){
                mSex = "男";
            }else {
                mSex = "女";
            }
        });
    }

    private void initSubmit() {
        if (TextUtils.isEmpty(mEditUser.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_not_null));
            return;
        }
        if (!RegularUtil.isCorrectUsername(mEditUser.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_username_error));
            return;
        }
        if (TextUtils.isEmpty(mEditEmail.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_not_null));
            return;
        }
        if (!RegularUtil.isCorrectEmail(mEditEmail.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_useremail_error));
            return;
        }
        if (TextUtils.isEmpty(mEditPass.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_not_null));
            return;
        }
        if (!RegularUtil.isCorrectPassword(mEditPass.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_userpass_error));
            return;
        }
        if (TextUtils.isEmpty(mEditPass.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_not_null));
            return;
        }
        if (!RegularUtil.isCorrectPassword(mEditPassConfirm.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_userpaser_error));
            return;
        }
        if (!mEditPass.getText().toString().equals(mEditPassConfirm.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_pass_not_error));
            return;
        }
        mHttpData.Logup(new LogupRequest()
                        .setUsername(mEditUser.getText().toString())
                        .setPassword(mEditPass.getText().toString())
                        .setEmail(mEditEmail.getText().toString())
                        .setSex(mSex),
                new OnHttpRequestListener<LoginResponse>() {
                    @Override
                    public void onHttpRequestSuccess(LoginResponse result) {
                        if (result.getCode() == LoginResponse.LOGUP_REPET){
                            ToastUtil.showToast(getString(R.string.logup_user_repet));
                        }else if (result.getCode() == LoginResponse.LOGUP_SUCCESS){
                            UserData.setUserIsLogin(true);
                            UserData.setUserLoginToken(result.getToken());
                            EventBusUtil.postEvent(new MessageEvent(MessageEvent.UPDATE_NETCAPSULE));
                            ToastUtil.showToast(getString(R.string.logup_success));
                            if (getParentFragment() != null){
                                if (getParentFragment() instanceof UserFragment){
                                    ((UserFragment) getParentFragment()).initLogoutFragment();
                                }
                            }
                        }else {
                            ToastUtil.showToast(getString(R.string.logup_failed));
                        }
                    }

                    @Override
                    public void onHttpRequestFailed(String error) {

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
