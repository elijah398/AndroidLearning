package com.elijah.androidlearning.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val mBinder = DownloadBinder()

    override fun onBind(intent: Intent): IBinder {
        Log.d("DYJDebug", "MyService onBind")
        return mBinder
    }

    class DownloadBinder: Binder() {
        fun startDownload() {
            Log.d("DYJDebug", "startDownload")
        }

        fun getProgress(): Int {
            Log.d("DYJDebug", "getProgress")
            return 0
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("DYJDebug", "MyService onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("DYJDebug", "MyService onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("DYJDebug", "MyService onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DYJDebug", "MyService onDestroy")
    }
}