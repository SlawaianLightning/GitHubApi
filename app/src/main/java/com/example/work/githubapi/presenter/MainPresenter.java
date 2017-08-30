package com.example.work.githubapi.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.work.githubapi.GitHubApp;
import com.example.work.githubapi.data.DataManager;
import com.example.work.githubapi.data.entities.Item;
import com.example.work.githubapi.data.entities.UsersData;
import com.example.work.githubapi.ui.viewstate.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView>{
    @Inject DataManager dataManager;
    private List<Item> users=new ArrayList<>();

    public MainPresenter(){
        GitHubApp.getAppComponent().inject(this);
    }

    public List<Item> getUsers(){
        return users;
    }

    public void searchUser(String query){
        if(users.size()==0){
        dataManager.getUsersData(query).
                onErrorResumeNext( Observable.create(s->{
                            s.onNext(new UsersData());
                            s.onCompleted();
                        }
                )).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(s -> {
                    if(s.getItems()!=null){
                        users=s.getItems();
                        getViewState().setUsers(s.getItems());
                        if(users.size()==0){
                            getViewState().setText("No such user!");
                            getViewState().setText(null);
                        }
                    }else {
                        getViewState().setText("Connection error!");
                        getViewState().setText(null);
                        getViewState().setUsers(users);
                    }
                });
    }else {
            getViewState().setUsers(users);
        }
    }

    public void clearData(){
        users.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
