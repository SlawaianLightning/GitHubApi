package com.example.work.githubapi.ui.viewstate;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.work.githubapi.data.entities.User;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface UserView extends MvpView{
    public void setData(User user);
    public void onError();
}
