package com.liuguangqiang.androidsupportdesignsample;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.liuguangqiang.androidsupportdesignsample.adapter.BaseRecyclerAdapter;
import com.liuguangqiang.androidsupportdesignsample.adapter.SampleAdapter;
import com.liuguangqiang.androidsupportdesignsample.entity.Sample;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BaseRecyclerAdapter.OnItemClickListener {

    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;

    private RecyclerView recyclerView;
    private List<Sample> sampleList = new ArrayList<>();
    private SampleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initToolbar();

        ImageView iv = new ImageView(getApplicationContext());
        Picasso.with(getApplicationContext()).load("").into(iv);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu_white);
        setTitle("Android Support Design Sample");
    }

    private void initViews() {
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar(v, "Hello ! I am SnackBar !");
            }
        });

        initSampleList();
        adapter = new SampleAdapter(getApplicationContext(), sampleList);
        recyclerView = (RecyclerView) findViewById(R.id.rv_sample);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void initSampleList() {
        String[] titles = getResources().getStringArray(R.array.samples);
        Sample sample;
        for (String title : titles) {
            sample = new Sample();
            sample.title = title;
            sampleList.add(sample);
        }
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(getApplicationContext(), RecyclerViewActivity.class));
                break;
            case 1:
                startActivity(new Intent(getApplicationContext(), ScrollViewActivity.class));
                break;
            case 2:
                startActivity(new Intent(getApplicationContext(), TextInputLayoutActivity.class));
                break;
            case 3:
                startActivity(new Intent(getApplicationContext(), DataBindingActivity.class));
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerlayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSnackBar(View view, final String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        }).show();
    }

}
