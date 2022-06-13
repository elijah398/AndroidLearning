package com.elijah.androidlearning.callback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elijah.androidlearning.R

class CallBackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_back)
        val student = Ricky()
        val teacher = Teacher(student)
        teacher.askStudent()
    }
}