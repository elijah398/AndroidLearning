package com.elijah.androidlearning.architecture.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elijah.androidlearning.architecture.mvvm.model.User;

/**
 * @FileName null.java
 * @Description TODO
 * @Author 80254912
 * @Date 2023/4/17
 */
public class LoginViewModel extends ViewModel {
    private User user;
    private MutableLiveData<Boolean> isLoginSuccessfulLD;

    public LoginViewModel() {
        this.isLoginSuccessfulLD = new MutableLiveData<>();
        user = new User();
    }

    public MutableLiveData<Boolean> getIsLoginSuccessfulLD() {
        return isLoginSuccessfulLD;
    }

    public void setIsLoginSuccessfulLD(boolean isLoginSuccessful) {
        isLoginSuccessfulLD.postValue(isLoginSuccessful);
    }

    public void login(String userName, String password) {
        if (userName.equals("Yijun") && password.equals("12315")) {
            user.setUserName(userName);
            user.setPassword(password);
            setIsLoginSuccessfulLD(true);
        } else {
            setIsLoginSuccessfulLD(false);
        }
    }

    public String getUserName() {
        return user.getUserName();
    }
}
