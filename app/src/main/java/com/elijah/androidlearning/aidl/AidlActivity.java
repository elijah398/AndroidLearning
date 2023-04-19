package com.elijah.androidlearning.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.elijah.androidlearning.IPersonAidlInterface;
import com.elijah.androidlearning.R;

// Client
public class AidlActivity extends AppCompatActivity {
    private static final String TAG = "AidlActivity";
    private IPersonAidlInterface iPersonAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
    }

    public void bindServiceClick(View view) {
        Log.i(TAG,"绑定服务...");
        Intent intent = new Intent(this, PersonService.class);
        // 绑定服务时自动创建服务
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    public void unbindServiceClick(View view) {
        Log.i(TAG,"解绑服务...");
        unbindService(conn);
    }

    public void callRemoteClick(View view) {
        Log.i(TAG,"远程调用具体服务...");
        try {
            iPersonAidlInterface.setName("Tom");
            iPersonAidlInterface.setAge(10);
            String info = iPersonAidlInterface.getInfo();
            Log.i(TAG,"这是远程调用的服务信息："+info);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 根据实际情况返回 IBinder 的本地对象或其代理对象
            iPersonAidlInterface = IPersonAidlInterface.Stub.asInterface(service);
            Log.i(TAG,"具体的业务对象："+iPersonAidlInterface);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // Service 意外中断时调用
        }
    };
}
