package com.zql.lib_setting.view;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.jaeger.library.StatusBarUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.UserSeting;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.MessageEvent;
import com.zql.lib_setting.R;

import org.angmarch.views.NiceSpinner;
import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class AboutActivity extends BaseLifecycleActivity<AboutPresenter> implements AboutContract.view, View.OnClickListener{
    private AlertDialog alertDialog_creatpassword;
    private NiceSpinner niceSpinner;
    private Toolbar toolbar_about;
    private UserSeting userSeting;
    

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorFloatingButton));
        userSeting=(UserSeting)getApplication();
        initview();
        mPresenter.setBackgroundcolorfromSeting();
    }

    @Override
    protected AboutPresenter getPresenter() {
        return new AboutPresenter(this);
    }

    private void initview(){
        inittoolbarseting();
        initAboutcode();
        initBackgroundcolorview();
        initSerectandQuestionview();
    }
    private void inittoolbarseting(){
        toolbar_about=(Toolbar)this.findViewById(R.id.toolbar_about);
        setSupportActionBar(toolbar_about);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_about.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initSerectandQuestionview(){
        TextView text_password=(TextView)this.findViewById(R.id.text_creatpasswordandquestion);
        TextView text_password_edit=(TextView)this.findViewById(R.id.text_editpassword);
        TextView text_question_edit=(TextView)this.findViewById(R.id.text_editquestion);
        TextView text_forgetpaswword=(TextView)this.findViewById(R.id.text_forgetpassword);
        text_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPresenter.isnullthepasswordfromSeting()){
                    initpassworddialog();
                }else {
                    Toast.makeText(AboutActivity.this, "密码已经存在，如修改请点击修改密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
        text_password_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPresenter.isnullthepasswordfromSeting()){
                    Toast.makeText(AboutActivity.this, "密码尚未创建，请先创建密码", Toast.LENGTH_SHORT).show();
                }else {
                    initEditpassworddialog();
                }
            }
        });
        text_question_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPresenter.isnullthequestionfromSeting()){
                    Toast.makeText(AboutActivity.this, "密保尚未创建，请先创建密保", Toast.LENGTH_SHORT).show();
                }else {
                    initQuestionddialog();
                }
            }
        });
        text_forgetpaswword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPresenter.isnullthequestionfromSeting()){
                    Toast.makeText(AboutActivity.this, "密保尚未创建，请先创建密保", Toast.LENGTH_SHORT).show();
                }else {
                    initiscurrentQuestiondialog();
                }
            }
        });
    }
    private void initEditnewpasssworddialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_editpassworddialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_password_edit_password);
        final TextView texttitle=(TextView)centerview.findViewById(R.id.title_text_password);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok_password);
        final AlertDialog alertDialog_editpassword=builder.setView(centerview).create();
        texttitle.setText("创建新的密码");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Means.isedittext_empty(materialEditText_password)){
                    Toast.makeText(AboutActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                }else {
                    if (materialEditText_password.getText().toString().length()!=6){
                        Toast.makeText(AboutActivity.this, "请输入六位密码", Toast.LENGTH_SHORT).show();
                    }else {
                        mPresenter.putpasswordOnSeting(materialEditText_password.getText().toString());
                        alertDialog_editpassword.dismiss();
                    }
                }
            }
        });
        alertDialog_editpassword.show();
    }
    private void initEditpassworddialog(){//实例化一个重新编辑密码的dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_editpassworddialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_password_edit_password);
        final TextView texttitle=(TextView)centerview.findViewById(R.id.title_text_password);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok_password);
        final AlertDialog alertDialog_editpassword=builder.setView(centerview).create();
        texttitle.setText("请输入旧密码");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPresenter.iscurrentthepasswordfromSeting(materialEditText_password.getText().toString())){
                    initEditnewpasssworddialog();
                    alertDialog_editpassword.dismiss();
                }else {
                    Toast.makeText(AboutActivity.this, "输入密码有误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog_editpassword.show();
    }
    private void initQuestionddialog(){//实例化一个重新编辑密保的dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_editquestiondialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_question_edit_question);
        final TextView texttitle=(TextView)centerview.findViewById(R.id.title_text_question);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok_question);
        final AlertDialog alertDialog_editpassword=builder.setView(centerview).create();
        texttitle.setText("请输入旧密保");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPresenter.iscurrentthequestionfromSeting(materialEditText_password.getText().toString())){
                    alertDialog_editpassword.dismiss();
                    initeditnewQuestiondialog();
                }else {
                    Toast.makeText(AboutActivity.this, "输入旧的密保有误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog_editpassword.show();
    }
    private void initeditnewQuestiondialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_editquestiondialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_question_edit_question);
        final TextView texttitle=(TextView)centerview.findViewById(R.id.title_text_question);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok_question);
        final AlertDialog alertDialog_editpassword=builder.setView(centerview).create();
        texttitle.setText("请输入新密保");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Means.isedittext_empty(materialEditText_password)){
                    Toast.makeText(AboutActivity.this, "请输入密保", Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.putquestionOnSeting(materialEditText_password.getText().toString());
                    alertDialog_editpassword.dismiss();
                }
            }
        });
        alertDialog_editpassword.show();
    }
    private void initiscurrentQuestiondialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_editquestiondialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_question_edit_question);
        final TextView texttitle=(TextView)centerview.findViewById(R.id.title_text_question);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok_question);
        final AlertDialog alertDialog_editpassword=builder.setView(centerview).create();
        texttitle.setText("请输入密保");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Means.isedittext_empty(materialEditText_password)){
                    Toast.makeText(AboutActivity.this, "请输入密保", Toast.LENGTH_SHORT).show();
                }else {
                    if (mPresenter.iscurrentthequestionfromSeting(materialEditText_password.getText().toString())){
                        alertDialog_editpassword.dismiss();
                        mPresenter.showthecurrentpasswordOnAboutactivity();
                    }else {
                        Toast.makeText(AboutActivity.this, "密保错误请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        alertDialog_editpassword.show();
    }
    private void initBackgroundcolorview(){//颜色选择
        niceSpinner=(NiceSpinner)this.findViewById(R.id.spiner_color_set);
        List<String>data=new LinkedList<>(Arrays.asList("默认","珊瑚朱","樱草紫","霓虹绿","绅士黑"));
        niceSpinner.attachDataSource(data);
        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        mPresenter.putcurrentcolorOnSeting(0);
                        mPresenter.setBackgroundcolorfromSeting();
                        break;
                    case 1:
                        mPresenter.putcurrentcolorOnSeting(1);
                        mPresenter.setBackgroundcolorfromSeting();
                        break;
                    case 2:
                        mPresenter.putcurrentcolorOnSeting(2);
                        mPresenter.setBackgroundcolorfromSeting();
                        break;
                    case 3:
                        mPresenter.putcurrentcolorOnSeting(3);
                        mPresenter.setBackgroundcolorfromSeting();
                        break;
                    case 4:
                        mPresenter.putcurrentcolorOnSeting(4);
                        mPresenter.setBackgroundcolorfromSeting();
                        break;
                    default:
                            break;

                }
                EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATA_COLOR));
            }
        });
    }
    private void initAboutcode(){

    }
    private void initpassworddialog(){//实例化一个创建密码和密保问题的Dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View centerview=layoutInflater.inflate(R.layout.activity_set_passworddialog,null);
        final MaterialEditText materialEditText_password=(MaterialEditText)centerview.findViewById(R.id.set_dialog_password_edit);
        final MaterialEditText materialEditText_question=(MaterialEditText)centerview.findViewById(R.id.set_dialog_question_edit);
        Button button_ok=(Button)centerview.findViewById(R.id.set_dialog_password_ok);
        alertDialog_creatpassword=builder.setView(centerview).create();
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Means.isedittext_empty(materialEditText_password)&Means.isedittext_empty(materialEditText_question)){
                    Toast.makeText(AboutActivity.this, "请输入密码和密保", Toast.LENGTH_SHORT).show();
                }else if (Means.isedittext_empty(materialEditText_password)|Means.isedittext_empty(materialEditText_question)){
                    Toast.makeText(AboutActivity.this, "请输入密码或密保", Toast.LENGTH_SHORT).show();
                }else {
                    if (materialEditText_password.getText().toString().length()!=6){
                        Toast.makeText(AboutActivity.this, "密码为6位数字", Toast.LENGTH_SHORT).show();
                    }else {
                        mPresenter.putpasswordandquestionOnSeting(materialEditText_password.getText().toString(),
                                materialEditText_question.getText().toString());
                        alertDialog_creatpassword.dismiss();
                    }
                }
            }
        });
        alertDialog_creatpassword.show();
    }

    @Override
    public void showthecurrentPassword(String password) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("密码");
        builder.setMessage(password);
        builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public Context getAboutActivityContext() {
        return this;
    }

    @Override
    public Application getAboutApplication() {
        return this.getApplication();
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer>colorlist) {
        niceSpinner.setSelectedIndex(mPresenter.getcurrentcolorNumfromSeting());
        StatusBarUtil.setColor(this,colorlist.get(0));
        toolbar_about.setBackgroundColor(colorlist.get(0));
    }

    @Override
    public void finish() {
        super.finish();
    }
}
