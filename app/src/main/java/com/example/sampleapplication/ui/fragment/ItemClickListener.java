package com.example.sampleapplication.ui.fragment;

import com.example.sampleapplication.login.roomdatabase.UserEntity;

public interface ItemClickListener {

    void onClick(UserEntity userEntity, int position);
}
