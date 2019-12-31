package com.zql.app_jinnang.View;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;


import com.jaeger.library.StatusBarUtil;
import com.zql.comm.widget.SwipeActivity;

import java.util.List;

import com.zql.app_jinnang.Adapter.RecyclerViewSearchAdapter;
import com.zql.app_jinnang.Bean.NoteBean;
import com.zql.app_jinnang.Prestener.PrestenerImp_seacher;
import com.zql.app_jinnang.Prestener.Prestener_seacher;
import com.zql.app_jinnang.R;

public class SearchActivity extends SwipeActivity implements SearchActivityImp {
    private SearchView searchView;
    private Toolbar toolbar_search;
    private RecyclerView searchrecyclerView;
    private PrestenerImp_seacher prestenerImpSeacher;
    private RecyclerViewSearchAdapter recyclerViewSearchAdapter;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        StatusBarUtil.setColor(this, getColor(R.color.colorFloatingButton));
        prestenerImpSeacher=new Prestener_seacher(this);
        initview();
        prestenerImpSeacher.setBackgroundcolorfromSeting();
    }
    private void initview(){
        initsearchview();
        inittoolbarset();
        initrecyclerview();
    }
    private void inittoolbarset(){
        toolbar_search=(Toolbar)this.findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar_search);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_search.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initrecyclerview(){
        searchrecyclerView=(RecyclerView)this.findViewById(R.id.recycler_search);
    }
    private void initAdapter(String searth){
        List<NoteBean>list=prestenerImpSeacher.getNotebeanfromDatatoSearch(searth);
        if (list.size()>0){
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
            RecyclerViewSearchAdapter recyclerViewSearchAdapter=new RecyclerViewSearchAdapter(list,this);
            searchrecyclerView.setLayoutManager(layoutManager);
            searchrecyclerView.setAdapter(recyclerViewSearchAdapter);
        }else {
            searchrecyclerView.removeAllViews();
            Toast.makeText(this, "无搜索结果", Toast.LENGTH_SHORT).show();
        }
    }
    private void initsearchview(){
        searchView=(SearchView)this.findViewById(R.id.searchview_search);
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        searchView.onActionViewExpanded();
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                initAdapter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public Context getSearchActivityContext() {
        return this;
    }

    @Override
    public Application getSearchApplication() {
        return getApplication();
    }

    @Override
    public void setBackgroundcolorfromSeting(List<Integer> colorlist) {
        StatusBarUtil.setColor(this, colorlist.get(0));
        toolbar_search.setBackgroundColor(colorlist.get(0));
    }
}
