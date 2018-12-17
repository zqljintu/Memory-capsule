package zql.app_jinnang.View;

import android.app.Application;
import android.content.Context;

import java.util.List;

public interface EditActivityImp {
    public Context getbasecontext();//获取
    public Application getapplication();
    public int getStatement();//获取修改还是添加状态
    public void setbackgroundcolor(List<Integer>list);//修改背景色
}
