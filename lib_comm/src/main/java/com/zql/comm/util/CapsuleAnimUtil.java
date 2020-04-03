package com.zql.comm.util;

import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class CapsuleAnimUtil {

    /**
     * 碎片反转动画
     */
    public static void begainErrorAnim(View view) {
        view.post(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .repeat(0)
                        .playOn(view);
            }
        });
    }
}
