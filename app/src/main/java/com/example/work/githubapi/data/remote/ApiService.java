package com.example.work.githubapi.data.remote;

import com.example.work.githubapi.data.entities.User;
import com.example.work.githubapi.data.entities.UsersData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
    @GET("search/users?")
    public Observable<UsersData> getUsersData(@Query("q")String name);

    @GET("users/{user}")
    public Observable<User> getUser(@Path("user") String user);
}
