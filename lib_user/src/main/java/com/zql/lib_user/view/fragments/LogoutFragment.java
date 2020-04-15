package com.zql.lib_user.view.fragments;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zql.base.event.EventBusUtil;
import com.zql.base.ui.mvp.BaseLifecycleFragment;
import com.zql.comm.bean.MessageEvent;
import com.zql.comm.data.UserData;
import com.zql.comm.util.NetUtil;
import com.zql.lib_user.R;
import com.zql.lib_user.view.UserFragment;

public class LogoutFragment extends BaseLifecycleFragment<LogoutPresenter> implements LogoutContract.view {

    private ImageView mImageUser;

    private TextView mTextUser;

    private TextView mTextNickName;

    private TextView mTextUserTitle;


    public static LogoutFragment newInstance(){
        return new LogoutFragment();
    }


    @Override
    protected void initView(View view) {
        mImageUser = find(R.id.img_user);
        mTextUser = find(R.id.name_user);
        mTextNickName = find(R.id.text_nickname);
        mTextUserTitle = find(R.id.text_usertitle);
        mTextUser.setText(UserData.getUserName());
        if (TextUtils.isEmpty(UserData.getUserImgUrl())){
            if (UserData.getUserSex().equals("男")){
                mImageUser.setImageResource(R.drawable.user_man);
            }else {
                mImageUser.setImageResource(R.drawable.user_woman);
            }
        }else {
            Glide.with(this).load(NetUtil.getImageUrl(UserData.getUserImgUrl())).into(mImageUser);
        }
        mTextUserTitle.setText(UserData.getUserTitle());
        mTextNickName.setText(UserData.getUserNickname());
        find(R.id.text_logoutout).setOnClickListener(v -> {
            UserData.setUserIsLogin(false);
            UserData.setUserName("");
            UserData.setUserPass("");
            UserData.setUserLoginToken("");
            EventBusUtil.postEvent(new MessageEvent(MessageEvent.UPDATE_LOGOUT));
            if (getParentFragment() instanceof UserFragment){
                if (null != getParentFragment()){
                    ((UserFragment)getParentFragment()).initLoginFragment();
                }
            }
        });

        find(R.id.text_logoutreout).setOnClickListener(v -> {

        });
    }

    @Override
    protected LogoutPresenter getPresenter() {
        return new LogoutPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_logout;
    }
}
