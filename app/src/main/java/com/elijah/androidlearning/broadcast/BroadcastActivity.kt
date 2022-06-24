package com.elijah.androidlearning.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.elijah.androidlearning.R
import com.elijah.androidlearning.activitylifecycle.LifeCycleActivity
import com.elijah.androidlearning.callback.CallBackActivity
import com.elijah.androidlearning.datapersist.DataPersistActivity
import com.elijah.androidlearning.lrucache.LruCacheActivity
import com.elijah.androidlearning.requestpermission.RequestPermissionActivity
import com.elijah.androidlearning.service.ServiceActivity
import com.elijah.androidlearning.thread.ThreadActivity

class BroadcastActivity : AppCompatActivity() {

    private lateinit var timeChangeReceiver: TimeChangeReceiver

    private val listener =  View.OnClickListener { v ->
        when (v.id) {
            R.id.registerTimeChangeListener -> registerTimeChangeListener()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        findViewById<View>(R.id.registerTimeChangeListener).setOnClickListener(listener)
    }

    private fun registerTimeChangeListener() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        Log.d("DYJDebug", "timeChangeReceiver has registered")
        registerReceiver(timeChangeReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Toast.makeText(p0, "Time has Changed", Toast.LENGTH_SHORT).show()
        }
    }
}