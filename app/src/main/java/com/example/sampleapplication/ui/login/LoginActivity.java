package com.example.sampleapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sampleapplication.R;
import com.example.sampleapplication.databinding.ActivityLoginBinding;
import com.example.sampleapplication.login.model.LoginResults;
import com.example.sampleapplication.login.roomdatabase.UserDao;
import com.example.sampleapplication.login.roomdatabase.UserDatabase;
import com.example.sampleapplication.login.roomdatabase.UserEntity;
import com.example.sampleapplication.login.viewmodel.LoginViewModel;
import com.example.sampleapplication.ui.home.HomeActivity;
import com.example.sampleapplication.ui.register.RegisterActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static String PREFS_NAME = "MyPrefsFile";
    LoginViewModel loginViewModel;
    Boolean islogin = false;
    private LiveData<Integer> user_count;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class); //initialize viewmodel

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel(loginViewModel);

        dbObserver();
        registerButtonObserver();
        registerObservers();

        loginViewModel.getValidationLivedata().observe(this, s -> {
            switch (s) {
                case PHONE:
                    Toast.makeText(LoginActivity.this, "Please Fill PhoneNumber", Toast.LENGTH_SHORT).show();
                    break;

                case SUCCESS:
                    loginViewModel.getData();
                    break;

                case PASSWORD:
                    Toast.makeText(LoginActivity.this, "Please Fill Password", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }


    public enum Validationtype {
        PHONE,SUCCESS, PASSWORD
    }

    private void registerObservers() {
        loginViewModel.getLoginResponsedata().observe(this, loginResults -> {
            if (loginResults == null) {
                Toast.makeText(LoginActivity.this, "Error has occured", Toast.LENGTH_SHORT).show();
            } else {
                loginViewModel.getRowsFromDb();
            }
        });
    }
    private void dbObserver(){
        loginViewModel.getDbResponsedata().observe(this, new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if (userEntity == null) {
                    Toast.makeText(LoginActivity.this, loginViewModel.getUserphonenumber() +" :Mobile Number not exist in db", Toast.LENGTH_SHORT).show();
                } else{
                    SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.putString("currentPhone", userEntity.getUserphno());
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    private void registerButtonObserver(){
        loginViewModel.registerEvent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
