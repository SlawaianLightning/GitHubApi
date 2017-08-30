package com.example.work.githubapi.data;

import com.example.work.githubapi.data.entities.User;
import com.example.work.githubapi.data.entities.UsersData;

import rx.Observable;

import static com.example.work.githubapi.GitHubApp.apiServiceUserData;

public class DataManager {
    public Observable<UsersData> getUsersData(String name){
        return apiServiceUserData.getUsersData(name);
    }

    public Observable<User> getUser(String user){
        return apiServiceUserData.getUser(user);
    }
}
