package com.example.day01download;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.day01download.beans.MsgBean;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyService extends IntentService {


    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.apkUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<ResponseBody> apk = apiService.getApk();
        apk.subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("tag", "onSubscribe: 开始下载");
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                Log.i("tag", "onSubscribe: 正在下载");

                try {
                    File filesDir = getApplication().getFilesDir();
                    File file = new File(filesDir, "a.apk");
                    long length = responseBody.contentLength();
                    InputStream inputStream = responseBody.byteStream();
                    FileOutputStream out = new FileOutputStream(file);
                    byte[] bytes = new byte[1024*1024];
                    int line = 0;
                    int num = 0;
                    while ((line = inputStream.read(bytes))!=-1){
                        out.write(bytes,0,line);
                        num+=line;
                        int schedule = (int) (num * 1.0f / length * 100);
                        Log.i("tag", "onNext: "+schedule);
                        EventBus.getDefault().post(new MsgBean(schedule));
                    }
                    inputStream.close();
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i("tag", "onSubscribe: 下载错误");
            }

            @Override
            public void onComplete() {
                Log.i("tag", "onSubscribe: 下载完成");
            }
        });
    }
}
