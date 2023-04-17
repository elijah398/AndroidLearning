package com.elijah.androidlearning.architecture.mvvm.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elijah.androidlearning.R;
import com.elijah.androidlearning.architecture.mvvm.viewmodel.LoginViewModel;

public class MvvmLoginActivity extends AppCompatActivity {
    private LoginViewModel loginVM;
    private EditText userNameEt;
    private EditText passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm_login);

        userNameEt = findViewById(R.id.user_name_et);
        passwordEt = findViewById(R.id.password_et);
        Button loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginVM.login(userNameEt.getText().toString(), passwordEt.getText().toString());
            }
        });

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        loginVM = viewModelProvider.get(LoginViewModel.class);
        loginVM.getIsLoginSuccessfulLD().observe(this, loginObserver);
    }

    private Observer<Boolean> loginObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean isLoginSuccessFul) {
            if (isLoginSuccessFul) {
                Toast.makeText(MvvmLoginActivity.this,
                                loginVM.getUserName() + " Login Successful",
                                Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(MvvmLoginActivity.this,
                                "Login Failed",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };
}