package com.example.work.githubapi.injection;

import android.support.annotation.NonNull;

import com.example.work.githubapi.data.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ReceiversModule {

    @Provides
    @Singleton
    @NonNull
    public DataManager getDataManaget(){
        return new DataManager();}
}
