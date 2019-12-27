package com.solo.comm.dao;

import com.solo.comm.CommApplication;


import java.util.List;

public class GameDBUtils {

    private static GameBeanDao getInstance(){
        return CommApplication.getDaoSession().getGameBeanDao();
    }

    /**
     * 获取全部应用数据
     */

    public synchronized static List<GameBean> queryAllGameAppData(){
        return getInstance().queryBuilder().build().list();
    }

    /**
     * 插入一个新的应用数据
     */
    public synchronized static void insertNewGameApp(GameBean gameBean){
        List<GameBean> games = getInstance().queryBuilder().where(GameBeanDao.Properties.PackageName.eq(gameBean.getPackageName())).build().list();
        if (games.size() == 0){
            getInstance().insert(gameBean);
        }
    }

    /**
     * 判断应用有没有存在数据库中
     */
    public synchronized static boolean isExceting(String packagename){
        List<GameBean> games = getInstance().queryBuilder().where(GameBeanDao.Properties.PackageName.eq(packagename)).build().list();
        if (games.size() == 0){
            return false;
        }
        return true;
    }
}
