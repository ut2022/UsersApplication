package com.example.sampleapplication.retrofit;

import com.example.sampleapplication.login.model.CurrentResults;
import com.example.sampleapplication.login.model.LoginResults;
import com.example.sampleapplication.login.model.PreviousResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL="https://mocki.io/v1/";
    @GET("a67abe3b-c4e5-4402-a0a2-fc04466252d8")
    Call<List<LoginResults>> getData();

    @GET("414a8d43-5f09-46b2-a0be-fc84178f3f1d")
    Call<List<CurrentResults>> getPost();

    @GET("0617823a-8f4b-4548-b43b-4cfd80dd3c55")
    Call<List<PreviousResults>> getPre();
}
