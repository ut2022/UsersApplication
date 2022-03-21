package com.example.sampleapplication.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sampleapplication.R;
import com.example.sampleapplication.login.adapter.CurrentAdapter;
import com.example.sampleapplication.login.model.CurrentResults;
import com.example.sampleapplication.login.roomdatabase.UserEntity;
import com.example.sampleapplication.login.viewmodel.CurrentViewModel;
import com.example.sampleapplication.login.viewmodel.RegisterViewModel;
import com.example.sampleapplication.ui.home.HomeActivity;

import java.util.List;

public class CurrentEmployeesFragment extends Fragment {
   CurrentViewModel currentViewModel;
    private RecyclerView recyclerView;
    private CurrentAdapter currentAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_current_employees, container, false);
        currentViewModel = new ViewModelProvider(this).get(CurrentViewModel.class);
        currentObservers();
        recyclerView = view.findViewById(R.id.rv_current);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        currentViewModel.getPost();


        return view;
    }
    private void currentObservers() {
        currentViewModel.getUserResponsedata().observe(getViewLifecycleOwner(), (Observer<List<CurrentResults>>) currentResults -> {
            if(currentResults == null){
                Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
            }else{
                CurrentAdapter currentAdapter=new CurrentAdapter(getContext(),currentResults);
                recyclerView.setAdapter(currentAdapter);
            }
        });

    }

}