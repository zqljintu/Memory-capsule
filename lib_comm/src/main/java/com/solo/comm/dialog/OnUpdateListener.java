package com.solo.comm.dialog;

/**
 * Create by Totoro
 * 2019-11-18 15:59
 **/
public interface OnUpdateListener {

    void onUpdate(String path, boolean isForce);

    void onClose(boolean isForce);
}
