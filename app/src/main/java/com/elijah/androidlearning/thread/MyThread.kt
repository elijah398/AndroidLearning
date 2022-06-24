package com.elijah.androidlearning.thread

import android.util.Log

class MyThread(name: String) : Thread(name) {
    private var ticket = 100

    override fun run() {
        super.run()
        while (ticket > 0) {
            ticket--
            Log.d("DYJDebug", "$name, 卖掉了1张票，剩余票数为：$ticket")
            try {
                sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}