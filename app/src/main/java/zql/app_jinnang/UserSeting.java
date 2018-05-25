package zql.app_jinnang;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class UserSeting extends Application implements UserSetingImp {
    public SharedPreferences sharedPreferences;
    @Override
    public void onCreate() {
        super.onCreate();
        initSharedPreference();
    }

    private void initSharedPreference(){
        sharedPreferences=getSharedPreferences("config_jinnang",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
    }

    @Override
    public String getpassswordfromSeting() {
        return sharedPreferences.getString("password","null").toString();
    }

    @Override
    public String getquestionfromSeting() {
        return sharedPreferences.getString("question","null").toString();
    }

    @Override
    public void putpasswordonSeting(String password) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("password",password);
        editor.commit();
        editor.apply();
    }

    @Override
    public void putquestiononSeting(String question) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("question",question);
        editor.commit();
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
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("color",color);
        editor.commit();
    }

    @Override
    public List<Integer> getcurrentColor() {
        List<Integer> mlist=new ArrayList<>();
        switch (sharedPreferences.getInt("color",0)){
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

    @Override
    public int getcurrentColorNum() {
        return sharedPreferences.getInt("color",0);
    }
}
