package com.example.sampleapplication.login.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sampleapplication.login.model.CurrentResults;
import com.example.sampleapplication.login.roomdatabase.UserDao;
import com.example.sampleapplication.login.roomdatabase.UserEntity;
import com.example.sampleapplication.login.roomdatabase.UserRepository;
import com.example.sampleapplication.ui.login.LoginActivity;
import com.example.sampleapplication.ui.register.RegisterActivity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterViewModel extends AndroidViewModel implements Observable {
    public UserRepository userRepository;
    public LiveData<List<UserEntity>> getAllPosts;

    private UserDao userDao;

    private MutableLiveData<RegisterActivity.Validationtype> validationLivedata = new MutableLiveData<>();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }
    private String registername = "";
    private String registerpassword = "";

    @Bindable
    public String getRegistername() {
        return registername;
    }

    public void setRegistername(String registername) {
        this.registername = registername;
    }

    @Bindable
    public String getRegisterpassword() {
        return registerpassword;
    }

    public void setRegisterpassword(String registerpassword) {
        this.registerpassword = registerpassword;
    }

    @Bindable
    public String getRegisteremail() {
        return registeremail;
    }

    public void setRegisteremail(String registeremail) {
        this.registeremail = registeremail;
    }

    @Bindable
    public String getRegisterphno() {
        return registerphno;
    }

    public void setRegisterphno(String registerphno) {
        this.registerphno = registerphno;
    }

    private String registeremail="";
    private String registerphno="";

    public MutableLiveData<RegisterActivity.Validationtype> getValidationLivedata() {
        return validationLivedata;
    }
    public boolean validate(String phonenumber, String password, String email, String name) {
        if (!isValidPhoneNumber(phonenumber)) {
            validationLivedata.setValue(RegisterActivity.Validationtype.PHONENO);
            return false;
        }
        if (!validatePassword(password)) {
            validationLivedata.setValue(RegisterActivity.Validationtype.REGPASSWORD);
            return false;
        }
        if (!isValidMail(email)) {
            validationLivedata.setValue(RegisterActivity.Validationtype.REGEMAIL);
            return false;
        }
        if (name.isEmpty()) {
            validationLivedata.setValue(RegisterActivity.Validationtype.REGNAME);
            return false;
        }
        return true;
    }
    public static final boolean isValidPhoneNumber(CharSequence target) {
        if (target.length()!=10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }
    public boolean validatePassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
    private boolean isValidMail(String email) {

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(EMAIL_STRING).matcher(email).matches();

    }
    public void onProceedClicked() {

        if(validate(registerphno,registerpassword,registeremail,registername)){
            userRepository.InsertTask(new UserEntity(registername,registeremail,registerphno,registerpassword));
            validationLivedata.setValue(RegisterActivity.Validationtype.SUCCESS);
        }
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
