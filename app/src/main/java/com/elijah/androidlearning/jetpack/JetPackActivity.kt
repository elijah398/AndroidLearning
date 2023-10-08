package com.elijah.androidlearning.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.elijah.androidlearning.R
import com.elijah.androidlearning.datapersist.DataPersistActivity
import com.elijah.androidlearning.jetpack.lifecycle.LifeCycleTestActivity
import com.elijah.androidlearning.jetpack.livedata.LiveDataTestActivity

class JetPackActivity : AppCompatActivity() {

    private val listener = View.OnClickListener { v ->
        when (v.id) {
            R.id.lifecycle -> startActivity(Intent(this@JetPackActivity, LifeCycleTestActivity::class.java))
            R.id.livedata -> startActivity(Intent(this@JetPackActivity, LiveDataTestActivity::class.java))
            R.id.viewmodel -> startActivity(Intent(this@JetPackActivity, DataPersistActivity::class.java))
            R.id.databinding -> startActivity(Intent(this@JetPackActivity, DataPersistActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jet_pack)
        findViewById<View>(R.id.lifecycle).setOnClickListener(listener)
        findViewById<View>(R.id.livedata).setOnClickListener(listener)
        findViewById<View>(R.id.viewmodel).setOnClickListener(listener)
        findViewById<View>(R.id.databinding).setOnClickListener(listener)
    }
}