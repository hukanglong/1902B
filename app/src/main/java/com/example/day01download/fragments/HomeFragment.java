package com.example.day01download.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day01download.R;
import com.example.day01download.adapters.HomeAdapter;
import com.example.day01download.beans.Bean;
import com.example.day01download.mvp.presenter.HomePresenter;
import com.example.day01download.mvp.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView {

    private List<Bean.DataBean.DatasBean> mList = new ArrayList<>();
    private HomeAdapter mHomeAdapter;
    private RecyclerView mRec;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getData();
        initView();
    }

    private void initView() {
        mRec = getActivity().findViewById(R.id.rec);
        mRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeAdapter = new HomeAdapter(getActivity(), mList);
        mRec.setAdapter(mHomeAdapter);
    }

    @Override
    public void onSuccess(List<Bean.DataBean.DatasBean> list) {
        mList.addAll(list);
        mHomeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDefault(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
