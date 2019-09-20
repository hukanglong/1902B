package com.example.day01download.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.day01download.MyService;
import com.example.day01download.R;
import com.example.day01download.beans.MsgBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownLoadFragment extends Fragment {


    @BindView(R.id.pro)
    ProgressBar mPro;
    @BindView(R.id.text_down)
    TextView mTextDown;
    @BindView(R.id.btn_down)
    Button mBtnDown;
    private Unbinder mUnbinder;

    public DownLoadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_down_load, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initView();
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMsg(MsgBean msg){
        int schedule = msg.getSchedule();
        mPro.setProgress(schedule);
        mTextDown.setText("当前下载："+schedule+"%");
        if(schedule == 100){
            mTextDown.setText("下载完成");
            Toast.makeText(getActivity(), "下载完成", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        mBtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startService(new Intent(getActivity(), MyService.class));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
