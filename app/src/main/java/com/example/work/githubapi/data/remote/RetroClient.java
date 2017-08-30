package com.example.work.githubapi.data.remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class RetroClient {
    private static final String baseUrl="https://api.github.com/";

    private static Retrofit getRetrofitInstaceUserSearch(){
        return new Retrofit.Builder().baseUrl(baseUrl).
                addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiService getApiServiceUserData(){
        return getRetrofitInstaceUserSearch().create(ApiService.class);
    }
}
