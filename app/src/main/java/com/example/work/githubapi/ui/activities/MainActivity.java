package com.example.work.githubapi.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.work.githubapi.R;
import com.example.work.githubapi.data.entities.Item;
import com.example.work.githubapi.presenter.MainPresenter;
import com.example.work.githubapi.ui.adapters.UsersAdapter;
import com.example.work.githubapi.ui.viewstate.MainView;
import com.example.work.githubapi.utills.ItemClickSupport;
import com.example.work.githubapi.utills.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter MainPresenter presenter;
    @BindView(R.id.rvUsers) RecyclerView rvUsers;
    @BindView(R.id.tvListInformation) TextView tvListInformation;
    @BindView(R.id.pbUpdated) ProgressBar pbUpdated;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Search users");
        usersAdapter=new UsersAdapter(this);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(usersAdapter);
        showDefaultValue();
        ItemClickSupport.addTo(rvUsers).setOnItemClickListener((rvUsers,position,view)->{
            Intent intent=new Intent(MainActivity.this,UserActivity.class);
            intent.putExtra(StringUtils.LOGIN, presenter.getUsers().get(position).getLogin());
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem menuItem=menu.findItem(R.id.search_view);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                load();
                presenter.clearData();
                presenter.searchUser(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void setUsers(List<Item> users) {
        usersAdapter.updateUsers(users);
        if(users.size()!=0){
            show();
        }else {
            showDefaultValue();
        }
    }

    @Override
    public void setText(String text) {
        if(text!=null){
        Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();}
    }

    private void load(){
        pbUpdated.setVisibility(View.VISIBLE);
        rvUsers.setVisibility(View.INVISIBLE);
        tvListInformation.setVisibility(View.INVISIBLE);
    }

    private void show(){
        pbUpdated.setVisibility(View.INVISIBLE);
        rvUsers.setVisibility(View.VISIBLE);
        tvListInformation.setVisibility(View.INVISIBLE);
    }

    private void showDefaultValue(){
        pbUpdated.setVisibility(View.INVISIBLE);
        rvUsers.setVisibility(View.INVISIBLE);
        tvListInformation.setVisibility(View.VISIBLE);
    }
}
