package zql.app_jinnang.Model;

import android.database.Cursor;

import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import zql.app_jinnang.Bean.NoteBean;

/**
 * Created by 尽途 on 2018/4/4.
 */

public interface NoteInfoModelImp {
    public void InsertNotetoData(NoteBean noteBean);
    public void InsertNotetoData_secret(NoteBean noteBean);
    public void InsertNotetoDatabyId(NoteBean noteBean);
    public void DeleteNotefromData(NoteBean noteBean);
    public void DeleteNotefromData_secret(NoteBean noteBean);
    public List<NoteBean> QueryAllNotefromData();
    public List<NoteBean> QueryAllNotefromData_secret();
    public List<NoteBean> QueryNoyefromDataByType(String READ_TYPE);
    public int QueryEveryTypeSumfromDataByType(String READ_TYPE);
    public int QueryAllNoteSumfromfromData();//获取创建note的总数量
    public int QueryAllSecretNoteSumfromSecretData();//获取创建私密note的总数量
    public List<String> QueryNotecreatetime();
    public List<NoteBean> QueryNotebeanBycreatetime(String creaetime);
    public void ChangeNotetoData(NoteBean noteBean);
    public void DeleteNotefromDataByid(Long id);
    public Cursor getCursorfromData(String search);
    public List<NoteBean> getSearchfromData(String search);
    public List<SliceValue> getPieChartNumberfromData();
    public List<Integer> getPieChartTypeListfromData();//获取各类型数据
    public boolean readDataSizeisEmpty();//判断数据库是否为空
    public boolean readSecretDataisEmpty();//判断密码数据库是否为空
}
