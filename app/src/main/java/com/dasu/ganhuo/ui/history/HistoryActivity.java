package com.dasu.ganhuo.ui.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.history.HistoryController;
import com.dasu.ganhuo.mode.logic.home.HtmlDataEntity;
import com.dasu.ganhuo.ui.base.DrawerActivity;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasu on 2017/4/18.
 *
 * 往期推荐界面
 */

public class HistoryActivity extends DrawerActivity implements OnItemClickListener<HtmlDataEntity> {

    @Override
    protected int bindMenuId() {
        return MENU_HISTORY;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initVariable();
        initView();
        mHistoryController.loadBaseData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private List<HtmlDataEntity> mHistoryList;
    private HistoryController mHistoryController;

    private void initVariable() {
        mHistoryList = new ArrayList<>();
        mHistoryController = new HistoryController(this);
    }

    private RecyclerView mHistoryRv;
    private HistoryRecycleAdapter mRecycleAdapter;

    private void initView() {
        //添加 toolbar
        addToolbar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("往期推荐");
        mHistoryRv = (RecyclerView) findViewById(R.id.rv_history_content);
        mHistoryRv.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleAdapter = new HistoryRecycleAdapter(mHistoryList);
        mRecycleAdapter.setOnItemClickListener(this);
        mHistoryRv.setAdapter(mRecycleAdapter);
    }

    public void updateHistory(List<HtmlDataEntity> data) {
        mRecycleAdapter.setData(data);
    }

    @Override
    public void onItemClick(View view, HtmlDataEntity data, int position) {
        ToastUtils.show(mContext, data.getTitle());
    }
}