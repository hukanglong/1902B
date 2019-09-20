package com.example.day01download;

import com.example.day01download.beans.Bean;

import java.util.List;

public interface CallBack {

    void onSuccess(List<Bean.DataBean.DatasBean> list);
    void onDefault(String msg);

}
