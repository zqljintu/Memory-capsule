package com.zql.comm;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mmkv.MMKV;
import com.zql.base.BaseApplication;
import com.zql.comm.bean.DaoMaster;
import com.zql.comm.bean.DaoSession;
import com.zql.comm.data.CommData;

import java.util.ArrayList;
import java.util.List;

public class UserSeting extends BaseApplication implements UserSetingImp {

    public SharedPreferences sharedPreferences;

    private static BaseApplication mApplication;

    private SQLiteDatabase db,db_secret;

    private static DaoSession daoSession,daoSession_secret;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();
        ARouter.init(this);//初始化阿里路由
        MMKV.initialize(this);//初始化MMKV写入工具
        initSharedPreference();//初始化写入xml
        initGreendao();//初始化数据库
        initGreendao_serect();//初始化隐私数据库
        CrashReport.initCrashReport(getApplicationContext(),
                "cca6ad46fc",
                true);
    }

    void initGreendao(){//创建公开数据库
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"recluse-db",null);
        db=helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(db);
        daoSession=daoMaster.newSession();
    }
    void initGreendao_serect(){//创建私密数据库
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"serect-db",null);
        db_secret=helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(db_secret);
        daoSession_secret=daoMaster.newSession();
    }

    public static DaoSession getNoteDaoSession(){
        return daoSession;
    }

    public static DaoSession getNoteSeDaoSession(){
        return daoSession_secret;
    }

    @Override
    protected void init() {

    }

    @Override
    public void whatObj(Object o) {

    }

    private void initSharedPreference(){
        sharedPreferences=getSharedPreferences("config_jinnang",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
    }

    @Override
    public String getpassswordfromSeting() {
        return CommData.getUserPassword();
    }

    @Override
    public String getquestionfromSeting() {
        return CommData.getUserQuestion();
    }

    @Override
    public void putpasswordonSeting(String password) {
        CommData.setUserPassword(password);
    }

    @Override
    public void putquestiononSeting(String question) {
        CommData.setUserQuestion(question);
    }

    @Override
    public boolean iscurrentthePassword(String password) {
        if (password.equals(this.getpassswordfromSeting())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean iscurrentthQuestion(String qusetion) {
        if (qusetion.equals(this.getquestionfromSeting())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isnullthepassword() {
        if (this.getpassswordfromSeting().equals("null")){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isnullthequestion() {
        if (this.getquestionfromSeting().equals("null")){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void putcurrentColor(int color) {
        CommData.setUserColor(color);
    }

    @Override
    public List<Integer> getcurrentColor() {
        List<Integer> mlist=new ArrayList<>();
        switch (getcurrentColorNum()){
            case 0:
                mlist.add(0,getResources().getColor(R.color.colorFloatingButton));
                mlist.add(1,getResources().getColor(R.color.colorfirst));
                break;
            case 1:
                mlist.add(0,getResources().getColor(R.color.colorFloatingButton1));
                mlist.add(1,getResources().getColor(R.color.colorfirsr_1));
                break;
            case 2:
                mlist.add(0,getResources().getColor(R.color.colorFloatingButton2));
                mlist.add(1,getResources().getColor(R.color.colorfirst_2));
                break;
            case 3:
                mlist.add(0,getResources().getColor(R.color.colorFloatingButton3));
                mlist.add(1,getResources().getColor(R.color.colorfirst_3));
                break;
            case 4:
                mlist.add(0,getResources().getColor(R.color.colorFloatingButton4));
                mlist.add(1,getResources().getColor(R.color.colorfirst_4));
                break;
                default:
                    break;
        }
        return mlist;
    }

    public static BaseApplication getApplication() {
        return mApplication;
    }

    @Override
    public int getcurrentColorNum() {
        return CommData.getUserColor();
    }
}
