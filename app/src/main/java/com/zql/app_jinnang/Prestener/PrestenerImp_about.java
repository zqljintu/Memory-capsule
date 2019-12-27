package com.zql.app_jinnang.Prestener;

/**
 * Created by 尽途 on 2018/5/12.
 */

public interface PrestenerImp_about {
    public void showthecurrentpasswordOnAboutactivity();
    public boolean isnullthepasswordfromSeting();//判断密码是不是初始化“null”
    public boolean isnullthequestionfromSeting();//判断密保问题是不是初始化“null”
    public void putpasswordandquestionOnSeting(String password,String question);//写入密码和密保到设置文件中
    public boolean iscurrentthepasswordfromSeting(String password);//判断是否为当前密码
    public boolean iscurrentthequestionfromSeting(String question);//判断是否为当前密保
    public void putpasswordOnSeting(String password);//单独修改当前密码
    public void putquestionOnSeting(String question);//单独修改当前密保
    public void putcurrentcolorOnSeting(int color);//改变主题色
    public int getcurrentcolorNumfromSeting();//获取最新的颜色代码
    public void setBackgroundcolorfromSeting();//设置主题色
}
