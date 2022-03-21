package com.example.sampleapplication.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplication.R;
import com.example.sampleapplication.login.model.CurrentResults;
import com.example.sampleapplication.login.roomdatabase.UserEntity;
import com.example.sampleapplication.ui.home.HomeActivity;

import java.util.List;

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.CurrentViewHolder> {
    List<CurrentResults> currentlist;
    Context context;

    public CurrentAdapter(Context context,List<CurrentResults> current){
        this.context =context;
        currentlist=current;
    }
    @NonNull
    @Override
    public CurrentAdapter.CurrentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_details,parent,false);
       CurrentViewHolder currentViewHolder=new CurrentViewHolder(view);
       return currentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentAdapter.CurrentViewHolder holder, int position) {
        CurrentResults currentResults=currentlist.get(position);
        holder.name.setText("name : "+ currentResults.getName());
        holder.city.setText("city : "+ currentResults.getCity());
        holder.designation.setText("designation : "+ currentResults.getDesignation());
        holder.employee_id.setText("employee_id : "+ currentResults.getEmployee_id());
    }

    @Override
    public int getItemCount() {
            return currentlist.size();
    }

    public class CurrentViewHolder extends RecyclerView.ViewHolder {
        private TextView name, city,designation,employee_id;

        public CurrentViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_current_name);
            city= itemView.findViewById(R.id.tv_current_phonenumber);
            designation= itemView.findViewById(R.id.tv_current_email);
            employee_id= itemView.findViewById(R.id.tv_current_password);
        }

    }

}





