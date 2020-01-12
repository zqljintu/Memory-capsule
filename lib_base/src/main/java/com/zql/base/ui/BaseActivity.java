package com.zql.base.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zql.base.BaseApplication;
import com.zql.base.event.EventBusUtil;
import com.zql.base.utils.HandlerUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


public abstract class BaseActivity extends AppCompatActivity implements HandlerUtils.OnReceiveMessageListener {

    protected ArrayList<Animator> mAnimators = new ArrayList<>();

    protected ArrayList<AnimatorSet> mAnimatorSets = new ArrayList<>();

    //用于发送延时任务
    protected HandlerUtils.HandlerHolder mHandler = new HandlerUtils.HandlerHolder(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        EventBusUtil.register(this);
        ARouter.getInstance().inject(this);
    }

    protected abstract int getContentLayoutId();

    /**
     * 简化findViewById
     *
     * @param id  控件id
     * @param <T> 控件类型
     * @return view
     */
    public <T extends View> T find(int id) {
        return findViewById(id);
    }

    /**
     * @param lottieAnimationView 取消lottie动画资源
     */
    protected void cancelLottieAnim(LottieAnimationView lottieAnimationView) {
        if (lottieAnimationView != null) {
            lottieAnimationView.removeAllAnimatorListeners();
            lottieAnimationView.cancelAnimation();
        }
    }

    /**
     * @param animator 取消动画资源
     */
    protected void cancelAnim(Animator animator) {
        if (animator != null) {
            animator.removeAllListeners();
            animator.cancel();
        }
    }


    @Override
    public void handlerMessage(Message msg) {

    }

    /**
     * @param o 可以重写这个方法来获取event数据 强转o类型
     */
    @Subscribe
    public void onEvent(Object o) {

    }


    @Override
    protected void onDestroy() {
        //移除延时任务
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        EventBusUtil.unRegister(this);
        for (Animator mAnimator : mAnimators) {
            mAnimator.removeAllListeners();
            mAnimator.cancel();
            mAnimator = null;
        }
        mAnimators.clear();
        mAnimators = null;

        for (AnimatorSet mAnimatorSet : mAnimatorSets) {
            mAnimatorSet.removeAllListeners();
            mAnimatorSet.cancel();
            mAnimatorSet = null;
        }
        mAnimatorSets.clear();
        mAnimatorSets = null;

        super.onDestroy();

        BaseApplication.getApplication().whatObj(this);

    }

    public Context getContext() {
        return this;
    }


    protected static void immersive(Activity context) {
        Window window = context.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色透明
            window.setStatusBarColor(Color.TRANSPARENT);
            int visibility = window.getDecorView().getSystemUiVisibility();
            //布局内容全屏展示
            visibility = visibility | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            //隐藏虚拟导航栏
            //visibility = visibility or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            //防止内容区域大小发生变化
            visibility = visibility | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(visibility);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
