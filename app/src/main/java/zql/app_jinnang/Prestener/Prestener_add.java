package zql.app_jinnang.Prestener;

import android.graphics.BitmapFactory;
import android.widget.Toast;

import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Bean.Noteinfo;
import zql.app_jinnang.Model.NoteInfoModel;
import zql.app_jinnang.Model.NoteInfoModelImp;
import zql.app_jinnang.R;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.AddActivityImp;

/**
 * Created by 尽途 on 2018/4/12.
 */

public class Prestener_add implements PrestenerImp_add{
    private AddActivityImp addActivityImp;
    private NoteInfoModelImp noteInfoModelImp;
    private NoteBean noteBean;
    private String notetype,photopath;
    private UserSeting userSeting;
    private Long DELETEID;
    public Prestener_add(AddActivityImp maddActivityImp){
        this.addActivityImp=maddActivityImp;
        noteInfoModelImp= new NoteInfoModel(addActivityImp.getAddActivityContext());
        userSeting=(UserSeting)addActivityImp.getAddApplication();
        DELETEID=new Long(0);
        noteBean=new NoteBean();
        noteBean.setNoteinfo(Means.NOSTRING);
        noteBean.setNotetype(Means.NOSTRING);
        noteBean.setPeople(Means.NOSTRING);
        noteBean.setDate(Means.NOSTRING);
        noteBean.setTime(Means.NOSTRING);
        noteBean.setLocation(Means.NOSTRING);
        noteBean.setPhotopath(Means.NOSTRING);
        noteBean.setIsshow(true);
        noteBean.setCreatetime(Means.NOSTRING);
    }

    @Override
    public void setBackgroundcolorfromSeting() {
        addActivityImp.setBackgroundcolorfromSeting(userSeting.getcurrentColor());
    }

    @Override
    public void addpeopleTagtoTaggroupandData(String people) {
        addActivityImp.addpeopleTagtoTaggroup(people);
        noteBean.setPeople(people);
    }

    @Override
    public void addnotetypeTagtoTaggroupandData(String notetype) {
        addActivityImp.addnotetypeTagtoTaggroup(notetype);
        this.notetype=notetype;
    }

    @Override
    public void adddateTagtoTaggroupandData(String date) {
        addActivityImp.adddateTagtoTaggroup(date);
        noteBean.setDate(date);
    }

    @Override
    public void addtimeTagtoTaggroupandData(String time) {
        addActivityImp.addtimeTagtoTaggroup(time);
        noteBean.setTime(time);
    }

    @Override
    public void addlocationTagtoTaggroupandData(String location) {
       addActivityImp.addlocationTagtoTaggroup(location);
       noteBean.setLocation(location);
    }

    @Override
    public void addphotoTagtoTaggroupandData(String photo) {
        addActivityImp.addphotoTagtoTaggroup(photo);
        noteBean.setPhotopath(photo);
    }

    @Override//保存初次编辑的信息到数据库
    public void addnotetoData(String noteinfo) {
        int SAVESUCCESS=1;
        int SAVEFAIL=0;
        int CHANGESUCCESS=2;
        noteBean.setId(null);
        noteBean.setNoteinfo(noteinfo);
        noteBean.setNotetype(notetype);
        noteBean.setCreatetime(Means.getCreatetime());
        if (DELETEID.equals(0)){//判断是不是修改的文件，如果是的话，先删除这个文件，再重新插入文件到开头
            if (addActivityImp.getIsCheckedSwitchbuttonSecret()){
                noteInfoModelImp.InsertNotetoData(noteBean);
            }else {
                noteInfoModelImp.InsertNotetoData(noteBean);
            }
            addActivityImp.showmessageAbout_savenote(SAVESUCCESS);
        }else {
            if (addActivityImp.getIsCheckedSwitchbuttonSecret()){//判断switchButton的状态来判断存入哪个数据库文件中
                noteInfoModelImp.DeleteNotefromDataByid(DELETEID);
                noteInfoModelImp.InsertNotetoData_secret(noteBean);
            }else {
                noteInfoModelImp.DeleteNotefromDataByid(DELETEID);
                noteInfoModelImp.InsertNotetoData(noteBean);
            }
            addActivityImp.showmessageAbout_savenote(CHANGESUCCESS);
        }
    }

    /**
     * 修改锦囊信息时，将已经存在的消息内容和标签转存到编辑界面，继续进行编辑修改操作
     * @param noteinfo
     */
    @Override
    public void setchangeNoteinfoActivity(Noteinfo noteinfo) {
        if (!noteinfo.getNoteinfo().isEmpty()){
            addActivityImp.addnoteinfotoEdittext(noteinfo.getNoteinfo());
        }
        if (!noteinfo.getNotetype().isEmpty()){
            this.notetype=noteinfo.getNotetype();
            addActivityImp.addnotetypeTagtoTaggroup(noteinfo.getNotetype());
        }
        if (noteinfo.getDate().equals("null")){
            addActivityImp.adddateTagtoTaggroup("<日期>");
            noteBean.setDate(noteinfo.getDate());
        }else {
            addActivityImp.adddateTagtoTaggroup(noteinfo.getDate());
            noteBean.setDate(noteinfo.getDate());
        }
        if (noteinfo.getPeople().equals("null")){
            addActivityImp.addpeopleTagtoTaggroup("<人物>");
            noteBean.setPeople(noteinfo.getPeople());
        }else {
            addActivityImp.addpeopleTagtoTaggroup(noteinfo.getPeople());
            noteBean.setPeople(noteinfo.getPeople());
        }
        if (noteinfo.getTime().equals("null")){
            addActivityImp.addtimeTagtoTaggroup("<时间>");
            noteBean.setTime(noteinfo.getTime());
        }else {
            addActivityImp.addtimeTagtoTaggroup(noteinfo.getTime());
            noteBean.setTime(noteinfo.getTime());
        }
        if (noteinfo.getLocation().equals("null")){
            addActivityImp.addlocationTagtoTaggroup("<地点>");
            noteBean.setLocation(noteinfo.getLocation());
        }else {
            addActivityImp.addlocationTagtoTaggroup(noteinfo.getLocation());
            noteBean.setLocation(noteinfo.getLocation());
        }
        if (noteinfo.getPhotopath().equals("图片")){
            addActivityImp.addphotoTagtoTaggroup("<图片>");
            noteBean.setPhotopath(noteinfo.getPhotopath());
        }else {
            addActivityImp.addphotoTagtoTaggroup(noteinfo.getPhotopath());
            noteBean.setPhotopath(noteinfo.getPhotopath());
        }
        noteBean.setIsshow(noteinfo.getIsshow());
        noteBean.setCreatetime(noteinfo.getCreatetime());
        this.DELETEID=noteinfo.getId();
    }
}
