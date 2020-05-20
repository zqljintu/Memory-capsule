package com.zql.comm.route;

/**
 * 2019-11-11 11:05
 **/
public interface RouteKey {
    String CAPSULE_INFO = "capsule_info";
    String NOTE_INFO = "note_info";
    String EDIT_DATA = "edit_data";
    String CAPSULE_TYPE = "capsule_type";

    int CAPSULE_WORK = 0;
    int CAPSULE_STUDY = 1;
    int CAPSULE_LIVE = 2;
    int CAPSULE_DIARY = 3;
    int CAPSULE_TRAVEL = 4;
}
