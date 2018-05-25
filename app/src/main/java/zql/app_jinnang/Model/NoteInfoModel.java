package zql.app_jinnang.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.CursorQuery;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.greendao.db.DaoMaster;
import zql.app_jinnang.greendao.db.DaoSession;
import zql.app_jinnang.greendao.db.NoteBeanDao;

/**
 * Created by 尽途 on 2018/4/4.
 */

public class NoteInfoModel implements NoteInfoModelImp {
    private NoteBeanDao noteBeanDao;
    private SQLiteDatabase db;
    private DaoSession daoSession;
    private Context mcontext;

    public NoteInfoModel(Context context){
        this.mcontext=context;
        initGreendao();
    }
    void initGreendao(){
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(mcontext,"recluse-db",null);
        db=helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(db);
        daoSession=daoMaster.newSession();
        noteBeanDao=daoSession.getNoteBeanDao();
    }


    @Override
    public void InsertNotetoData(NoteBean noteBean) {
        noteBeanDao.insert(noteBean);
    }

    @Override
    public void DeleteNotefromData(NoteBean noteBean) {
        noteBeanDao.delete(noteBean);
    }

    @Override
    public void ChangeNotetoData(NoteBean noteBean) {
        noteBeanDao.update(noteBean);
    }

    @Override
    public List<String> QueryNotecreatetime() {//获取创建时间的列表
        List<NoteBean> mlist=noteBeanDao.loadAll();
        List<String> slist=new ArrayList<>();
        for (int i=0;i<mlist.size();i++){
            slist.add(mlist.get(i).getCreatetime().toString());
        }
        return Means.removeDuplicate(slist);//去除重复的元素
    }

    @Override
    public List<NoteBean> QueryNotebeanBycreatetime(String creaetime) {
        QueryBuilder<NoteBean> queryBuilder=noteBeanDao.queryBuilder();
        queryBuilder.where(NoteBeanDao.Properties.Createtime.like(creaetime))
                .orderAsc(NoteBeanDao.Properties.Createtime);
        return queryBuilder.list();
    }

    @Override
    public List<NoteBean> QueryAllNotefromData() {
        List mlist=noteBeanDao.loadAll();
        Collections.reverse(mlist);//倒序
        return mlist;
    }

    @Override
    public Cursor getCursorfromData(String search) {
        Cursor cursor=null;
        return cursor;
    }

    @Override
    public void DeleteNotefromDataByid(Long id) {
        noteBeanDao.deleteByKey(id);
    }

    @Override
    public List<NoteBean> getSearchfromData(String search) {
        QueryBuilder<NoteBean> queryBuilder=noteBeanDao.queryBuilder();
        queryBuilder.where(NoteBeanDao.Properties.Noteinfo.like("%"+search+"%"))
                .orderAsc(NoteBeanDao.Properties.Noteinfo);
        return queryBuilder.list();
    }

    @Override
    public List<NoteBean> QueryNoyefromDataByType(String READ_TYPE) {
        QueryBuilder<NoteBean> queryBuilder=noteBeanDao.queryBuilder();
        queryBuilder.where(NoteBeanDao.Properties.Notetype.like(READ_TYPE))
                .orderAsc(NoteBeanDao.Properties.Notetype);
        List list=queryBuilder.list();
        Collections.reverse(list);//倒序
        return list;
    }
}
