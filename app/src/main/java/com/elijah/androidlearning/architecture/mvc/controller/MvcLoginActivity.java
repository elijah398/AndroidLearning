package com.elijah.androidlearning.architecture.mvc.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elijah.androidlearning.R;
import com.elijah.androidlearning.architecture.mvc.model.User;

public class MvcLoginActivity extends AppCompatActivity {
    private EditText userNameEt;
    private EditText passwordEt;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_login);

        user = new User();
        userNameEt = findViewById(R.id.user_name_et);
        passwordEt = findViewById(R.id.password_et);
        Button loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(userNameEt.getText().toString(), passwordEt.getText().toString());
            }
        });
    }

    private void login(String userName, String password) {
        if (userName.equals("Yijun") && password.equals("12315")) {
            user.setUserName(userName);
            user.setPassword(password);
            Toast.makeText(MvcLoginActivity.this,
                            userName + " Login Successful",
                            Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(MvcLoginActivity.this,
                            "Login Failed",
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }
}