package com.is.lib_util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Create by Totoro
 * 2019-08-03 19:57
 **/
public class AnimatorUtils {

    public static ObjectAnimator getAlphaAnimation(View view, int repeatMode) {
        ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        alphaObjectAnimator.setDuration(1000);
        alphaObjectAnimator.setRepeatMode(repeatMode);
        alphaObjectAnimator.setRepeatCount(-1);
        return alphaObjectAnimator;
    }


    public static AlphaAnimation getAlphaAnimation(View view) {
        AlphaAnimation alpha = new AlphaAnimation(1f, 0f);
        alpha.setDuration(700);
        alpha.setRepeatMode(Animation.REVERSE);
        alpha.setRepeatCount(-1);
        return alpha;
    }

    public static ObjectAnimator rotationAnimation(View view, float from, float end, int duration) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", from, end);
        rotation.setDuration(duration);
        rotation.setRepeatCount(ValueAnimator.INFINITE);
        rotation.setRepeatMode(ValueAnimator.RESTART);
        return rotation;
    }


    public static void rotateYAnimationReset(final View beforeView, final View afterView) {
        final ObjectAnimator inVisToVis = ObjectAnimator.ofFloat(afterView, "rotationY", -90f, 0f);
        ObjectAnimator visToInVis = ObjectAnimator.ofFloat(beforeView, "rotationY", 0f, 90f);
        visToInVis.setDuration(500);
        inVisToVis.setDuration(500);
        visToInVis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator anim) {
                beforeView.setVisibility(View.GONE);
                inVisToVis.start();
                afterView.setVisibility(View.VISIBLE);
            }
        });
        visToInVis.start();
    }


}
