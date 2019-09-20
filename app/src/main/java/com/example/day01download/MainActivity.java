package com.example.day01download;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day01download.adapters.VpAdapter;
import com.example.day01download.fragments.DownLoadFragment;
import com.example.day01download.fragments.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.tab)
    TabLayout mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initVp();
        initTab();
    }

    private void initVp() {
        List<Fragment> views = new ArrayList<>();
        views.add(new HomeFragment());
        views.add(new DownLoadFragment());
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), this, views);
        mVp.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mVp);
    }

    private void initTab() {
        mTab.getTabAt(0).setText("首页").setIcon(R.drawable.select_home);
        mTab.getTabAt(1).setText("我的").setIcon(R.drawable.select_down);

        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        mToolbar.setTitle("首页");
                        break;
                    case 1:
                        mToolbar.setTitle("我的");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initToolbar() {
        mToolbar.setTitle("首页");
        setSupportActionBar(mToolbar);
    }
}
