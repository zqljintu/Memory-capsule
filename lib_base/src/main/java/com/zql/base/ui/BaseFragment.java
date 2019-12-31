package com.zql.base.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.zql.base.BaseApplication;
import com.zql.base.event.EventBusUtil;
import com.zql.base.utils.HandlerUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Create by Totoro
 * 2019-11-07 16:56
 **/
public abstract class BaseFragment extends Fragment implements HandlerUtils.OnReceiveMessageListener {


    protected ArrayList<Animator> mAnimators = new ArrayList<>();

    protected ArrayList<AnimatorSet> mAnimatorSets = new ArrayList<>();

    private View parentView;

    //用于发送延时任务
    protected HandlerUtils.HandlerHolder mHandler = new HandlerUtils.HandlerHolder(this);

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBusUtil.register(this);
    }

    /**
     * @param o 可以重写这个方法来获取event数据 强转o类型
     */
    @Subscribe
    public void onEvent(Object o) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayoutId(), container, false);
        parentView = view;
        return view;
    }

    protected abstract int getContentLayoutId();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroyRes();
    }

    /**
     * 简化findViewById
     *
     * @param id  控件id
     * @param <T> 控件类型
     * @return view
     */
    public <T extends View> T find(int id) {
        return parentView.findViewById(id);
    }

    @Override
    public void handlerMessage(Message msg) {

    }

    /**
     * @param lottieAnimationView 取消lottie动画资源
     */
    protected void cancelLottieAnim(LottieAnimationView lottieAnimationView) {
        if (lottieAnimationView != null) {
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
    public void onDestroy() {
        super.onDestroy();
        EventBusUtil.unRegister(this);
        destroyRes();
        BaseApplication.getApplication().whatObj(this);
    }

    private void destroyRes() {
        //移除延时任务
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        for (Animator mAnimator : mAnimators) {
            if (mAnimator != null) {
                mAnimator.removeAllListeners();
                mAnimator.cancel();
                mAnimator = null;
            }
        }

        for (AnimatorSet mAnimatorSet : mAnimatorSets) {
            if (mAnimatorSet != null) {
                mAnimatorSet.removeAllListeners();
                mAnimatorSet.cancel();
                mAnimatorSet = null;
            }
        }


    }


}
