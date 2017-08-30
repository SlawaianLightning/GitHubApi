package com.example.work.githubapi.ui.viewstate;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.work.githubapi.data.entities.Item;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView{
    public void setUsers(List<Item> users);
    public void setText(String text);
}
