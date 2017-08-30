package com.example.work.githubapi;

import android.app.Application;

import com.example.work.githubapi.data.remote.ApiService;
import com.example.work.githubapi.data.remote.RetroClient;
import com.example.work.githubapi.injection.AppComponents;
import com.example.work.githubapi.injection.AppModule;
import com.example.work.githubapi.injection.DaggerAppComponents;

public class GitHubApp extends Application {
    private static AppComponents sAppComponent;
    public static ApiService apiServiceUserData= RetroClient.getApiServiceUserData();
    public static AppComponents getAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponents.builder().appModule(new AppModule(this)).build();
    }
}
