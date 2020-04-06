package com.bawei.week3moni.activity;

import android.view.View;
import android.widget.EditText;

import com.bawei.week3moni.R;
import com.bawei.week3moni.base.BaseActivity;
import com.bawei.week3moni.base.BasePresenter;
import com.bawei.week3moni.view.SearchHistoryView;

public class SearchActivity extends BaseActivity {
    //定义
    private EditText edit_search;
    private SearchHistoryView search_history_view;
    //方法实现
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initView() {
        //获取id
        edit_search = findViewById(R.id.edit_search);
        search_history_view = findViewById(R.id.search_history_view);
        isShowBack(true);
        search_history_view.setEditSearchTextListener(new SearchHistoryView.EditSearchTextListener() {
            @Override
            public void editSearchText(String text) {
                edit_search.setText(text);
            }
        });
    }
    @Override
    protected String getAppTitle() {
        return "搜索";
    }
    //添加
    public void searchGo(View view) {
        search_history_view.addHistory(edit_search.getText().toString());
    }
    //清空
    public void emptySearchHistory(View view) {
        search_history_view.removeAllViews();
    }
}
