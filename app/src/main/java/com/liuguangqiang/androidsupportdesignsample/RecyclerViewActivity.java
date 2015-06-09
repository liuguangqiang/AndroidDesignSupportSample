package com.liuguangqiang.androidsupportdesignsample;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.liuguangqiang.androidsupportdesignsample.adapter.ProductAdapter;
import com.liuguangqiang.androidsupportdesignsample.entity.Product;
import com.liuguangqiang.androidsupportdesignsample.view.LinearRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 15/6/1.
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private List<Product> data = new ArrayList<>();

    private ProductAdapter adapter;

    private LinearRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initToolbar();
        initData();
        initViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("RecyclerViewActivity");
    }

    private void initData() {
        Product item;
        for (int i = 0; i < 15; i++) {
            item = new Product();
            item.title = "Product Title " + i;
            data.add(item);
        }
    }

    private void initViews() {
        adapter = new ProductAdapter(getApplicationContext(), data);
        recyclerView = (LinearRecyclerView) findViewById(R.id.rv_product);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollPositionListener(new LinearRecyclerView.OnScrollPositionListener() {
            @Override
            public void onScrollToTop() {
                Log.i("LinearRecyclerView", "at top");
            }

            @Override
            public void onScrollToBottom() {
                Log.i("LinearRecyclerView", "at bottom");
            }
        });
    }

}
