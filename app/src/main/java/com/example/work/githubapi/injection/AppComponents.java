package com.example.work.githubapi.injection;

import com.example.work.githubapi.presenter.MainPresenter;
import com.example.work.githubapi.presenter.UserPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, ReceiversModule.class})
@Singleton
public interface AppComponents {
    public void inject(MainPresenter presenter);
    public void inject(UserPresenter presenter);
}
