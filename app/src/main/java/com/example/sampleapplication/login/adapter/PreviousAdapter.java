package com.example.sampleapplication.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplication.R;
import com.example.sampleapplication.login.model.PreviousResults;

import java.util.List;

public class PreviousAdapter extends RecyclerView.Adapter<PreviousAdapter.PreviousViewHolder> {
    List<PreviousResults> previousList;
    Context context;

    public PreviousAdapter(Context context, List<PreviousResults> previous){
        this.context =context;
        previousList=previous;
    }
    @NonNull
    @Override
    public PreviousViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.previous_details,parent,false);
        return new PreviousViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousViewHolder holder, int position) {
        PreviousResults previousResults=previousList.get(position);
        holder.name.setText("name : "+ previousResults.getName());
        holder.city.setText("city : "+ previousResults.getCity());
        holder.designation.setText("designation : "+ previousResults.getDesignation());
        holder.employee_id.setText("employee_id : "+ previousResults.getEmployee_id());
        holder.date_of_release.setText("date_of_release : "+ previousResults.getDate_of_release());
    }

    @Override
    public int getItemCount() {
        return previousList.size();
    }

    public class PreviousViewHolder extends RecyclerView.ViewHolder{
        TextView name,city,designation,employee_id,date_of_release;

        public PreviousViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.tv_previous_name);
            city=itemView.findViewById(R.id.tv_previous_city);
            designation=itemView.findViewById(R.id.tv_previous_designation);
            employee_id=itemView.findViewById(R.id.tv_previous_id);
            date_of_release=itemView.findViewById(R.id.tv_previous_date);
        }
    }
}





