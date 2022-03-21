package com.example.sampleapplication.login.viewmodel;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.sampleapplication.login.model.LoginResults;
import com.example.sampleapplication.login.roomdatabase.UserEntity;
import com.example.sampleapplication.login.roomdatabase.UserRepository;
import com.example.sampleapplication.retrofit.RetrofitClient;
import com.example.sampleapplication.ui.login.LoginActivity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel implements Observable {
    private UserRepository userRepository;

    public MutableLiveData<Boolean> registerEvent= new MutableLiveData<>();
    public MutableLiveData<Boolean> isUserExist = new MutableLiveData<>();

    public MutableLiveData<UserEntity> dbLiveData= new MutableLiveData<>();

    private MutableLiveData<LoginActivity.Validationtype> validationLivedata = new MutableLiveData<>();

    private MutableLiveData<List<LoginResults>> loginResponsedata = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public MutableLiveData<List<LoginResults>> getLoginResponsedata() {
        return loginResponsedata;
    }

    public MutableLiveData<UserEntity> getDbResponsedata() {
        return dbLiveData;
    }

    @Bindable
    public String getUserphonenumber() {
        return userphonenumber;
    }

    public void setUserphonenumber(String userphonenumber) {
        this.userphonenumber = userphonenumber;
    }

    @Bindable
    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    private String userphonenumber = "";
    private String userpassword = "";

    public void validate(String phonenumber, String password) {
        if (!isValidPhoneNumber(phonenumber)) {
            validationLivedata.setValue(LoginActivity.Validationtype.PHONE);
            return;
        }
        if (!validatePassword(password)) {
            validationLivedata.setValue(LoginActivity.Validationtype.PASSWORD);
            return;
        }
        validationLivedata.setValue(LoginActivity.Validationtype.SUCCESS);
    }

    public void getData() {
        RetrofitClient.getRetrofitInstance().getMyApi().getData();
        Call<List<LoginResults>> call = RetrofitClient.getRetrofitInstance().getMyApi().getData();
        call.enqueue(new Callback<List<LoginResults>>() {
            @Override
            public void onResponse(@NonNull Call<List<LoginResults>> call, @NonNull Response<List<LoginResults>> response) {
                if(response.isSuccessful()) {
                    List<LoginResults> loginResults = response.body();
                    Log.e("login", "onResponse: " + response.code());
                    loginResponsedata.setValue(loginResults);
                }
            }
            @Override
            public void onFailure(Call<List<LoginResults>> call, Throwable t) {
                loginResponsedata.setValue(null);
            }
        });
    }

    public boolean validatePassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
    public static final boolean isValidPhoneNumber(CharSequence target) {
        if (target.length()!=10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }

    public MutableLiveData<LoginActivity.Validationtype> getValidationLivedata() {
        return validationLivedata;
    }

    public void onLoginClicked() {
        validate(userphonenumber, userpassword);
    }
    public void onRegisterClicked() {

        registerEvent.setValue(true);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
    public void getRowsFromDb(){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                List<UserEntity> users = userRepository.GetTask();
                if(users.isEmpty()){
                    dbLiveData.setValue(null);
                    return;
                }
                for (int i = 0; i < users.size(); i++) {
                    UserEntity user=users.get(i);
                    if(user.getUserphno().equals(getUserphonenumber())){
                        dbLiveData.setValue(user);
                        return;
                    }
                }
                dbLiveData.setValue(null);
            }
        });
    }



//    public List<UserEntity> checkuser(Application application) {
//
//    }

//    public LiveData<Integer> getCount() {
//        return userRepository.getCount();
//    }
}
