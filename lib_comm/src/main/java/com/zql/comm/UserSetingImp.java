package com.zql.comm;

import java.util.List;

public interface UserSetingImp {

    void putpasswordonSeting(String password);
    String getpassswordfromSeting();
    void putquestiononSeting(String question);
    String getquestionfromSeting();
    boolean iscurrentthePassword(String password);
    boolean iscurrentthQuestion(String qusetion);
    boolean isnullthepassword();
    boolean isnullthequestion();
    void putcurrentColor(int color);
    List<Integer> getcurrentColor();
    int getcurrentColorNum();

}
