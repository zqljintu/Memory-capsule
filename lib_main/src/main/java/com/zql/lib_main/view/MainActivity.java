package com.zql.lib_main.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jaeger.library.StatusBarUtil;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.MessageEvent;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.route.RouteUrl;
import com.zql.comm.widget.PasswordView.KeyPasswordView;
import com.zql.comm.widget.PasswordView.KeynumberDialog;
import com.zql.lib_main.R;
import com.zql.lib_main.adapter.ViewPagerCardAdapter;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;
import java.util.List;

@Route(path = RouteUrl.Url_MainActivity)
public class MainActivity extends BaseLifecycleActivity<MainPresenter> implements MainContract.view {

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
        initview();
        mPresenter.readNotefromDatatoMain();
        mPresenter.setBackgroundcolorfromSeting();
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
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
                mPresenter.readNotefromDatatoMain();
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
                mPresenter.openCalendarActivity();
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
    private void startEditActivity(int type, NoteBean noteBean){
        Bundle bundle=new Bundle();
        bundle.putInt("type",type);
        if (noteBean!=null){
            bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
        }
        ARouter.getInstance().build(RouteUrl.Url_EditActivity).withBundle("data",bundle).navigation();
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
                    if (mPresenter.iscurrentthepasswordfromSeting(password)){
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
                Bundle bundle=new Bundle();
                bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
                ARouter.getInstance().build(RouteUrl.Url_NoteinfoActivity).withBundle("info", bundle).navigation();
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
                mPresenter.changeNotetoPasswordFile(noteBean);
                mPresenter.readNotefromDatatoMain();
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
                mPresenter.deleteNoteBean(noteBean);
                mPresenter.readNotefromDatatoMain();
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
        int itemId = item.getItemId();
        if (itemId == R.id.action_list) {
            mPresenter.openListActivity();
            return true;
        } else if (itemId == R.id.action_seting) {
            mPresenter.openSetiongActivity();
            return true;
        } else if (itemId == R.id.action_search) {
            mPresenter.openSearchActivity();
            return true;
        } else if (itemId == R.id.action_serect) {
            initEditpassworddialog();
            return true;
        } else if (itemId == R.id.action_chart) {
            initChartActiviy();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override//打开新的搜索界面
    public void startSearchActivity() {
        ARouter.getInstance().build(RouteUrl.Url_SearchActivity).navigation();
    }
    //打开数据图表分析界面
    private void initChartActiviy(){
        ARouter.getInstance().build(RouteUrl.Url_DateChartActivity).navigation();
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
                mPresenter.readNotefromDatatoMain();
                break;
            case MessageEvent.UPDATA_COLOR:
                mPresenter.setBackgroundcolorfromSeting();
                break;
                default:
                    break;

        }
    }

    @Override
    public void startSetingActivity() {
        ARouter.getInstance().build(RouteUrl.Url_AboutActivity).navigation();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override//设置背景的颜色
    public void setMainBackground(Integer integer) {
        relativeLayout.setBackground(getDrawable(integer));
    }

    @Override//打开新的ListActivity
    public void startListActivity() {
        ARouter.getInstance().build(RouteUrl.Url_ListActivity).navigation();
    }

    @Override//打开秘密ListActivity
    public void startListSecretActivity() {
        ARouter.getInstance().build(RouteUrl.Url_ListSeActivity).navigation();
    }

    @Override//打开日历CalendarActivity
    public void startCalendarActivity() {
        ARouter.getInstance().build(RouteUrl.Url_CalendarActivity).withInt("UPDATE",0).navigation();
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
        adapter=new ViewPagerCardAdapter(this,noteBeanList,mPresenter);
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
