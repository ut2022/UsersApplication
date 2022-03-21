package com.example.sampleapplication.ui.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleapplication.R;
import com.example.sampleapplication.databinding.ActivityLoginBinding;
import com.example.sampleapplication.databinding.ActivityRegisterBinding;
import com.example.sampleapplication.login.roomdatabase.UserEntity;
import com.example.sampleapplication.login.roomdatabase.UserRepository;
import com.example.sampleapplication.login.viewmodel.LoginViewModel;
import com.example.sampleapplication.login.viewmodel.RegisterViewModel;
import com.example.sampleapplication.ui.login.LoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText regName;
    EditText regEmail;
    EditText regPassword;
    EditText regPhno;
    private UserRepository userRepository;
    RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize viewmodel
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setRegViewModel(registerViewModel);

        registerViewModel.getValidationLivedata().observe(this, s -> {
            switch (s) {
                case PHONENO:
                    Toast.makeText(RegisterActivity.this, "Please Fill PhoneNumber", Toast.LENGTH_SHORT).show();
                    break;

                case SUCCESS:
                    Toast.makeText(getApplication(), "Registered Successfully!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplication(), LoginActivity.class);
                   startActivity(intent);
                    break;

                case REGPASSWORD:
                    Toast.makeText(RegisterActivity.this, "Please Fill Password", Toast.LENGTH_SHORT).show();
                    break;
                case REGEMAIL:
                    Toast.makeText(RegisterActivity.this, "Please Fill Email", Toast.LENGTH_SHORT).show();
                    break;
                case REGNAME:
                    Toast.makeText(RegisterActivity.this, "Please Fill Name", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
    private Boolean validate() {
        if (!isValidEmail(regEmail.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Please Check mailid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (regPhno.getText().toString().isEmpty() || regPhno.getText().length() < 10) {
            Toast.makeText(RegisterActivity.this, "Please Fill Phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (regName.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please Fill Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!validatePassword(regPassword.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Please check password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean validatePassword(final String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

//                Toast.makeText(this, "Back button pressed!", Toast.LENGTH_SHORT).show();
//                this.onBackPressed();
                finish();  //destroy method called
                return true;
        }
        return super.onOptionsItemSelected(item);  //immediate base class function call
    }

    public enum Validationtype {
        PHONENO,SUCCESS, REGPASSWORD,REGEMAIL,REGNAME
    }
}
