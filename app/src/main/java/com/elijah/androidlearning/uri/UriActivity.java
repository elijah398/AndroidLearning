package com.elijah.androidlearning.uri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.elijah.androidlearning.R;

import java.util.List;

public class UriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uri);
    }

    public void btnQIA(View view) {
        Log.d("DYJ", "click btnQIA");

        Uri uri = Uri.parse("tel:10086");
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        List<ResolveInfo> resolveInfoList = this.getPackageManager().queryIntentActivities(it, PackageManager.MATCH_ALL);
        if (resolveInfoList == null || resolveInfoList.isEmpty()) {
            Log.d("DYJ", "resolveInfoList size is " + resolveInfoList.size());
        } else {
            for (ResolveInfo resolveInfo : resolveInfoList) {
                Log.d("DYJ", "resolveInfo is：" + resolveInfo.toString());
                startActivity(it);
            }
        }
    }
}