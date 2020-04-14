package com.zql.lib_user.view.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;
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
import com.zql.comm.util.CapsuleAnimUtil;
import com.zql.lib_user.R;
import com.zql.lib_user.view.UserFragment;

import org.angmarch.views.NiceSpinner;
import org.apache.commons.lang3.event.EventUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class LogupFragment extends BaseLifecycleFragment<LogupPresenter> implements LogupContract.view{

    private HttpData mHttpData;

    private EditText mEditUser, mEditPass, mEditEmail;

    private NiceSpinner mSpinerSex;

    private EditText mEditPassConfirm;
    
    private EditText mEditUserImg;

    private TextView mTextSubmit;

    private TextView mTextCancle;

    private String mUserImg;

    private String mSex = "男";

    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;



    public static LogupFragment newInstance(){
        return new LogupFragment();
    }

    @Override
    protected void initView(View view) {
        mEditUser = find(R.id.edit_user);
        mEditPass = find(R.id.edit_pass);
        mEditUserImg = find(R.id.edit_userimg);
        mEditPassConfirm = find(R.id.edit_pass_confirm);
        mEditEmail = find(R.id.edit_email);
        mSpinerSex = find(R.id.spiner_sex);
        mTextCancle = find(R.id.text_cancle);
        mTextSubmit = find(R.id.text_logup_submit);
        List<String> data=new LinkedList<>(Arrays.asList("男","女"));
        mSpinerSex.attachDataSource(data);
        mHttpData = new HttpData();
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        mEditUserImg.setFocusable(false);
        mEditUserImg.setOnClickListener(v -> {
            initImgSelector();
        });
        initData();
    }

    private void initImgSelector() {
        ISListConfig config = new ISListConfig.Builder()
                // 是否多选, 默认true
                .multiSelect(false)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.GRAY)
                // “确定”按钮文字颜色
                .btnTextColor(Color.BLUE)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#7BD1CE"))
                // 返回图标ResId
                .backResId(R.drawable.ic_back)
                // 标题
                .title("头像")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(Color.parseColor("#7BD1CE"))
                // 裁剪大小。needCrop为true的时候配置
                .cropSize(1, 1, 200, 200)
                .needCrop(true)
                // 第一个是否显示相机，默认true
                .needCamera(false)
                // 最大选择图片数量，默认9
                .maxNum(1)
                .build();
        ISNav.getInstance().toListActivity(this, config, REQUEST_LIST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LIST_CODE && resultCode == RESULT_OK && data != null){
            List<String> pathList = data.getStringArrayListExtra("result");
            mUserImg = pathList.get(0);
            if (mEditUserImg != null){
                mEditUserImg.setText(mUserImg);
            }
            Log.d("zzzzzzzzzPac",pathList.get(0));
        }

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
            CapsuleAnimUtil.begainErrorAnim(mEditUser);
            return;
        }
        if (!RegularUtil.isCorrectUsername(mEditUser.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_username_error));
            CapsuleAnimUtil.begainErrorAnim(mEditUser);
            return;
        }
        if (TextUtils.isEmpty(mEditEmail.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_not_null));
            CapsuleAnimUtil.begainErrorAnim(mEditEmail);
            return;
        }
        if (!RegularUtil.isCorrectEmail(mEditEmail.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_useremail_error));
            CapsuleAnimUtil.begainErrorAnim(mEditEmail);
            return;
        }
        if (TextUtils.isEmpty(mEditPass.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_not_null));
            CapsuleAnimUtil.begainErrorAnim(mEditPass);
            return;
        }
        if (!RegularUtil.isCorrectPassword(mEditPass.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_userpass_error));
            CapsuleAnimUtil.begainErrorAnim(mEditPass);
            return;
        }
        if (TextUtils.isEmpty(mEditPass.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_not_null));
            CapsuleAnimUtil.begainErrorAnim(mEditPass);
            return;
        }
        if (!RegularUtil.isCorrectPassword(mEditPassConfirm.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_userpaser_error));
            CapsuleAnimUtil.begainErrorAnim(mEditPassConfirm);
            return;
        }
        if (!mEditPass.getText().toString().equals(mEditPassConfirm.getText().toString())){
            ToastUtil.showToast(getString(R.string.logup_pass_not_error));
            CapsuleAnimUtil.begainErrorAnim(mEditPass);
            CapsuleAnimUtil.begainErrorAnim(mEditPassConfirm);
            return;
        }
        LogupRequest request =   new LogupRequest()
                .setUsername(mEditUser.getText().toString())
                .setPassword(mEditPass.getText().toString())
                .setEmail(mEditEmail.getText().toString())
                .setSex(mSex);
        if (mUserImg != null){
            request.setmUserImg(mUserImg);
        }
        mHttpData.Logup(request,
                new OnHttpRequestListener<LoginResponse>() {
                    @Override
                    public void onHttpRequestSuccess(LoginResponse result) {
                        if (result.getCode() == LoginResponse.LOGUP_REPET){
                            ToastUtil.showToast(getString(R.string.logup_user_repet));
                        }else if (result.getCode() == LoginResponse.LOGUP_SUCCESS){
                            UserData.setUserIsLogin(true);
                            UserData.setUserName(mEditUser.getText().toString());
                            UserData.setUserSex(result.getSex());
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
