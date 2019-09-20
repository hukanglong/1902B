package com.example.day01download;

import com.example.day01download.beans.Bean;

import org.greenrobot.eventbus.Subscribe;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface ApiService {

    //https://www.wanandroid.com/project/list/1/json?cid=294
    public String url = "https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Observable<Bean> getData();

    //https://cdn.banmi.com/banmiapp/apk/banmi_330.apk
    public String apkUrl = "https://cdn.banmi.com/";
    @Streaming
    @GET("banmiapp/apk/banmi_330.apk")
    Observable<ResponseBody> getApk();

}
