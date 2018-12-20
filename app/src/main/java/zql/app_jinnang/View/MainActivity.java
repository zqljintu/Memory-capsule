package zql.app_jinnang.View;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.jaeger.library.StatusBarUtil;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import zql.app_jinnang.Adapter.ViewPagerCardAdapter;
import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.MessageEvent;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Prestener.Prestener_main;
import zql.app_jinnang.Prestener.PrestenerImp_main;
import zql.app_jinnang.R;
import zql.app_jinnang.Service.PasswordView.KeyPasswordView;
import zql.app_jinnang.Service.PasswordView.KeynumberDialog;


public class MainActivity extends AppCompatActivity implements MainActivityImp{
    private PrestenerImp_main prestenerImpMain;
    private FloatingActionsMenu floatingActionsmenu_add;
    private FloatingActionButton floatingActionButton_work,floatingActionButton_study,floatingActionButton_live,floatingActionButton_diarly,floatingActionButton_travel,floatingActionButton_calendar;
    private AddFloatingActionButton addFloatingActionButton;
    private LinearLayout mainbottomlinearlayout,voicebuttonlayout;
    private ViewPager viewPagercard;
    private ViewPagerCardAdapter adapter;
    private Toolbar toolbar_main;
    private TextView title_toolbar_main,text_refresh;
    private RelativeLayout relativeLayout;
    private Integer maincolor;
    private String password="";
    private int count_delete;

    private static final int REQUEST_UPDATE=2;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this, getColor(R.color.colorFloatingButton));
        prestenerImpMain =new Prestener_main(this);
        initview();
        prestenerImpMain.readNotefromDatatoMain();
        prestenerImpMain.setBackgroundcolorfromSeting();
        EventBus.getDefault().register(this);
    }
    private void initview(){//具体的View实现生成的函数
        initToolBarSeting();
        initFloatingActionButton();
        initViewPagercard();
        initrefresh();
    }
    private void initToolBarSeting(){//实现ToolBar的生成
        relativeLayout=(RelativeLayout)this.findViewById(R.id.relativeLayout_main);
        toolbar_main=(Toolbar)this.findViewById(R.id.toolbar_main);
        title_toolbar_main=(TextView)findViewById(R.id.title_toolbar_main);
        YoYo.with(Techniques.DropOut)
                .duration(700)
                .playOn(findViewById(R.id.title_toolbar_main));
        setSupportActionBar(toolbar_main);
    }
    private void initrefresh(){//实现刷新
        text_refresh=(TextView)this.findViewById(R.id.main_refresh);
        text_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prestenerImpMain.readNotefromDatatoMain();
            }
        });
    }
    private void initFloatingActionButton(){//实现FloatingActionButton的生成
        floatingActionButton_calendar=(FloatingActionButton)this.findViewById(R.id.floatingbutton_calendar);
        floatingActionsmenu_add=(FloatingActionsMenu)this.findViewById(R.id.floatinbuttonmenu_add);
        floatingActionButton_work=(FloatingActionButton)this.findViewById(R.id.floatingbutton_work);
        floatingActionButton_study=(FloatingActionButton)this.findViewById(R.id.floatingbutton_study);
        floatingActionButton_live=(FloatingActionButton)this.findViewById(R.id.floatingbutton_live);
        floatingActionButton_diarly=(FloatingActionButton)this.findViewById(R.id.floatingbutton_diary);
        floatingActionButton_travel=(FloatingActionButton)this.findViewById(R.id.floatingbutton_travel);
        floatingActionButton_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestenerImpMain.openCalendarActivity();
            }
        });
        floatingActionButton_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditActivity(0,null);
                floatingActionsmenu_add.collapse();
            }
        });
        floatingActionButton_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditActivity(1,null);
                floatingActionsmenu_add.collapse();
            }
        });
        floatingActionButton_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditActivity(2,null);
                floatingActionsmenu_add.collapse();
            }
        });
        floatingActionButton_diarly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditActivity(3,null);
                floatingActionsmenu_add.collapse();
            }
        });
        floatingActionButton_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditActivity(4,null);
                floatingActionsmenu_add.collapse();
            }
        });

    }
    private void startEditActivity(int type,NoteBean noteBean){
        Intent addintent=new Intent(MainActivity.this,EditActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("type",type);
        if (noteBean!=null){
            bundle.putSerializable("noteinfo",Means.changefromNotebean(noteBean));
        }
        addintent.putExtra("data",bundle);
        startActivity(addintent);
    }
    private void initEditpassworddialog(){//实例化一个重新编辑密码的dialog
        if (!password.isEmpty()){
            password="";
        }
        final BottomSheetDialog passwordbottomsheetdialog=new BottomSheetDialog(MainActivity.this);
        View dialogview=LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.sheetdialog_password,null);
        final KeynumberDialog keynumberDialog=(KeynumberDialog)dialogview.findViewById(R.id.main_sheetdialog_keynumber);
        final KeyPasswordView keyPasswordView=(KeyPasswordView)dialogview.findViewById(R.id.main_sheetdialog_passwordview);
        keyPasswordView.setRouldRectcolor(maincolor);
        keynumberDialog.setOnNumberClickListener(new KeynumberDialog.OnNumberClickListener() {
            @Override
            public void onNumberReturn(String number) {
                password+=number;
                if (password.length()==6){
                    if (prestenerImpMain.iscurrentthepasswordfromSeting(password)){
                        startListSecretActivity();
                        password="";
                        passwordbottomsheetdialog.dismiss();
                    }else {
                        Toast.makeText(MainActivity.this, "输入密码有误", Toast.LENGTH_SHORT).show();
                        password="";
                    }
                }
                keyPasswordView.changeTheNum(password.length());
            }

            @Override
            public void onNumberDelete() {
                if (password.length()<=1){
                    password="";
                }else {
                    password=password.substring(0,password.length()-1);
                }
                keyPasswordView.changeTheNum(password.length());
            }
        });
        passwordbottomsheetdialog.setContentView(dialogview);
        passwordbottomsheetdialog.show();

    }
    private void initViewPagercard(){//实现ViewPagerCard的生成
        viewPagercard=(ViewPager)this.findViewById(R.id.viewpager_main);
        viewPagercard.setPageMargin((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,24,getResources().getDisplayMetrics()));
        viewPagercard.setPageTransformer(false,new ScaleTransformer0(this));
    }
    private void initBottomDialog(final NoteBean noteBean){//创建底部弹出的窗口
        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity.this);
        View dialogview= LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.activity_main_dialog,null);
        LinearLayout main_dialog_linear_about,main_dialog_linear_hide,main_dialog_linear_delete,main_dialog_linear_change,main_dialog_friendspace,main_dialog;
        main_dialog=(LinearLayout)dialogview.findViewById(R.id.main_dialog);
        main_dialog_linear_about=(LinearLayout)dialogview.findViewById(R.id.main_dialog_linear_about);
        main_dialog_friendspace=(LinearLayout)dialogview.findViewById(R.id.main_dialog_linear_friendspace);
        main_dialog_linear_hide=(LinearLayout)dialogview.findViewById(R.id.main_dialog_linear_hide);
        main_dialog_linear_delete=(LinearLayout)dialogview.findViewById(R.id.main_dialog_linear_delete);
        main_dialog_linear_change=(LinearLayout)dialogview.findViewById(R.id.main_dialog_linear_change);
        main_dialog_linear_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent=new Intent(MainActivity.this,NoteinfoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
                mintent.putExtras(bundle);
                startActivity(mintent);
                bottomSheetDialog.dismiss();
            }
        });
        main_dialog_friendspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedNotetexttoWeChart(noteBean.getNoteinfo());
                bottomSheetDialog.dismiss();
            }
        });
        main_dialog_linear_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPasswordFileDialog(noteBean);
                bottomSheetDialog.dismiss();
            }
        });
        main_dialog_linear_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDeleteDialog(noteBean);
                bottomSheetDialog.dismiss();
            }
        });
        main_dialog_linear_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startEditActivity(10,noteBean);
               bottomSheetDialog.dismiss();
            }
        });
        main_dialog.setBackgroundColor(maincolor);
        bottomSheetDialog.setContentView(dialogview);
        bottomSheetDialog.show();
    }
    private void sharedNotetexttoWeChart(String text){//将便签文字分享到微信朋友圈
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        startActivity(Intent.createChooser(intent,"share"));
    }
    private void initPasswordFileDialog(final NoteBean noteBean){//将便签加入到私密文件夹中
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("隐藏");
        builder.setMessage("确认隐藏？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                count_delete=viewPagercard.getCurrentItem();//做一个跳转优化
                prestenerImpMain.changeNotetoPasswordFile(noteBean);
                prestenerImpMain.readNotefromDatatoMain();
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
    private void initDeleteDialog(final NoteBean noteBean){//删除选定的便签
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("删除");
        builder.setMessage("确定删除？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                count_delete=viewPagercard.getCurrentItem();//做一个跳转优化
                prestenerImpMain.deleteNoteBean(noteBean);
                prestenerImpMain.readNotefromDatatoMain();
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

    @Override//创建菜单目录
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu.getClass() == MenuBuilder.class) {
            try {
                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                method.setAccessible(true);
                method.invoke(menu, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override//设置监听菜单
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_list:
                prestenerImpMain.openListActivity();
                return true;
            case R.id.action_seting:
                prestenerImpMain.openSetiongActivity();
                return true;
            case R.id.action_search:
                prestenerImpMain.openSearchActivity();
                return true;
            case R.id.action_serect:
                initEditpassworddialog();
                return true;
            case R.id.action_chart:
                initChartActiviy();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override//打开新的搜索界面
    public void startSearchActivity() {
        Intent mintent=new Intent(this,SearchActivity.class);
        startActivity(mintent);
    }
    //打开数据图表分析界面
    private void initChartActiviy(){
        Intent mintent=new Intent(MainActivity.this,DataChartActivity.class);
        this.startActivity(mintent);
    }


    @Override
    public Context getActivity_this() {
        return this;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * EventBus接受事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handEvent(MessageEvent messageEvent){
        switch (messageEvent.getMessageevent()){
            case MessageEvent.UPDATE_DATA:
                prestenerImpMain.readNotefromDatatoMain();
                break;
            case MessageEvent.UPDATA_COLOR:
                prestenerImpMain.setBackgroundcolorfromSeting();
                break;
                default:
                    break;

        }
    }

    @Override
    public void startSetingActivity() {
        Intent mintet=new Intent(MainActivity.this,AboutActivity.class);
        this.startActivity(mintet);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override//设置背景的颜色
    public void setMainBackground(Integer integer) {
        relativeLayout.setBackground(getDrawable(integer));
    }

    @Override//打开新的ListActivity
    public void startListActivity() {
        Intent mintent=new Intent(MainActivity.this,ListActivity.class);
        this.startActivityForResult(mintent,REQUEST_UPDATE);
    }

    @Override//打开秘密ListActivity
    public void startListSecretActivity() {
        Intent mintent=new Intent(MainActivity.this,ListSecretActivity.class);
        this.startActivity(mintent);
    }

    @Override//打开日历CalendarActivity
    public void startCalendarActivity() {
        Intent mintent=new Intent(MainActivity.this,CalendarActivity.class);
        mintent.putExtra("UPDATE",0);
        this.startActivity(mintent);
    }

    public class ScaleTransformer0 implements ViewPager.PageTransformer {//改变形状的透明度
        private Context context;
        private float elevation;

        public ScaleTransformer0(Context context) {
            this.context = context;
            elevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    20, context.getResources().getDisplayMetrics());
        }

        @Override
        public void transformPage(View page, float position) {
            if (position < -1 || position > 1) {

            } else {
                if (position < 0) {
                    ((CardView) page).setCardElevation((1 + position) * elevation);
                } else {
                    ((CardView) page).setCardElevation((1 - position) * elevation);
                }
            }
        }
    }
    @Override
    public void openSheetDialog(NoteBean noteBean) {
        initBottomDialog(noteBean);
    }

    @Override
    public void readNotefromData(List<NoteBean> noteBeanList) {
        setMainBackgroundIcon(noteBeanList.size());
        adapter=new ViewPagerCardAdapter(this,noteBeanList,this,prestenerImpMain);
        viewPagercard.setAdapter(adapter);

        /**
         * 当为删除事件时，count_delete为大于1，否则为0，在进入界面初始化的时候，我们会把count_delete设置为0
         * */

       if (count_delete>=1){//优化删除（删除完成后跳转到上一个事件界面）
            viewPagercard.setCurrentItem(count_delete-1);
        }else {
            viewPagercard.setCurrentItem(0);
        }

    }

    @Override
    public Application getMainApplication() {
        return getApplication();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setBackgroundcolorfromSeting(List<Integer>mlist) {
        maincolor=mlist.get(0);//设置主体颜色
        StatusBarUtil.setColor(this, mlist.get(0));
        toolbar_main.setBackgroundColor(mlist.get(0));
        relativeLayout.setBackgroundColor(mlist.get(1));
        floatingActionButton_diarly.setColorNormal(mlist.get(0));
        floatingActionButton_live.setColorNormal(mlist.get(0));
        floatingActionButton_study.setColorNormal(mlist.get(0));
        floatingActionButton_travel.setColorNormal(mlist.get(0));
        floatingActionButton_work.setColorNormal(mlist.get(0));
    }

    @Override
    public void setMainBackgroundIcon(int size) {
        LinearLayout linearlayout_listempty=(LinearLayout)findViewById(R.id.linearlayout_listEmpty);
        if (size==0){
            linearlayout_listempty.setVisibility(View.VISIBLE);
        }else {
            linearlayout_listempty.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
