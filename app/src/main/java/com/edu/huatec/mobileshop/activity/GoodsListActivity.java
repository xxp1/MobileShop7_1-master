package com.edu.huatec.mobileshop.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.huatec.mobileshop.R;
import com.edu.huatec.mobileshop.adapter.GoodsListAdapter;
import com.edu.huatec.mobileshop.common.BaseActivity;
import com.edu.huatec.mobileshop.http.entity.GoodsEntity;
import com.edu.huatec.mobileshop.http.presenter.GoodsPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

public class GoodsListActivity extends BaseActivity {


    private int cat_id;

    @BindView(R.id.goodslist_swipe_refresh)
    SwipeRefreshLayout goodslistSwipeRefresh;
    @BindView(R.id.goodslist_recyclerview)
    RecyclerView goodslistRecyclerview;
    @BindView(R.id.goodslist_nodata)
    TextView goodsListNoData;

    private List<GoodsEntity> listData;
    private GoodsListAdapter adapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_goodslist;
    }

    @OnClick(R.id.iv_back)
    void close() {
        finish();
    }

    @Override
    protected void initView() {
        super.initView();
        //初始隐藏no data
        goodsListNoData.setVisibility(View.GONE);


        //从商品分类界面传递过来的的参数
        cat_id = getIntent().getIntExtra("cat_id", 0);

        //设置刷新样式
        goodslistSwipeRefresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //下拉刷新监听器
        goodslistSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                loadData();
            }
        });


        //设置列表样式
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        goodslistRecyclerview.setLayoutManager(layoutManager);

        listData = new ArrayList<GoodsEntity>();
        adapter = new GoodsListAdapter(this, listData);
        adapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, GoodsEntity entity) {
                //跳转到商品列表界面
                Intent intent = new Intent(GoodsListActivity.this, GoodsDetailActivity.class);
                intent.putExtra("goods_id", entity.getGoods_id());
                intent.putExtra("goods_name", entity.getName());
                startActivity(intent);
            }
        });
        goodslistRecyclerview.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        //首次进入页面刷新数据
        loadData();
    }

    private void loadData() {
        GoodsPresenter.list(new Subscriber<List<GoodsEntity>>() {
            @Override
            public void onCompleted() {
                goodslistSwipeRefresh.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                goodslistSwipeRefresh.setRefreshing(false);
            }

            @Override
            public void onNext(List<GoodsEntity> goodsEntities) {
                listData.clear();
                listData.addAll(goodsEntities);
                adapter.notifyDataSetChanged();

                if (listData == null || listData.size() == 0) {
                    toastShort("没有该列表的商品数据！");
                    goodsListNoData.setVisibility(View.VISIBLE);
                    goodslistRecyclerview.setVisibility(View.GONE);
                } else {
                    goodsListNoData.setVisibility(View.GONE);
                    goodslistRecyclerview.setVisibility(View.VISIBLE);
                }
            }
        }, cat_id);
    }

}
