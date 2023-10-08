package com.elijah.androidlearning.jetpack.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.elijah.androidlearning.R
import com.elijah.androidlearning.activitylifecycle.DialogActivity
import com.elijah.androidlearning.activitylifecycle.NormalActivity

class LifeCycleTestActivity : AppCompatActivity() {
    //private val lifeCycleObserver = LifeCycleObserver1()
    //private val lifeCycleObserver = LifeCycleObserver2()
    private val lifeCycleObserver = LifeCycleObserver3()

    private val listener = View.OnClickListener { v ->
        when (v.id) {
            R.id.addObserver -> lifecycle.addObserver(lifeCycleObserver)
            R.id.normalActivity -> startActivity(Intent(this, NormalActivity::class.java))
            R.id.dialogActivity -> startActivity(Intent(this, DialogActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_test)
        findViewById<View>(R.id.addObserver).setOnClickListener(listener)
        findViewById<View>(R.id.normalActivity).setOnClickListener(listener)
        findViewById<View>(R.id.dialogActivity).setOnClickListener(listener)
    }
}