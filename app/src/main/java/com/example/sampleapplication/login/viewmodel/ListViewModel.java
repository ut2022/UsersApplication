package com.example.sampleapplication.login.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sampleapplication.login.roomdatabase.UserDao;
import com.example.sampleapplication.login.roomdatabase.UserEntity;
import com.example.sampleapplication.login.roomdatabase.UserRepository;

import java.util.List;

public class ListViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private UserDao userDao;
    public MutableLiveData<UserEntity> dbLiveData= new MutableLiveData<>();

    public ListViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }
    public void deleteUser(UserEntity userEntity){
        userDao= new UserRepository(getApplication()).userDao;
        userDao.deleteUserPhno(userEntity.getUserphno());
    }
}
