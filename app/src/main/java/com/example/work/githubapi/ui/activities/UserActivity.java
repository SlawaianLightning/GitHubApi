package com.example.work.githubapi.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.work.githubapi.R;
import com.example.work.githubapi.data.entities.User;
import com.example.work.githubapi.presenter.UserPresenter;
import com.example.work.githubapi.ui.viewstate.UserView;
import com.example.work.githubapi.utills.StringUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends MvpAppCompatActivity implements UserView{

    @InjectPresenter UserPresenter presenter;
    @BindView(R.id.cvMainInformation) CardView cvMainInformation;
    @BindView(R.id.cvAdditionalInformation) CardView cvAdditionalInformation;
    @BindView(R.id.cvDates) CardView cvDates;
    @BindView(R.id.ivAvatar) ImageView ivAvatar;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvCompany) TextView tvCompany;
    @BindView(R.id.tvLocation) TextView tvLocation;
    @BindView(R.id.tvEmail) TextView tvEmail;
    @BindView(R.id.tvPublicRepos) TextView tvPublicRepos;
    @BindView(R.id.tvDateCreated) TextView tvDateCreated;
    @BindView(R.id.tvDateUpdated) TextView tvDateUpdated;
    @BindView(R.id.pbUpdated) ProgressBar pbUpdated;
    @BindView(R.id.tvTextError) TextView tvTextError;
    @BindView(R.id.bError) Button bError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        Intent i=getIntent();
        Bundle extras = i.getExtras();
        String login=extras.getString(StringUtils.LOGIN,"");
        getSupportActionBar().setTitle(login);
        presenter.loadUserInformation(login);
        load();
    }

    @Override
    public void setData(User user) {
        Picasso.with(getApplicationContext()).
                load(user.getAvatarUrl()).
                placeholder(R.drawable.default_picture).
                resize(100, 100).
                into(ivAvatar);
        if(user.getName()==null){
            tvName.setText("name    no data");
        }else {
            tvName.setText("name    "+user.getName());
        }
        if(user.getLocation()==null){
            tvLocation.setText("location    no data");
        }else{
            tvLocation.setText("location    "+user.getLocation());
        }
        if(user.getCompany()==null){
            tvCompany.setText("company          no data");
        }else {
            tvCompany.setText("company      "+user.getCompany());
        }
        if(user.getEmail()==null){
            tvEmail.setText("email      no data");
        }else{
            tvEmail.setText("email      "+user.getEmail());
        }
        tvPublicRepos.setText("public repositors        "+String.valueOf(user.getPublicRepos()));
        tvDateCreated.setText("date created         "+user.getCreatedAt().replace("T"," ").replace("Z",""));
        tvDateUpdated.setText("date updated         "+user.getUpdatedAt().replace("T"," ").replace("Z",""));
        show();
    }

    @OnClick(R.id.bError)
    public void onErrorClick(View view) {
        load();
        presenter.tryAgain();
    }

    private void load(){
        cvMainInformation.setVisibility(View.INVISIBLE);
        cvAdditionalInformation.setVisibility(View.INVISIBLE);
        cvDates.setVisibility(View.INVISIBLE);
        tvName.setVisibility(View.INVISIBLE);
        tvCompany.setVisibility(View.INVISIBLE);
        tvLocation.setVisibility(View.INVISIBLE);
        tvEmail.setVisibility(View.INVISIBLE);
        tvPublicRepos.setVisibility(View.INVISIBLE);
        tvDateCreated.setVisibility(View.INVISIBLE);
        tvDateUpdated.setVisibility(View.INVISIBLE);
        pbUpdated.setVisibility(View.VISIBLE);
        tvTextError.setVisibility(View.INVISIBLE);
        bError.setVisibility(View.INVISIBLE);
    }

    private void show(){
        cvMainInformation.setVisibility(View.VISIBLE);
        cvAdditionalInformation.setVisibility(View.VISIBLE);
        cvDates.setVisibility(View.VISIBLE);
        tvName.setVisibility(View.VISIBLE);
        tvCompany.setVisibility(View.VISIBLE);
        tvLocation.setVisibility(View.VISIBLE);
        tvEmail.setVisibility(View.VISIBLE);
        tvPublicRepos.setVisibility(View.VISIBLE);
        tvDateCreated.setVisibility(View.VISIBLE);
        tvDateUpdated.setVisibility(View.VISIBLE);
        pbUpdated.setVisibility(View.INVISIBLE);
        tvTextError.setVisibility(View.INVISIBLE);
        bError.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError(){
        cvMainInformation.setVisibility(View.INVISIBLE);
        cvAdditionalInformation.setVisibility(View.INVISIBLE);
        cvDates.setVisibility(View.INVISIBLE);
        tvName.setVisibility(View.INVISIBLE);
        tvCompany.setVisibility(View.INVISIBLE);
        tvLocation.setVisibility(View.INVISIBLE);
        tvEmail.setVisibility(View.INVISIBLE);
        tvPublicRepos.setVisibility(View.INVISIBLE);
        tvDateCreated.setVisibility(View.INVISIBLE);
        tvDateUpdated.setVisibility(View.INVISIBLE);
        pbUpdated.setVisibility(View.INVISIBLE);
        tvTextError.setVisibility(View.VISIBLE);
        bError.setVisibility(View.VISIBLE);
    }
}
