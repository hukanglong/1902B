package com.example.day01download.mvp.presenter;

import com.example.day01download.CallBack;
import com.example.day01download.beans.Bean;
import com.example.day01download.mvp.model.HomeModel;
import com.example.day01download.mvp.view.HomeView;

import java.util.List;

public class HomePresenter implements CallBack {

    private HomeView mHomeView;
    private final HomeModel mHomeModel;

    public HomePresenter(HomeView homeView) {
        mHomeView = homeView;
        mHomeModel = new HomeModel();
    }

    public void getData(){
        mHomeModel.getData(this);
    }

    @Override
    public void onSuccess(List<Bean.DataBean.DatasBean> list) {
        mHomeView.onSuccess(list);
    }

    @Override
    public void onDefault(String msg) {
        mHomeView.onDefault(msg);
    }
}
