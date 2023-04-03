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

    private lateinit var airPlaneChangeReceiver: AirPlaneChangeReceiver

    private val listener =  View.OnClickListener { v ->
        when (v.id) {
            R.id.registerAirPlaneChangeListener -> registerAirPlaneChangeListener()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        findViewById<View>(R.id.registerAirPlaneChangeListener).setOnClickListener(listener)
    }

    private fun registerAirPlaneChangeListener() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        airPlaneChangeReceiver = AirPlaneChangeReceiver()
        Log.d("DYJDebug", "AirPlane Mode listener has registered")
        registerReceiver(airPlaneChangeReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneChangeReceiver)
    }

    inner class AirPlaneChangeReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Toast.makeText(p0, "AirPlane Mode has changed", Toast.LENGTH_SHORT).show()
        }
    }
}