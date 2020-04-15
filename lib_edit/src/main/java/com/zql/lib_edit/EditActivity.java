package com.zql.lib_edit;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.joaquimley.faboptions.FabOptions;
import com.kyleduo.switchbutton.SwitchButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.zql.base.event.EventBusUtil;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.base.utils.ToastUtil;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.MessageEvent;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.bean.Noteinfo;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;
import com.zql.comm.data.CommData;
import com.zql.comm.data.UserData;
import com.zql.comm.route.RouteKey;
import com.zql.comm.route.RouteUrl;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;

import static com.zql.comm.bean.Means.isphotouri;

@Route(path = RouteUrl.Url_EditActivity)
public class EditActivity extends BaseLifecycleActivity<EditPresenter> implements EditContract.view, View.OnClickListener {
    private FabOptions fabOptions;

    private SwitchButton switchButton_secret;

    private Toolbar toolbar_add;

    private TagGroup tagGroup;

    private List<String> tags;

    private MaterialEditText materialEditText;

    private NoteBean noteBean;//最后加入数据库的数据类

    private static final int REQUEST_CAMERA_CODE = 1;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_add;
    }

    @Override
    protected void initView() {
        initviewanddata();
        mPresenter.setBackgroundColorfromUserseting();
        initIntentExtra();
    }

    @Override
    protected EditPresenter getPresenter() {
        return new EditPresenter(this);
    }

    /**
     * 实现界面实例化
     */
    private void initviewanddata(){
        this.tags=new ArrayList<>();
        this.noteBean=new NoteBean();
        tags.add("类型");
        tags.add("人物");
        tags.add("日期");
        tags.add("时间");
        tags.add("地点");
        tags.add("图片");
        noteBean.setNotetype("");
        noteBean.setPeople("");
        noteBean.setDate("");
        noteBean.setTime("");
        noteBean.setLocation("");
        noteBean.setPhotopath("");
        noteBean.setIsshow(true);
        inittoolbarseting();
        initfloationgButton();
        inittagegroup();
        initEdittextView();
        initsaveview();
        initSwitchbutton();
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
    }

    /**
     * 监听传入值
     */
    private void initIntentExtra(){
        Intent mintent=getIntent();
        Bundle bundle=mintent.getBundleExtra(RouteKey.EDIT_DATA);
        int type=bundle.getInt(RouteKey.CAPSULE_TYPE);
        switch (type){
            case RouteKey.CAPSULE_WORK:
                updateTagsGroup(0,"工作");
                break;
            case RouteKey.CAPSULE_STUDY:
                updateTagsGroup(0,"学习");
                break;
            case RouteKey.CAPSULE_LIVE:
                updateTagsGroup(0,"生活");
                break;
            case RouteKey.CAPSULE_DIARY:
                updateTagsGroup(0,"日记");
                break;
            case RouteKey.CAPSULE_TRAVEL:
                updateTagsGroup(0,"旅行");
                break;
            case 10:
                Noteinfo noteinfo=(Noteinfo) bundle.getSerializable("noteinfo");
                loadNoteinfotoEdit(noteinfo);
                break;
                default:
                    break;
        }

    }
    /**
     * 修改背景色
     */
    @Override
    public void setbackgroundcolor(List<Integer>list) {
        StatusBarUtil.setColor(this,list.get(0));
        toolbar_add.setBackgroundColor(list.get(0));
    }

    @Override
    public void showMessageOnView(String message) {
        ToastUtil.showToast(message);
        EventBusUtil.postEvent(new MessageEvent(MessageEvent.UPDATE_NETCAPSULE));
        finish();
    }

    /**
     * //实例化一个edittext
     */
    private void initEdittextView(){
        materialEditText = findViewById(R.id.add_noteinfoedittext);
    }
    private void initSwitchbutton(){
        switchButton_secret = findViewById(R.id.add_switchbutton_secret);
    }

    /**
     * //实例化保存按钮
     */
    private void initsaveview(){
        TextView saveview = this.findViewById(R.id.add_savefile);
        saveview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedNoteinfotoDatabase();
            }
        });
    }
    /**
     * 添加到数据库
     */
    private void savedNoteinfotoDatabase(){
        if (materialEditText.getText().toString().isEmpty()){
            Toast.makeText(EditActivity.this, "输入框为空，请重新输入", Toast.LENGTH_SHORT).show();
        }else {
            if (CommData.getIsLocalVersion()){
                noteBean.setNoteinfo(materialEditText.getText().toString());
                noteBean.setCreatetime(Means.getCreatetime());
                if (switchButton_secret.isChecked()){
                    mPresenter.saveNoteinfotoSecrectDatabase(noteBean);
                }else {
                    mPresenter.saveNoteinfotoDatabase(noteBean);
                }
                finish();
            }else {
                if (!UserData.getUserIsLogin()){//用户尚未登陆
                    return;
                }
                noteBean.setNoteinfo(materialEditText.getText().toString());
                mPresenter.addNoteInfoToService(Means.changefromNotebean(noteBean));
            }
        }
    }

    /**
     * //实例化标签框
     */
    private void inittagegroup(){
        tagGroup= findViewById(R.id.add_tag_group);
        tagGroup.setTags(tags);
        tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                if (isphotouri(tag)){
                    initphotoshowdialog(tag);
                }else {
                    Toast.makeText(EditActivity.this,tag, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * //实例化floatingbuttond对象
     */
    private void initfloationgButton(){
        fabOptions=(FabOptions)this.findViewById(R.id.add_floatingbutton);
        fabOptions.setButtonsMenu(R.menu.menu_item_add_floatingbutton);
        fabOptions.setOnClickListener(this);
    }

    /**
     * //实例化toolbar对象，设置返回按钮，监听返回按钮状态
     */
    private void inittoolbarseting(){
        toolbar_add = findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar_add);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_add.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (materialEditText.getText().toString().isEmpty()){
                    finish();
                }else {
                   initsavenotedialog();
                }
            }
        });
    }

    /**
     * //实例化一个中心dialog输入人物
     */
    private void initcenterpeopledialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_add_peopledialog,null);
        final TextView add_dialog_ok,add_dialog_cancel;
        final MaterialEditText add_dialog_peole_edit;
        final AlertDialog alertDialog=builder.setView(centerview).create();
        add_dialog_ok=(TextView) centerview.findViewById(R.id.add_dialog_people_ok);
        add_dialog_cancel=(TextView) centerview.findViewById(R.id.add_dialog_people_cancel);
        add_dialog_peole_edit=(MaterialEditText)centerview.findViewById(R.id.add_dialog_edit_people);
        add_dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTagsGroup(1,add_dialog_peole_edit.getText().toString());
                alertDialog.dismiss();
            }
        });
        add_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    /**
     * //实例化地址输入dialog
     */
    private void initcenterlocationdialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_add_locationdialog,null);
        final TextView add_location_ok,add_location_cancel;
        final MaterialEditText add_dialog_location_edit;
        final AlertDialog alertDialog=builder.setView(centerview).create();
        add_location_ok=(TextView)centerview.findViewById(R.id.add_dialog_location_ok);
        add_location_cancel=(TextView)centerview.findViewById(R.id.add_dialog_location_cancel);
        add_dialog_location_edit=(MaterialEditText)centerview.findViewById(R.id.add_dialog_edit_location);
        add_location_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTagsGroup(4,add_dialog_location_edit.getText().toString());
                alertDialog.dismiss();
            }
        });
        add_location_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    /**
     * //实例化一个类型重新选择菜单
     */
    private void initnoteTypeDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_add_notetypedialog,null);
        LinearLayout add_dialog_type_work,add_dialog_type_study,add_dialog_type_live,add_dialog_type_diary,add_dialog_type_travel;
        add_dialog_type_work = centerview.findViewById(R.id.add_dialog_typenote_work);
        add_dialog_type_diary = centerview.findViewById(R.id.add_dialog_typenote_diary);
        add_dialog_type_live = centerview.findViewById(R.id.add_dialog_typenote_live);
        add_dialog_type_study = centerview.findViewById(R.id.add_dialog_typenote_study);
        add_dialog_type_travel = centerview.findViewById(R.id.add_dialog_typenote_travel);
        final AlertDialog alertDialog=builder.setView(centerview).create();
        add_dialog_type_work.setOnClickListener(view -> {
            updateTagsGroup(0,getString(R.string.note_work));
            alertDialog.dismiss();
        });
        add_dialog_type_diary.setOnClickListener(view -> {
            updateTagsGroup(0,getString(R.string.note_diary));
            alertDialog.dismiss();
        });
        add_dialog_type_live.setOnClickListener(view -> {
            updateTagsGroup(0,getString(R.string.note_live));
            alertDialog.dismiss();
        });
        add_dialog_type_study.setOnClickListener(view -> {
            updateTagsGroup(0,getString(R.string.note_study));
            alertDialog.dismiss();
        });
        add_dialog_type_travel.setOnClickListener(view -> {
            updateTagsGroup(0,getString(R.string.note_travel));
            alertDialog.dismiss();
        });
        alertDialog.show();
    }

    /**
     * //实例化图片拍摄选取对象
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void initphotoseleteActivity(){
        ISListConfig config=new ISListConfig.Builder()
                .multiSelect(false)
                .rememberSelected(false)
                .btnBgColor(Color.WHITE)
                .btnTextColor(Color.BLACK)
                .statusBarColor(getColor(R.color.colorFloatingButton))
                .backResId(R.drawable.icon_back)
                .title("选取图片")
                .titleColor(Color.BLACK)
                .titleBgColor(getColor(R.color.colorFloatingButton))
                .needCamera(true)
                .needCrop(false)
                .cropSize(0,0,400,200)
                .maxNum(9)
                .build();
        ISNav.getInstance().toListActivity(this,config,REQUEST_CAMERA_CODE);
    }

    /**
     * //实例化一个日期选择dialog
     */
    private void initdatecenterdialog(){
        final Calendar calendar=Calendar.getInstance();
        new DatePickerDialog(this,0, (datePicker, i, i1, i2) -> {
            int year=i1+1;
            updateTagsGroup(2,i+"年"+year+"月"+i2+"日");
        },calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH)
                ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    /**
     //实例化一个时间选择dialog
     */
    private void inittimecenterdialog(){
        Calendar calendar=Calendar.getInstance();
        new TimePickerDialog(this,3, (timePicker, i, i1) -> updateTagsGroup(3,i+"时"+i1+"分")
                ,calendar.get(Calendar.HOUR_OF_DAY)
                ,calendar.get(Calendar.MINUTE)
                ,true).show();
    }
    /**
     //实例化一个显示图片的dialog
     */
    private void initphotoshowdialog(String imagpath){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_add_photodiaolg,null);
        final ImageView photoimageview;
        final AlertDialog alertDialog=builder.setView(centerview).create();
        photoimageview=(ImageView)centerview.findViewById(R.id.add_dialog_imageview);
        Glide.with(centerview.getContext()).load(imagpath).into(photoimageview);
        photoimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    /**
     实例化一个保存界面弹出的dialog
     */
    private void initsavenotedialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(R.string.add_dialog_savenote_message);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.add_dialog_savenote_save, (dialogInterface, i) -> {
            savedNoteinfotoDatabase();
            dialogInterface.dismiss();
            finish();
        });
        builder.setNegativeButton(R.string.add_dialog_savenote_cancel, (dialogInterface, i) -> {
            dialogInterface.dismiss();
            finish();
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    /**
     * 函数重写区
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CAMERA_CODE&&resultCode==RESULT_OK&&data!=null){
            List<String>pathlist=data.getStringArrayListExtra("result");
            updateTagsGroup(5,pathlist.get(0).toString());
        }
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.add_floatingbutton_people) {
            initcenterpeopledialog();
            if (fabOptions.isOpen()) {
                fabOptions.close(null);
            }
        } else if (id == R.id.add_floatingbutton_clock) {
            inittimecenterdialog();
            if (fabOptions.isOpen()) {
                fabOptions.close(null);
            }
        } else if (id == R.id.add_floatingbutton_location) {
            initcenterlocationdialog();
            if (fabOptions.isOpen()) {
                fabOptions.close(null);
            }
        } else if (id == R.id.add_floatingbutton_photo) {
            initphotoseleteActivity();
            if (fabOptions.isOpen()) {
                fabOptions.close(null);
            }
        } else if (id == R.id.add_floatingbutton_type) {
            initnoteTypeDialog();
            if (fabOptions.isOpen()) {
                fabOptions.close(null);
            }
        } else if (id == R.id.add_floatingbutton_time) {
            initdatecenterdialog();
            if (fabOptions.isOpen()) {
                fabOptions.close(null);
            }
        }
    }

    /**
     * 修改模式下，加载已经有的记录
     * @return
     */
    private void loadNoteinfotoEdit(Noteinfo noteinfo){
        if (noteinfo.getId()!=null){
            noteBean.setId(noteinfo.getId());
        }
       if (!noteinfo.getNoteinfo().isEmpty()){
            materialEditText.setText(noteinfo.getNoteinfo());
        }
        if (!noteinfo.getNotetype().isEmpty()){
            updateTagsGroup(0,noteinfo.getNotetype());
        }
        if (!TextUtils.isEmpty(noteinfo.getPeople())){
            updateTagsGroup(1,noteinfo.getPeople());
        }
        if (!TextUtils.isEmpty(noteinfo.getDate())){
            updateTagsGroup(2,noteinfo.getDate());
        }
        if (!TextUtils.isEmpty(noteinfo.getTime())){
            updateTagsGroup(3,noteinfo.getTime());
        }
        if (!TextUtils.isEmpty(noteinfo.getLocation())){
            updateTagsGroup(4,noteinfo.getLocation());
        }
        if (!TextUtils.isEmpty(noteinfo.getPhotopath())){
            updateTagsGroup(5,noteinfo.getPhotopath());
        }
    }

    /**
     * 更新视图
     * @param i
     * @param str
     */
    private void updateTagsGroup(int i,String str){
        if (tags!=null){
            tags.remove(i);
            tags.add(i,str);
        }
        switch (i){
            case 0:
                noteBean.setNotetype(str);
                break;
            case 1:
                noteBean.setPeople(str);
                break;
            case 2:
                noteBean.setDate(str);
                break;
            case 3:
                noteBean.setTime(str);
                break;
            case 4:
                noteBean.setLocation(str);
                break;
            case 5:
                noteBean.setPhotopath(str);
                break;
            default:
                break;
        }
        tagGroup.setTags(tags);
    }
    /**
    获取context
     */
    @Override
    public Context getbasecontext() {
        return this;
    }
    /**
     * 获取application
     */
    @Override
    public Application getapplication() {
        return this.getApplication();
    }


    /**
     * 返回按键事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (materialEditText.getText().toString().isEmpty()){
                finish();
            }else {
                initsavenotedialog();
            }
        }
        return false;
    }

    /**
     * Event事件监听函数
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handEvent(MessageEvent messageEvent){
        switch (messageEvent.getMessageevent()){
            case MessageEvent.UPDATA_COLOR:
                mPresenter.setBackgroundColorfromUserseting();
                break;
            default:
                break;

        }
    }



    @Override
    public void finish() {
        super.finish();
    }
}
