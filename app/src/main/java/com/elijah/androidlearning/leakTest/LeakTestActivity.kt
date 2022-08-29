package com.elijah.androidlearning.leakTest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elijah.androidlearning.R


class LeakTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leak_test)
        val leakThread = LeakThread()
        leakThread.start()
    }

    companion object {
        class LeakThread : Thread() {
            override fun run() {
                super.run()
                try {
                    sleep(6 * 60 * 1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}