package com.elijah.androidlearning.thread

import android.util.Log

class MyRunnable: Runnable  {
    private var ticket = 100

    override fun run() {
        while (ticket > 0) {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            ticket--
            Log.d("DYJDebug", "${Thread.currentThread().name}, 卖掉了1张票，剩余票数为：$ticket")
        }
    }
}