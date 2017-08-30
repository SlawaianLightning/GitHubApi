package com.example.work.githubapi.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.work.githubapi.R;
import com.example.work.githubapi.data.entities.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersAdapter extends RecyclerView.Adapter{
    private List<Item> users = new ArrayList<>();
    private LayoutInflater inflater;
    private Context mContext;

    public UsersAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.user, parent, false);
        return new UsersViewHolder(v);
    }

    public void updateUsers(List<Item> list){
        users.clear();
        users.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UsersViewHolder usersViewHolder=(UsersViewHolder) holder;
        usersViewHolder.tvLogin.setText(users.get(position).getLogin());
        Picasso.with( usersViewHolder.ivAvatar.getContext()).
                load(users.get(position).getAvatarUrl()).
                placeholder(R.drawable.default_picture).
                resize(100, 100).
                into(usersViewHolder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UsersViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ivAvatar) ImageView ivAvatar;
        @BindView(R.id.tvLogin) TextView tvLogin;

        UsersViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}