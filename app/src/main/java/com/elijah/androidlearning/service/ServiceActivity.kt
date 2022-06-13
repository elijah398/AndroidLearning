package com.elijah.androidlearning.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.elijah.androidlearning.R

class ServiceActivity : AppCompatActivity() {

    lateinit var downloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }
    }
    private val listener =  View.OnClickListener { v ->
        when (v.id) {
            R.id.StartService -> startService(Intent(this, MyService::class.java))
            R.id.StopService -> stopService(Intent(this, MyService::class.java))
            R.id.bindService -> bindService(Intent(this, MyService::class.java), connection, Context.BIND_AUTO_CREATE)
            R.id.unbindService -> unbindService(connection)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        findViewById<View>(R.id.StartService).setOnClickListener(listener)
        findViewById<View>(R.id.StopService).setOnClickListener(listener)
        findViewById<View>(R.id.bindService).setOnClickListener(listener)
        findViewById<View>(R.id.unbindService).setOnClickListener(listener)
    }
}