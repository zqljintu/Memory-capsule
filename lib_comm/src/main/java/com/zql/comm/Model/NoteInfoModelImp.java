package com.zql.comm.Model;

import android.database.Cursor;


import com.zql.comm.bean.NoteBean;

import java.util.List;

import lecho.lib.hellocharts.model.SliceValue;

/**
 * Created by 尽途 on 2018/4/4.
 */

public interface NoteInfoModelImp {
    void InsertNotetoData(NoteBean noteBean);
    void InsertNotetoData_secret(NoteBean noteBean);
    void InsertNotetoDatabyId(NoteBean noteBean);
    void DeleteNotefromData(NoteBean noteBean);
    void DeleteNotefromData_secret(NoteBean noteBean);
    List<NoteBean> QueryAllNotefromData();
    List<NoteBean> QueryAllNotefromData_secret();
    List<NoteBean> QueryNoyefromDataByType(String READ_TYPE);
    int QueryEveryTypeSumfromDataByType(String READ_TYPE);
    int QueryAllNoteSumfromfromData();//获取创建note的总数量
    int QueryAllSecretNoteSumfromSecretData();//获取创建私密note的总数量
    List<String> QueryNotecreatetime();
    List<NoteBean> QueryNotebeanBycreatetime(String creaetime);
    void ChangeNotetoData(NoteBean noteBean);
    void DeleteNotefromDataByid(Long id);
    Cursor getCursorfromData(String search);
    List<NoteBean> getSearchfromData(String search);
    List<SliceValue> getPieChartNumberfromData();
    List<Integer> getPieChartTypeListfromData();//获取各类型数据
    boolean readDataSizeisEmpty();//判断数据库是否为空
    boolean readSecretDataisEmpty();//判断密码数据库是否为空
}
