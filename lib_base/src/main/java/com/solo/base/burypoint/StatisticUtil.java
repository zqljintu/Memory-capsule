package com.solo.base.burypoint;

import com.appsflyer.AppsFlyerLib;
import com.solo.base.BaseApplication;
import com.solo.base.BaseLogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Totoro
 * 2019-11-08 16:50
 **/
public class StatisticUtil {
    //启动页
    public static final String OPEN_FIRST = "Open_Click";//启动次数
    public static final String LANDINGPAGE_CLICK_FIRST = "landingpage_first_Click";//进入点击开始按钮
    public static final String HOME_SHOW = "Home_Show";//首頁展示次数

    //清理
    public static final String JUNK_CLICK = "JunkFiles_click";//首頁清理点击用户数*
    public static final String JUNKFILES_SHOW = "JunkFiles_show";//垃圾清理进程页展示次数
    public static final String JUNK_FIX_CLICK = "Junk_Fix_click";//垃圾清理点击clean按鈕*
    public static final String JUNK_END = "Junk_End";//清理結果页展示次数*


    //加速
    public static final String BOOST_CLICK = "Boost_click";//首頁加速点击用户数*
    public static final String BOOST_END = "Boost_End";//加速結果页*
    //杀毒
    public static final String SCAN_CLICK = "Scan_Click";//首頁杀毒点击用户数*
    public static final String SCAN_FIX_CLICK = "Scan_Fix_click";//一件修復按鈕*
    public static final String SCAN_RESULT = "Scan_Result";//杀毒結果页展示次数*
    //cpu
    public static final String CPU_CLICK = "CPU_click";//首頁cpu点击用户数*
    public static final String CPU_END = "Cpu_End";//清理結果页*

    //省电
    public static final String POWER_SAVER_CLICK = "Power_saver_click";//超级省电点击用户数
    public static final String POWER_SAVER_FIX_CLICK = "Power_saver_Fix_click";//省电按鈕
    public static final String POWER_SAVER_END = "Power_saver_End";//省电結果页

    //深度清理
    public static final String DEEPCLEAN_CLICK = "Deepclean_click";//首頁深度清理点击用户数*
    public static final String DEEPCLEAN_END = "DEEPCLEAN_End";//深度清理結果页*

    //网络加速
    public static final String WIFI_CLICK = "WIFI_click";//wifi点击用户数*
    public static final String WIFI_END = "WIFI_End";//wifi結果页*
    //图片管理
    public static final String PHOTO_MANAGER_CLICK = "photo_manager_click";//首图片管理点击用户数*
    public static final String PHOTO_MANAGER_END = "photo_manager_End";//图片管理結果页*

    //游戏加速
    public static final String GAME_BOOST_CLICK = "Game_boost_click";//游戏加速点击用户数*
    public static final String GAME_BOOST_END = "Game_boost_End";//游戏加速結果页*



    //ME
    public static final String ME_CLICK = "Me_click";//我的点击用户数


    //发送埋点数据
    public static void send(String msg) {
        BaseLogUtil.D("StatisticUtil", "send>>>>" + msg);
        Map<String, Object> eventValue = new HashMap<>();
        AppsFlyerLib.getInstance().trackEvent(BaseApplication.getApplication(), msg, eventValue);
    }
}
