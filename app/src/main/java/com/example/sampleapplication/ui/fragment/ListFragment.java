package com.example.sampleapplication.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sampleapplication.R;
import com.example.sampleapplication.login.adapter.ListAdapter;
import com.example.sampleapplication.login.roomdatabase.UserDao;
import com.example.sampleapplication.login.roomdatabase.UserEntity;
import com.example.sampleapplication.login.roomdatabase.UserRepository;
import com.example.sampleapplication.login.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment implements ItemClickListener{
ListViewModel listViewModel;
TextView listName;
TextView listPhno;
TextView listEmail;
TextView listPassword;
Button deleteItem;
List<UserEntity> list = new ArrayList<>();
private UserDao userDao;
private UserRepository userRepository;
ListAdapter adapter;
RecyclerView recyclerView;
ItemClickListener itemClickListener;
UserEntity userEntity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_list, container, false);
        userRepository = new UserRepository(getActivity().getApplication());
        initRecyclerView(view);
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);

        deleteItem=view.findViewById(R.id.bt_delete);
        listName=view.findViewById(R.id.current_name);
        listPhno=view.findViewById(R.id.current_phonenumber);
        listEmail=view.findViewById(R.id.current_email);
        listPassword=view.findViewById(R.id.current_password);

        return view;
    }
    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.rv_list);
        adapter = new ListAdapter(requireActivity(),list,ListFragment.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       userDao= userRepository.userDao;
       for(UserEntity userEntity:userDao.getValues()){
           list.add(userEntity);
       }
       adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(UserEntity userEntity, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage("Do you want to delete ?");
        builder.setTitle("Alert !");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            listViewModel.deleteUser(userEntity);
            list.remove(position);
            adapter.notifyItemRemoved(position);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
