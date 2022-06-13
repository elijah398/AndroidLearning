package com.elijah.androidlearning.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elijah.androidlearning.R

class NormalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal)
    }
}