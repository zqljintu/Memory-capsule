package com.zql.lib_other.view.search;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jaeger.library.StatusBarUtil;
import com.zql.base.ui.mvp.BaseLifecycleActivity;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_other.R;
import com.zql.lib_other.adapter.RecyclerViewSearchAdapter;


import java.util.List;

@Route(path = RouteUrl.Url_SearchActivity)
public class SearchActivity extends BaseLifecycleActivity<SearchPresenter> implements SearchContract.view {
    private SearchView searchView;
    private Toolbar toolbar_search;
    private RecyclerView searchrecyclerView;
    private RecyclerViewSearchAdapter recyclerViewSearchAdapter;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorFloatingButton));
        initsearchview();
        inittoolbarset();
        initrecyclerview();
        mPresenter.setBackgroundcolorfromSeting();
    }

    @Override
    protected SearchPresenter getPresenter() {
        return new SearchPresenter(this);
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
        List<NoteBean>list=mPresenter.getNotebeanfromDatatoSearch(searth);
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
