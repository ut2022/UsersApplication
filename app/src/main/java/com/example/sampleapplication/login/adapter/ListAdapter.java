package com.example.sampleapplication.login.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplication.R;
import com.example.sampleapplication.login.model.PreviousResults;
import com.example.sampleapplication.login.roomdatabase.UserEntity;
import com.example.sampleapplication.ui.fragment.ItemClickListener;
import com.example.sampleapplication.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    List<UserEntity> list;
    Context context;
    ItemClickListener itemClickListener;

    public ListAdapter(Context context, List<UserEntity> listitem,ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
        this.context =context;
        list=listitem;
    }

    @NonNull
    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new ListAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListViewHolder holder, int position) {
        UserEntity userEntity=list.get(position);
        holder.name.setText("name : "+ userEntity.getUsername());
        holder.phonenumber.setText("phonenumber : "+ userEntity.getUserphno());
        holder.email.setText("email: "+ userEntity.getUserEmail());
        holder.password.setText("password: "+ userEntity.getUserpassword());

        SharedPreferences sharedPreferences = context.getSharedPreferences(LoginActivity.PREFS_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString("currentPhone","").equals(userEntity.getUserphno())){
            holder.button.setVisibility(View.GONE);
        }else{
            holder.button.setVisibility(View.VISIBLE);
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == -1){
                    return;
                }
                itemClickListener.onClick(userEntity,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView name,phonenumber,email,password,button;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.current_name);
            phonenumber=itemView.findViewById(R.id.current_phonenumber);
            email=itemView.findViewById(R.id.current_email);
            password=itemView.findViewById(R.id.current_password);
            button=itemView.findViewById(R.id.bt_delete);
        }
    }
}
