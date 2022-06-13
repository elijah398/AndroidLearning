package com.elijah.androidlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.elijah.androidlearning.activitylifecycle.LifeCycleActivity
import com.elijah.androidlearning.callback.CallBackActivity
import com.elijah.androidlearning.contentprovider.ContentProviderActivity
import com.elijah.androidlearning.datapersist.DataPersistActivity
import com.elijah.androidlearning.lrucache.LruCacheActivity
import com.elijah.androidlearning.requestpermission.RequestPermissionActivity
import com.elijah.androidlearning.service.ServiceActivity
import com.elijah.androidlearning.thread.ThreadActivity

class MainActivity : AppCompatActivity() {

    private val listener =  View.OnClickListener { v ->
        when (v.id) {
            R.id.DataPersist -> startActivity(Intent(this@MainActivity, DataPersistActivity::class.java))
            R.id.RequestPermission -> startActivity(Intent(this@MainActivity, RequestPermissionActivity::class.java))
            R.id.ContentProvider -> startActivity(Intent(this@MainActivity, ContentProviderActivity::class.java))
            R.id.ServiceTest -> startActivity(Intent(this@MainActivity, ServiceActivity::class.java))
            R.id.ThreadTest -> startActivity(Intent(this@MainActivity, ThreadActivity::class.java))
            R.id.ActivityLifecycleTest -> startActivity(Intent(this@MainActivity, LifeCycleActivity::class.java))
            R.id.LruCacheTest -> startActivity(Intent(this@MainActivity, LruCacheActivity::class.java))
            R.id.CallBackTest -> startActivity(Intent(this@MainActivity, CallBackActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.DataPersist).setOnClickListener(listener)
        findViewById<View>(R.id.RequestPermission).setOnClickListener(listener)
        findViewById<View>(R.id.ContentProvider).setOnClickListener(listener)
        findViewById<View>(R.id.ServiceTest).setOnClickListener(listener)
        findViewById<View>(R.id.ThreadTest).setOnClickListener(listener)
        findViewById<View>(R.id.ActivityLifecycleTest).setOnClickListener(listener)
        findViewById<View>(R.id.LruCacheTest).setOnClickListener(listener)
        findViewById<View>(R.id.CallBackTest).setOnClickListener(listener)
    }
}