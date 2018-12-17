package zql.app_jinnang.View;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import zql.app_jinnang.Adapter.RecyclerViewCardAdapter;
import zql.app_jinnang.Adapter.RecyclerViewSecretCardAdapter;
import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Prestener.PrestenerImp_listserect;
import zql.app_jinnang.Prestener.Prestener_listserect;
import zql.app_jinnang.R;

public class ListSecretActivity extends SwipeActivity implements ListSecretActivityImp{
    private Toolbar toolbar_listsecret;
    private PrestenerImp_listserect prestenerImp_listserect;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_secret);
        prestenerImp_listserect=new Prestener_listserect(this);
        initview();
        prestenerImp_listserect.readNoteserectfromDatatoList();
        prestenerImp_listserect.setBackgroundcolorfromSeting();
    }
    private void initview(){//具体view的实现
        inittoolbarSeting();
        initRecyclerView();
    }
    private void inittoolbarSeting(){//对toolbar的实例化
        toolbar_listsecret=(Toolbar) this.findViewById(R.id.toolbar_list_secret);
        setSupportActionBar(toolbar_listsecret);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_listsecret.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initBottomMenu(final NoteBean noteBean){//Bottomsheetdialog进行实例化
        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
        final View dialogview= LayoutInflater.from(this)
                .inflate(R.layout.activity_secret_dialog,null);
        LinearLayout list_dialog_linear_about,list_dialog_linear_hide,list_dialog_linear_delete,list_dialog_linear_change;
        list_dialog_linear_about=(LinearLayout)dialogview.findViewById(R.id.secret_dialog_linear_about);
        list_dialog_linear_delete=(LinearLayout)dialogview.findViewById(R.id.secret_dialog_linear_delete);
        list_dialog_linear_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent=new Intent(ListSecretActivity.this,NoteinfoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
                mintent.putExtras(bundle);
                startActivity(mintent);
                bottomSheetDialog.dismiss();
            }
        });
        list_dialog_linear_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDeleteDialog(noteBean);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(dialogview);
        bottomSheetDialog.show();
    }
    private void initDeleteDialog(final NoteBean noteBean){//加入一个dialog，来询问是否要删除。
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("删除");
        builder.setMessage("确定删除");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                prestenerImp_listserect.deleteNotebeanserect(noteBean);
                prestenerImp_listserect.readNoteserectfromDatatoList();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void opensheetdialog(NoteBean noteBean) {
        initBottomMenu(noteBean);
    }

    private void initRecyclerView(){//对recycerView实例化
        recyclerView=(RecyclerView)this.findViewById(R.id.recycler_list_secret);
    }

    @Override
    public void readAllNoteSerectfromData(List<NoteBean> noteBeanList) {
        recyclerView.removeAllViews();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerViewSecretCardAdapter recyclerViewSecretCardAdapter=new RecyclerViewSecretCardAdapter((ArrayList<NoteBean>) noteBeanList,this,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewSecretCardAdapter);
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer> colorlist) {
        StatusBarUtil.setColor(this, colorlist.get(0));
        toolbar_listsecret.setBackgroundColor(colorlist.get(0));
    }

    @Override
    public Application getListSerectApplication() {
        return getApplication();
    }

    @Override
    public Context getListSerectActivityContext() {
        return this;
    }
}
