package zql.app_jinnang.View;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.joaquimley.faboptions.FabOptionsAnimationStateListener;
import com.kyleduo.switchbutton.SwitchButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.Noteinfo;
import zql.app_jinnang.Prestener.PrestenerImp_add;
import zql.app_jinnang.Prestener.Prestener_add;
import zql.app_jinnang.R;

import static zql.app_jinnang.Bean.Means.isedittext_empty;
import static zql.app_jinnang.Bean.Means.isphotouri;

public class AddActivity extends AppCompatActivity implements AddActivityImp,View.OnClickListener{
    private FabOptions fabOptions;
    private SwitchButton switchButton_secret;
    private Toolbar toolbar_add;
    private TagGroup tagGroup;
    private List<String>tags;
    private PrestenerImp_add prestenerImp_add;
    private MaterialEditText materialEditText;
    private int ADDTYPE=0;
    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;
    private static final int REQUEST_UPDATE=2;
    private static final int EDIT=0;
    private static final int CHANGE=1;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        StatusBarUtil.setColor(this, getColor(R.color.colorFloatingButton));
        initView();
        prestenerImp_add=new Prestener_add(this);
        getIntentExtra();
        prestenerImp_add.setBackgroundcolorfromSeting();
    }
    private void initView(){
        inittoolbarseting();
        initfloatingButton();
        inittagegroup();
        initsaveview();
        initEdittextView();
        initSwitchbutton();
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
    }
    private void initEdittextView(){//实例化一个edittext
        materialEditText=(MaterialEditText)this.findViewById(R.id.add_noteinfoedittext);
    }
    private void initSwitchbutton(){
        switchButton_secret=(SwitchButton)this.findViewById(R.id.add_switchbutton_secret);
    }
    private void initsaveview(){//实例化保存按钮
        TextView saveview=this.findViewById(R.id.add_savefile);
        saveview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isedittext_empty(materialEditText)){
                    Toast.makeText(AddActivity.this, "未输入内容", Toast.LENGTH_SHORT).show();
                }else {
                    prestenerImp_add.addnotetoData(materialEditText.getText().toString());
                    finish();
                }
            }
        });
    }
    private void getIntentExtra(){//获取默认type
        Intent mintent=getIntent();
        Bundle bundle=mintent.getBundleExtra("data");
        int addtype=bundle.getInt("addtype");
        int type=bundle.getInt("notetype");
        ADDTYPE=type;
        String photo=bundle.getString("photouri");
        switch (addtype){
            case EDIT:
                if (!photo.isEmpty()){
                    prestenerImp_add.addphotoTagtoTaggroupandData(photo);
                }
                switch (type){
                    case 0:
                        prestenerImp_add.addnotetypeTagtoTaggroupandData(getString(R.string.note_work));
                        break;
                    case 1:
                        prestenerImp_add.addnotetypeTagtoTaggroupandData(getString(R.string.note_study));
                        break;
                    case 2:
                        prestenerImp_add.addnotetypeTagtoTaggroupandData(getString(R.string.note_live));
                        break;
                    case 3:
                        prestenerImp_add.addnotetypeTagtoTaggroupandData(getString(R.string.note_diary));
                        break;
                    case 4:
                        prestenerImp_add.addnotetypeTagtoTaggroupandData(getString(R.string.note_travel));
                        break;
                    case 1001:
                        prestenerImp_add.addnotetypeTagtoTaggroupandData("<未定义标签>");
                        break;
                    default:
                        prestenerImp_add.addnotetypeTagtoTaggroupandData("error");
                        break;
                }
                break;
            case CHANGE:
                Noteinfo noteinfo=(Noteinfo) bundle.getSerializable("noteinfo");
                prestenerImp_add.setchangeNoteinfoActivity(noteinfo);
                break;
            default:
                break;
        }
    }
    private void inittagegroup(){//实例化标签框
        tagGroup=(TagGroup)this.findViewById(R.id.add_tag_group);
        tags=new ArrayList<String>();
        tags.add(0,"<类型>");
        tags.add(1,"<人物>");
        tags.add(2,"<日期>");
        tags.add(3,"<时间>");
        tags.add(4,"<位置>");
        tags.add(5,"<图片>");
        tagGroup.setTags(tags);
        tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                if (isphotouri(tag)){
                    initphotoshowdialog(tag);
                }else {
                    Toast.makeText(AddActivity.this,tag, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void initfloatingButton(){//实例化floatingbuttond对象
        fabOptions=(FabOptions)this.findViewById(R.id.add_floatingbutton);
        fabOptions.setButtonsMenu(R.menu.menu_item_add_floatingbutton);
        fabOptions.setOnClickListener(this);
    }
    private void inittoolbarseting(){//实例化toolbar对象，设置返回按钮，监听返回按钮状态
        toolbar_add=(Toolbar)this.findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar_add);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_add.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isedittext_empty(materialEditText)){
                    finish();
                }else {
                    initsavenotedialog();
                }
            }
        });
    }
    private void initcenterpeopledialog(){//实例化一个中心dialog输入人物
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
                prestenerImp_add.addpeopleTagtoTaggroupandData(add_dialog_peole_edit.getText().toString());//调用接口实现标签加载
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
    private void initcenterlocationdialog(){//实例化地址输入dialog
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
                prestenerImp_add.addlocationTagtoTaggroupandData(add_dialog_location_edit.getText().toString());//调用接口实现标签加载
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
    private void initnoteTypeDialog(){//实例化一个选择菜单
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
                prestenerImp_add.addnotetypeTagtoTaggroupandData(getString(R.string.note_work));//调用接口实现标签加载
                alertDialog.dismiss();
            }
        });
        add_dialog_type_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestenerImp_add.addnotetypeTagtoTaggroupandData(getString(R.string.note_diary));//调用接口实现标签加载
                alertDialog.dismiss();
            }
        });
        add_dialog_type_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestenerImp_add.addnotetypeTagtoTaggroupandData(getString(R.string.note_live));//调用接口实现标签加载
                alertDialog.dismiss();
            }
        });
        add_dialog_type_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestenerImp_add.addnotetypeTagtoTaggroupandData(getString(R.string.note_study));//调用接口实现标签加载
                alertDialog.dismiss();
            }
        });
        add_dialog_type_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestenerImp_add.addnotetypeTagtoTaggroupandData(getString(R.string.note_travel));//调用接口实现标签加载
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    @TargetApi(Build.VERSION_CODES.M)
    private void initphotoseleteActivity(){//实例化图片拍摄选取对象
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
    private void initdatecenterdialog(){//实例化一个日期选择dialog
        final Calendar calendar=Calendar.getInstance();
        new DatePickerDialog(this,0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int year=i1+1;
                prestenerImp_add.adddateTagtoTaggroupandData(i+"年"+year+"月"+i2+"日");//调用接口实现标签加载
            }
        },calendar.get(Calendar.YEAR)
        ,calendar.get(Calendar.MONTH)
        ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void inittimecenterdialog(){//实例化一个时间选择dialog
        Calendar calendar=Calendar.getInstance();
        new TimePickerDialog(this,3, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                prestenerImp_add.addtimeTagtoTaggroupandData(i+"时"+i1+"分");//调用接口实现标签加载
            }
        }
        ,calendar.get(Calendar.HOUR_OF_DAY)
        ,calendar.get(Calendar.MINUTE)
        ,true).show();
    }
    private void initphotoshowdialog(String imagpath){//实例化一个显示图片的dialog
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
    private void initsavenotedialog(){//实例化一个保存界面弹出的dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(R.string.add_dialog_savenote_message);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.add_dialog_savenote_save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                prestenerImp_add.addnotetoData(materialEditText.getText().toString());
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
    public String getnoteinfofromEdittext() {
        String noteinfo=materialEditText.getText().toString();
        if (!noteinfo.isEmpty()){
            return noteinfo;
        }else {
            return Means.NOSTRING;
        }
    }



    @Override
    public void addnoteinfotoEdittext(String noteinfo) {
        materialEditText.setText(noteinfo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CAMERA_CODE&&resultCode==RESULT_OK&&data!=null){
            List<String>pathlist=data.getStringArrayListExtra("result");
            prestenerImp_add.addphotoTagtoTaggroupandData(pathlist.get(0).toString());//调用接口实现标签加载
            Toast.makeText(this, pathlist.get(0).toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer> colorlist) {
        StatusBarUtil.setColor(this, colorlist.get(0));
        toolbar_add.setBackgroundColor(colorlist.get(0));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_floatingbutton_people:
                initcenterpeopledialog();
                if (fabOptions.isOpen()){
                    fabOptions.close(new FabOptionsAnimationStateListener() {
                        @Override
                        public void onOpenAnimationEnd() {

                        }

                        @Override
                        public void onCloseAnimationEnd() {

                        }
                    });
                }
                break;
            case R.id.add_floatingbutton_clock:
                inittimecenterdialog();
                if (fabOptions.isOpen()){
                    fabOptions.close(new FabOptionsAnimationStateListener() {
                        @Override
                        public void onOpenAnimationEnd() {

                        }

                        @Override
                        public void onCloseAnimationEnd() {

                        }
                    });
                }
                break;
            case R.id.add_floatingbutton_location:
                initcenterlocationdialog();
                if (fabOptions.isOpen()){
                    fabOptions.close(new FabOptionsAnimationStateListener() {
                        @Override
                        public void onOpenAnimationEnd() {

                        }

                        @Override
                        public void onCloseAnimationEnd() {

                        }
                    });
                }
                break;
            case R.id.add_floatingbutton_photo:
                if (fabOptions.isOpen()){
                    initphotoseleteActivity();
                    fabOptions.close(new FabOptionsAnimationStateListener() {
                        @Override
                        public void onOpenAnimationEnd() {

                        }

                        @Override
                        public void onCloseAnimationEnd() {

                        }
                    });
                }
                break;
            case R.id.add_floatingbutton_type:
                initnoteTypeDialog();
                if (fabOptions.isOpen()){
                    fabOptions.close(new FabOptionsAnimationStateListener() {
                        @Override
                        public void onOpenAnimationEnd() {

                        }

                        @Override
                        public void onCloseAnimationEnd() {

                        }
                    });
                }
                break;
            case R.id.add_floatingbutton_time:
                initdatecenterdialog();
                if (fabOptions.isOpen()){
                    fabOptions.close(new FabOptionsAnimationStateListener() {
                        @Override
                        public void onOpenAnimationEnd() {

                        }

                        @Override
                        public void onCloseAnimationEnd() {

                        }
                    });
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void addnotetypeTagtoTaggroup(String notetype) {
        if (!notetype.isEmpty()){
            tags.remove(0);
            tags.add(0,notetype);
        }else {

        }
        tagGroup.setTags(tags);
    }

    @Override
    public void addpeopleTagtoTaggroup(String people) {
        if (!people.isEmpty()){
            tags.remove(1);
            tags.add(1,people);
        }else {

        }
        tagGroup.setTags(tags);
    }

    @Override
    public void adddateTagtoTaggroup(String date) {
        if (!date.isEmpty()){
            tags.remove(2);
            tags.add(2,date);
        }else {

        }
        tagGroup.setTags(tags);
    }

    @Override
    public void addtimeTagtoTaggroup(String time) {
        if (!time.isEmpty()){
            tags.remove(3);
            tags.add(3,time);
        }else {

        }
        tagGroup.setTags(tags);
    }

    @Override
    public void addlocationTagtoTaggroup(String location) {
        if (!location.isEmpty()){
            tags.remove(4);
            tags.add(4,location);
        }else {

        }
        tagGroup.setTags(tags);
    }

    @Override
    public void addphotoTagtoTaggroup(String photo) {
        if (!photo.isEmpty()){
            tags.remove(5);
            tags.add(5,photo);
        }else {

        }
        tagGroup.setTags(tags);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (isedittext_empty(materialEditText)){
                finish();
            }else {
                initsavenotedialog();
            }
        }
        return false;
    }

    @Override
    public void finish() {
        setResult(REQUEST_UPDATE);
        super.finish();
    }

    @Override
    public void showmessageAbout_savenote(int MESSAGE) {
        switch (MESSAGE){
            case 0:
                Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public Context getAddActivityContext() {
        return this;
    }

    @Override
    public Application getAddApplication() {
        return getApplication();
    }

    @Override
    public boolean getIsCheckedSwitchbuttonSecret() {
        return switchButton_secret.isChecked();
    }
}
