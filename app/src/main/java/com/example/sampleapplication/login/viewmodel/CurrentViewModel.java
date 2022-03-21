package com.example.sampleapplication.login.viewmodel;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sampleapplication.login.model.CurrentResults;
import com.example.sampleapplication.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentViewModel extends ViewModel {
    private MutableLiveData<List<CurrentResults>> currentResponsedata = new MutableLiveData<>();

    public MutableLiveData<List<CurrentResults>> getUserResponsedata() {
        return currentResponsedata;

    }

    public void getPost() {
        RetrofitClient.getRetrofitInstance().getMyApi().getPost();
        Call<List<CurrentResults>> call = RetrofitClient.getRetrofitInstance().getMyApi().getPost();
        call.enqueue(new Callback<List<CurrentResults>>() {
            @Override
            public void onResponse(Call<List<CurrentResults>> call, Response<List<CurrentResults>> response) {
                List<CurrentResults> currentResults = response.body();
                Log.e(TAG, "onResponse: " + response.code());

                currentResponsedata.setValue(currentResults);
            }

            @Override
            public void onFailure(Call<List<CurrentResults>> call, Throwable t) {

            }
        });
    }
}