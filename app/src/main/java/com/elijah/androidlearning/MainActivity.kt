package com.elijah.androidlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.elijah.androidlearning.activitylifecycle.LifeCycleActivity
import com.elijah.androidlearning.annotation.AnnotationActivity
import com.elijah.androidlearning.architecture.ArchitectureActivity
import com.elijah.androidlearning.broadcast.BroadcastActivity
import com.elijah.androidlearning.callback.CallBackActivity
//import com.elijah.androidlearning.contentprovider.ContentProviderActivity
import com.elijah.androidlearning.datapersist.DataPersistActivity
import com.elijah.androidlearning.encrypttool.EncryptActivity
import com.elijah.androidlearning.leakTest.LeakTestActivity
import com.elijah.androidlearning.lrucache.LruCacheActivity
import com.elijah.androidlearning.okhttp.OkhttpActivity
import com.elijah.androidlearning.requestpermission.RequestPermissionActivity
import com.elijah.androidlearning.retrofit.GetRequestActivity
import com.elijah.androidlearning.service.ServiceActivity
import com.elijah.androidlearning.thread.ThreadActivity
import com.elijah.androidlearning.uri.UriActivity
import com.elijah.androidlearning.xmlparser.XMLParserActivity

class MainActivity : AppCompatActivity() {

    private val listener =  View.OnClickListener { v ->
        when (v.id) {
            R.id.DataPersist -> startActivity(Intent(this@MainActivity, DataPersistActivity::class.java))
            R.id.RequestPermission -> startActivity(Intent(this@MainActivity, RequestPermissionActivity::class.java))
            //R.id.ContentProvider -> startActivity(Intent(this@MainActivity, ContentProviderActivity::class.java))
            R.id.ServiceTest -> startActivity(Intent(this@MainActivity, ServiceActivity::class.java))
            R.id.ThreadTest -> startActivity(Intent(this@MainActivity, ThreadActivity::class.java))
            R.id.ActivityLifecycleTest -> startActivity(Intent(this@MainActivity, LifeCycleActivity::class.java))
            R.id.LruCacheTest -> startActivity(Intent(this@MainActivity, LruCacheActivity::class.java))
            R.id.CallBackTest -> startActivity(Intent(this@MainActivity, CallBackActivity::class.java))
            R.id.BroadcastTest -> startActivity(Intent(this@MainActivity, BroadcastActivity::class.java))
            R.id.RetrofitTest -> startActivity(Intent(this@MainActivity, GetRequestActivity::class.java))
            R.id.LeakTest -> startActivity(Intent(this@MainActivity, LeakTestActivity::class.java))
            R.id.XmlParser -> startActivity(Intent(this@MainActivity, XMLParserActivity::class.java))
            R.id.encrypt -> startActivity(Intent(this@MainActivity, EncryptActivity::class.java))
            R.id.annotationTest -> startActivity(Intent(this@MainActivity, AnnotationActivity::class.java))
            R.id.okhttpTest -> startActivity(Intent(this@MainActivity, OkhttpActivity::class.java))
            R.id.uriTest -> startActivity(Intent(this@MainActivity, UriActivity::class.java))
            R.id.architectureTest -> startActivity(Intent(this@MainActivity, ArchitectureActivity::class.java))
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
        findViewById<View>(R.id.BroadcastTest).setOnClickListener(listener)
        findViewById<View>(R.id.RetrofitTest).setOnClickListener(listener)
        findViewById<View>(R.id.LeakTest).setOnClickListener(listener)
        findViewById<View>(R.id.XmlParser).setOnClickListener(listener)
        findViewById<View>(R.id.encrypt).setOnClickListener(listener)
        findViewById<View>(R.id.annotationTest).setOnClickListener(listener)
        findViewById<View>(R.id.okhttpTest).setOnClickListener(listener)
        findViewById<View>(R.id.uriTest).setOnClickListener(listener)
        findViewById<View>(R.id.architectureTest).setOnClickListener(listener)
    }
}