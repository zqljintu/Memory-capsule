package com.zql.comm.live;

import android.content.Context;

import java.util.ArrayList;


public class LiveManager {

    private ArrayList<LiveIm> mLiveIms;

    private static LiveManager instance;

    public static LiveManager getInstance() {
        if (instance == null) {
            synchronized (LiveManager.class) {
                if (instance == null) {
                    instance = new LiveManager();
                }
            }
        }
        return instance;
    }


    private LiveManager() {
        mLiveIms = new ArrayList<>();
    }


    public void add(LiveIm im) {
        if (!mLiveIms.contains(im))
            mLiveIms.add(im);
    }


    public void run(Context context) {
        for (LiveIm mLiveIm : mLiveIms) {
            if (mLiveIm != null) {
                mLiveIm.destroy();
                mLiveIm.run(context);
            }
        }
    }


}
