package com.example.day01download.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class VpAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> mList;

    public VpAdapter(@NonNull FragmentManager fm, Context context, List<Fragment> list) {
        super(fm);
        mContext = context;
        mList = list;
    }

    public VpAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
