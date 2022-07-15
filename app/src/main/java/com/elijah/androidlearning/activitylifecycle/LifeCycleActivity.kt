package com.elijah.androidlearning.activitylifecycle

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.elijah.androidlearning.R

class LifeCycleActivity : AppCompatActivity() {
    private val listener =  View.OnClickListener { v ->
        when (v.id) {
            R.id.StartNormalActivity -> startActivity(Intent(this, NormalActivity::class.java))
            R.id.StartDialogActivity -> startActivity(Intent(this, DialogActivity::class.java))
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("DYJDebug", "onConfigurationChanged, Voiding Activity Restart")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DYJDebug", "onCreate")
        setContentView(R.layout.activity_life_cycle)
        findViewById<View>(R.id.StartNormalActivity).setOnClickListener(listener)
        findViewById<View>(R.id.StartDialogActivity).setOnClickListener(listener)
    }


    override fun onStart() {
        super.onStart()
        Log.d("DYJDebug", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("DYJDebug", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("DYJDebug", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("DYJDebug", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DYJDebug", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("DYJDebug", "onRestart")
    }

}