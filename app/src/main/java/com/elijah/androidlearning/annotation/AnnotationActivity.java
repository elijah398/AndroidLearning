package com.elijah.androidlearning.annotation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elijah.androidlearning.R;

@InjectLayout(R.layout.activity_annotation)
public class AnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindProcessor.bind(this);
        mButton.setText("通过注解设置的text");
    }

    @BindView(R.id.annotation)
    Button mButton;

    @OnClick(R.id.annotation)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.annotation:
                Toast.makeText(this, "通过注解点击了Button", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}