package com.zql.app_jinnang.View;

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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.joaquimley.faboptions.FabOptions;
import com.kyleduo.switchbutton.SwitchButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import com.zql.app_jinnang.Bean.Means;
import com.zql.app_jinnang.Bean.NoteBean;
import com.zql.app_jinnang.Bean.Noteinfo;
import com.zql.app_jinnang.Prestener.Prestener_edit;
import com.zql.app_jinnang.Prestener.PresterImp_edit;
import com.zql.app_jinnang.R;

import static com.zql.app_jinnang.Bean.Means.isphotouri;


public class EditActivity extends AppCompatActivity implements EditActivityImp,View.OnClickListener {
    private PresterImp_edit presterImp_edit;//代理接口
    private FabOptions fabOptions;
    private SwitchButton switchButton_secret;
    private Toolbar toolbar_add;
    private TagGroup tagGroup;
    private List<String> tags;
    private MaterialEditText materialEditText;
    private NoteBean noteBean;//最后加入数据库的数据类
    private static final int REQUEST_CAMERA_CODE = 1;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initprestener();
        initviewanddata();
        presterImp_edit.setBackgroundColorfromUserseting();
        initIntentExtra();
    }

    /**
     * 实现界面实例化
     */
    private void initviewanddata(){
        this.tags=new ArrayList<>();
        this.noteBean=new NoteBean();
        tags.add("<类型>");
        tags.add("<人物>");
        tags.add("<日期>");
        tags.add("<时间>");
        tags.add("<地点>");
        tags.add("<图片>");
        noteBean.setNotetype("null");//默认为null
        noteBean.setPeople("null");
        noteBean.setDate("null");
        noteBean.setTime("null");
        noteBean.setLocation("null");
        noteBean.setPhotopath("null");
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
     * 实现代理接口
     */
    private void initprestener(){
        presterImp_edit=new Prestener_edit(this);
    }
    /**
     * 监听传入值
     */
    private void initIntentExtra(){
        Intent mintent=getIntent();
        Bundle bundle=mintent.getBundleExtra("data");
        int type=bundle.getInt("type");
        switch (type){
            case 0:
                updateTagsGroup(0,"工作");
                break;
            case 1:
                updateTagsGroup(0,"学习");
                break;
            case 2:
                updateTagsGroup(0,"生活");
                break;
            case 3:
                updateTagsGroup(0,"日记");
                break;
            case 4:
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
        toolbar_add.setBackgroundColor(list.get(1));
    }

    /**
     * //实例化一个edittext
     */
    private void initEdittextView(){
        materialEditText=(MaterialEditText)this.findViewById(R.id.add_noteinfoedittext);
    }
    private void initSwitchbutton(){
        switchButton_secret=(SwitchButton)this.findViewById(R.id.add_switchbutton_secret);
    }

    /**
     * //实例化保存按钮
     */
    private void initsaveview(){
        TextView saveview=this.findViewById(R.id.add_savefile);
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
            noteBean.setNoteinfo(materialEditText.getText().toString());
            noteBean.setCreatetime(Means.getCreatetime());
            if (switchButton_secret.isChecked()){
                presterImp_edit.saveNoteinfotoSecrectDatabase(noteBean);
            }else {
                presterImp_edit.saveNoteinfotoDatabase(noteBean);
            }
            //EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATE_DATA));
            finish();
        }
    }

    /**
     * //实例化标签框
     */
    private void inittagegroup(){
        tagGroup=(TagGroup)this.findViewById(R.id.add_tag_group);
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
        toolbar_add=(Toolbar)this.findViewById(R.id.toolbar_add);
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
        add_dialog_type_work=(LinearLayout)centerview.findViewById(R.id.add_dialog_typenote_work);
        add_dialog_type_diary=(LinearLayout)centerview.findViewById(R.id.add_dialog_typenote_diary);
        add_dialog_type_live=(LinearLayout)centerview.findViewById(R.id.add_dialog_typenote_live);
        add_dialog_type_study=(LinearLayout)centerview.findViewById(R.id.add_dialog_typenote_study);
        add_dialog_type_travel=(LinearLayout)centerview.findViewById(R.id.add_dialog_typenote_travel);
        final AlertDialog alertDialog=builder.setView(centerview).create();
        add_dialog_type_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTagsGroup(0,getString(R.string.note_work));
                alertDialog.dismiss();
            }
        });
        add_dialog_type_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTagsGroup(0,getString(R.string.note_diary));
                alertDialog.dismiss();
            }
        });
        add_dialog_type_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTagsGroup(0,getString(R.string.note_live));
                alertDialog.dismiss();
            }
        });
        add_dialog_type_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTagsGroup(0,getString(R.string.note_study));
                alertDialog.dismiss();
            }
        });
        add_dialog_type_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTagsGroup(0,getString(R.string.note_travel));
                alertDialog.dismiss();
            }
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
        new DatePickerDialog(this,0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int year=i1+1;
                updateTagsGroup(2,i+"年"+year+"月"+i2+"日");
            }
        },calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH)
                ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    /**
     //实例化一个时间选择dialog
     */
    private void inittimecenterdialog(){
        Calendar calendar=Calendar.getInstance();
        new TimePickerDialog(this,3, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                updateTagsGroup(3,i+"时"+i1+"分");
            }
        }
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
        builder.setPositiveButton(R.string.add_dialog_savenote_save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                savedNoteinfotoDatabase();
                dialogInterface.dismiss();
                finish();
            }
        });
        builder.setNegativeButton(R.string.add_dialog_savenote_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
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
        switch (view.getId()){
            case R.id.add_floatingbutton_people:
                initcenterpeopledialog();
                if (fabOptions.isOpen()){
                    fabOptions.close(null);
                }
                break;
            case R.id.add_floatingbutton_clock:
                inittimecenterdialog();
                if (fabOptions.isOpen()){
                    fabOptions.close(null);
                }
                break;
            case R.id.add_floatingbutton_location:
                initcenterlocationdialog();
                if (fabOptions.isOpen()){
                    fabOptions.close(null);
                }
                break;
            case R.id.add_floatingbutton_photo:
                initphotoseleteActivity();
                if (fabOptions.isOpen()){
                    fabOptions.close(null);
                }
                break;
            case R.id.add_floatingbutton_type:
                initnoteTypeDialog();
                if (fabOptions.isOpen()){
                    fabOptions.close(null);
                }
                break;
            case R.id.add_floatingbutton_time:
                initdatecenterdialog();
                if (fabOptions.isOpen()){
                    fabOptions.close(null);
                }
                break;
            default:
                break;
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
        if (!noteinfo.getPeople().equals("null")){
            updateTagsGroup(1,noteinfo.getPeople());
        }
        if (!noteinfo.getDate().equals("null")){
            updateTagsGroup(2,noteinfo.getDate());
        }
        if (!noteinfo.getTime().equals("null")){
            updateTagsGroup(3,noteinfo.getTime());
        }
        if (!noteinfo.getLocation().equals("null")){
            updateTagsGroup(4,noteinfo.getLocation());
        }
        if (!noteinfo.getPhotopath().equals("图片")||!noteinfo.getPhotopath().equals("null")){
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

    @Override
    public void finish() {
        super.finish();
    }
}
