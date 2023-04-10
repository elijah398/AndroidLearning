package com.elijah.androidlearning.okhttp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.elijah.androidlearning.R;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {

    private OkHttpClient client;
    public static final String GET_URL = "http://publicobject.com/helloworld.txt";
    private Button btnGet;
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        assignViews();
        initOkHttp();
    }

    private void assignViews() {
        btnGet = (Button) findViewById(R.id.btn_get);
        tvShow = (TextView) findViewById(R.id.tv_show);
    }

    private void initOkHttp() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public void btnGet(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                Log.d("DYJ", "click btn_get");
                Request request = new Request.Builder()
                    .get()
                    .url(GET_URL)
                    .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("DYJ", "call failed, exception: " + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseString = response.body().string();
                        Log.d("DYJ", "onResponse: " + responseString);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvShow.setText(responseString);
                            }
                        });
                    }
                });
                break;
            default:
                break;
        }
    }
}