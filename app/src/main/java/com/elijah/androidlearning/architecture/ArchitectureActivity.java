package com.elijah.androidlearning.architecture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elijah.androidlearning.R;
import com.elijah.androidlearning.architecture.mvc.controller.MvcLoginActivity;
import com.elijah.androidlearning.architecture.mvp.view.MvpLoginActivity;
import com.elijah.androidlearning.architecture.mvvm.view.MvvmLoginActivity;

public class ArchitectureActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architecture);

        Button mvcBtn = findViewById(R.id.mvc_btn);
        Button mvpBtn = findViewById(R.id.mvp_btn);
        Button mvvmBtn = findViewById(R.id.mvvm_btn);
        mvcBtn.setOnClickListener(this);
        mvpBtn.setOnClickListener(this);
        mvvmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.mvc_btn:
                Intent mvcIntent = new Intent(this, MvcLoginActivity.class);
                startActivity(mvcIntent);
                break;
            case R.id.mvp_btn:
                Intent mvpIntent = new Intent(this, MvpLoginActivity.class);
                startActivity(mvpIntent);
                break;
            case R.id.mvvm_btn:
                Intent mvvmIntent = new Intent(this, MvvmLoginActivity.class);
                startActivity(mvvmIntent);
                break;
            default:
                break;
        }
    }
}