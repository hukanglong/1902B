package com.example.day01download.mvp.view;

import com.example.day01download.beans.Bean;

import java.util.List;

public interface HomeView {

    void onSuccess(List<Bean.DataBean.DatasBean> list);
    void onDefault(String msg);

}
