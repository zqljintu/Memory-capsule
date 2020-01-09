package com.zql.comm.Model;

import android.content.Context;
import android.database.Cursor;


import com.zql.comm.R;
import com.zql.comm.UserSeting;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.MessageEvent;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.bean.NoteBeanDao;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lecho.lib.hellocharts.model.SliceValue;

/**
 * Created by 尽途 on 2018/4/4.
 */

public class NoteInfoModel implements NoteInfoModelImp {

    private Context mcontext;


    private NoteBeanDao noteBeanDao,noteBeanDao_secret;


    public NoteInfoModel(Context context){
        this.mcontext=context;
        noteBeanDao = UserSeting.getNoteDaoSession().getNoteBeanDao();
        noteBeanDao_secret = UserSeting.getNoteSeDaoSession().getNoteBeanDao();
    }

    /**
     * 数据库插入操作
     * @param noteBean
     */
    @Override
    public void InsertNotetoData(NoteBean noteBean) {
        noteBeanDao.insert(noteBean);
        EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATE_DATA));
    }

    @Override
    public void InsertNotetoData_secret(NoteBean noteBean) {
        if (noteBean.getId()!=null){
            noteBean.setId(null);
        }
        noteBeanDao_secret.insert(noteBean);
        EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATE_DATA));
    }

    @Override
    public void InsertNotetoDatabyId(NoteBean noteBean) {
        if (noteBean.getId()!=null){
            noteBeanDao.refresh(noteBean);
            EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATE_DATA));
        }
    }

    /**
     * 数据库删除操作
     * @param noteBean
     */

    @Override
    public void DeleteNotefromData(NoteBean noteBean) {
        noteBeanDao.delete(noteBean);
        EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATE_DATA));
    }

    @Override
    public void DeleteNotefromData_secret(NoteBean noteBean) {
        noteBeanDao_secret.delete(noteBean);
    }

    @Override
    public void ChangeNotetoData(NoteBean noteBean) {
        if (noteBean.getId()!=null){
            DeleteNotefromDataByid(noteBean.getId());
            noteBeanDao.insert(noteBean);
            EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATE_DATA));
        }
    }

    @Override
    public List<String> QueryNotecreatetime() {//获取创建时间的列表
        List<NoteBean> mlist=noteBeanDao.loadAll();
        List<String> slist=new ArrayList<>();
        for (int i=0;i<mlist.size();i++){
            slist.add(mlist.get(i).getCreatetime());
        }
        return Means.removeDuplicate(slist);//去除重复的元素
    }

    @Override//通过创建时间查询
    public List<NoteBean> QueryNotebeanBycreatetime(String creaetime) {
        QueryBuilder<NoteBean> queryBuilder=noteBeanDao.queryBuilder();
        queryBuilder.where(NoteBeanDao.Properties.Createtime.like(creaetime))
                .orderAsc(NoteBeanDao.Properties.Createtime);
        return queryBuilder.list();
    }

    @Override
    public List<NoteBean> QueryAllNotefromData() {
        UserSeting.getNoteDaoSession().clear();
        noteBeanDao.detachAll();
        List mlist=noteBeanDao.loadAll();
        Collections.reverse(mlist);//倒序
        return mlist;
    }

    @Override
    public List<NoteBean> QueryAllNotefromData_secret() {
        List mlist=noteBeanDao_secret.loadAll();
        Collections.reverse(mlist);
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

    @Override
    public int QueryAllNoteSumfromfromData() {
        List mlist=noteBeanDao.loadAll();
        return mlist.size();
    }

    @Override
    public int QueryAllSecretNoteSumfromSecretData() {
        List mlist=noteBeanDao_secret.loadAll();
        return mlist.size();
    }

    @Override
    public int QueryEveryTypeSumfromDataByType(String READ_TYPE) {
        QueryBuilder<NoteBean> queryBuilder=noteBeanDao.queryBuilder();
        queryBuilder.where(NoteBeanDao.Properties.Notetype.like(READ_TYPE))
                .orderAsc(NoteBeanDao.Properties.Notetype);
        List list=queryBuilder.list();
        return list.size();
    }

    @Override
    public List<Integer> getPieChartTypeListfromData() {
        List<String> types=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        types.add(0,"工作");
        types.add(1,"学习");
        types.add(2,"生活");
        types.add(3,"日记");
        types.add(4,"旅行");
        for (int i=0;i<5;i++){
            list.add(i,QueryEveryTypeSumfromDataByType(types.get(i)));
        }
        return list;
    }

    @Override
    public List<SliceValue> getPieChartNumberfromData() {//获取饼状图的数据
        List<SliceValue>mlist=new ArrayList<>();
        List<Integer> colors=new ArrayList<>();
        List<String> types=new ArrayList<>();
        types.add(0,"生活");
        types.add(1,"工作");
        types.add(2,"学习");
        types.add(3,"日记");
        types.add(4,"旅行");
        colors.add(0, R.color.colorlive);
        colors.add(1,R.color.colorwork);
        colors.add(2,R.color.colorstudy);
        colors.add(3,R.color.colordiary);
        colors.add(4,R.color.colortravel);
        for (int i=0;i<5;i++){
            SliceValue sliceValue=new SliceValue();
            sliceValue.setColor(colors.get(i));
            sliceValue.setLabel(types.get(i));
            sliceValue.setValue(QueryEveryTypeSumfromDataByType(types.get(i)));
        }
        return mlist;
    }

    @Override
    public boolean readDataSizeisEmpty() {
        if (QueryAllNoteSumfromfromData()==0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean readSecretDataisEmpty() {
        if (QueryAllSecretNoteSumfromSecretData()==0){
            return true;
        }else {
            return false;
        }
    }
}
