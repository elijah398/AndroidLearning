package com.elijah.androidlearning.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PersonService extends Service {
    public PersonService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IPersonImpl();
    }
}