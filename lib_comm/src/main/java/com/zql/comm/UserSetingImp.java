package com.zql.comm;

import java.util.List;

public interface UserSetingImp {
    public void putpasswordonSeting(String password);
    public String getpassswordfromSeting();
    public void putquestiononSeting(String question);
    public String getquestionfromSeting();
    public boolean iscurrentthePassword(String password);
    public boolean iscurrentthQuestion(String qusetion);
    public boolean isnullthepassword();
    public boolean isnullthequestion();
    public void putcurrentColor(int color);
    public List<Integer> getcurrentColor();
    public int getcurrentColorNum();
}
