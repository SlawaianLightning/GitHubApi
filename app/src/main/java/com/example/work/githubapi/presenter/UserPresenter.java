package com.example.work.githubapi.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.work.githubapi.GitHubApp;
import com.example.work.githubapi.data.DataManager;
import com.example.work.githubapi.data.entities.User;
import com.example.work.githubapi.ui.viewstate.UserView;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

@InjectViewState
public class UserPresenter extends BasePresenter<UserView> {
    @Inject DataManager dataManager;
    private String login;

    public UserPresenter(){
        GitHubApp.getAppComponent().inject(this);
    }

    public void tryAgain(){
        loadUserInformation(login);
    }

    public void loadUserInformation(String login){
        this.login=login;
        dataManager.getUser(login).
                onErrorResumeNext( Observable.create(s->{
                            s.onNext(new User());
                            s.onCompleted();
                        }
                )).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(s -> {
                    if(s.getLogin()!=null){
                    getViewState().setData(s);}else {
                        getViewState().onError();
                    }
                });
    }
}
