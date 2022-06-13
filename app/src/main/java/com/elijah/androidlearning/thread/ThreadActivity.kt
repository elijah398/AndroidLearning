package com.elijah.androidlearning.thread

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import com.elijah.androidlearning.R
import kotlinx.android.synthetic.main.activity_thread.*
import java.lang.ref.WeakReference

class ThreadActivity : AppCompatActivity() {

    private val mTask = MyTask(this)

    private val listener =  View.OnClickListener { v ->
        when (v.id) {
            R.id.Thread_sole_tickets -> threadSellTickets()
            R.id.Runnable_sole_tickets -> runnableSellTickets()
            R.id.Runnable_both_sole_tickets -> runnableBothSellTickets()
            R.id.AsyncTaskTest -> asyncTaskTest()
            R.id.AsyncTaskCancel -> asyncTaskCancel()
            R.id.HandlerTest -> handlerTest()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        findViewById<View>(R.id.Thread_sole_tickets).setOnClickListener(listener)
        findViewById<View>(R.id.Runnable_sole_tickets).setOnClickListener(listener)
        findViewById<View>(R.id.Runnable_both_sole_tickets).setOnClickListener(listener)
        findViewById<View>(R.id.AsyncTaskTest).setOnClickListener(listener)
        findViewById<View>(R.id.AsyncTaskCancel).setOnClickListener(listener)
        findViewById<View>(R.id.HandlerTest).setOnClickListener(listener)
    }

    private fun threadSellTickets() {
        val mt1 = MyThread("Thread窗口1")
        val mt2 = MyThread("Thread窗口2")

        mt1.start()
        mt2.start()
    }

    private fun runnableSellTickets() {
        val mt1 = MyRunnable()
        val mt2 = MyRunnable()

        val mt11 = Thread(mt1, "Runnable窗口1")
        val mt22 = Thread(mt2, "Runnable窗口2")

        mt11.start()
        mt22.start()
    }

    private fun runnableBothSellTickets() {
        val mt = MyRunnable()

        val mt11 = Thread(mt, "Runnable窗口1")
        val mt22 = Thread(mt, "Runnable窗口2")

        mt11.start()
        mt22.start()
    }

    private fun asyncTaskTest() {
        mTask.execute()
    }

    private fun asyncTaskCancel() {
        mTask.cancel(true)
    }

    private fun handlerTest() {
        val mHandler = MyHandler(this)

        Thread {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val msg = Message.obtain().apply {
                what = 1
                obj = "A"
            }

            mHandler.sendMessage(msg)
        }.start()

        Thread {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val msg = Message.obtain().apply {
                what = 2
                obj = "B"
            }

            mHandler.sendMessage(msg)
        }.start()
    }

    private class MyThread(name: String) : Thread(name) {
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

    private class MyRunnable: Runnable {
        private var ticket = 100

        override fun run() {
            while (ticket > 0) {
                ticket--
                Log.d("DYJDebug", "${Thread.currentThread().name}, 卖掉了1张票，剩余票数为：$ticket")
            }

            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    /*
    防止内存泄漏，设置为静态内部类
     */
    companion object {
        class MyTask internal constructor(context: ThreadActivity): AsyncTask<Int, Int, String?>() {

            private val activityReference: WeakReference<ThreadActivity> = WeakReference(context)

            override fun onPreExecute() {
                super.onPreExecute()
                activityReference.get()?.AsyncTaskLoadingState?.text = "加载中"
            }

            override fun doInBackground(vararg p0: Int?): String? {
                try {
                    var count = 0
                    var length = 1
                    while (count < 99) {
                        count += length
                        publishProgress(count)
                        Thread.sleep(50)
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                return null
            }

            override fun onProgressUpdate(vararg values: Int?) {
                super.onProgressUpdate(*values)
                values[0]?.let { activityReference.get()?.AsyncTaskLoadingProgress?.setProgress(it) }
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                activityReference.get()?.AsyncTaskLoadingState?.text = "加载完毕"
            }

            override fun onCancelled() {
                super.onCancelled()
                activityReference.get()?.AsyncTaskLoadingState?.text = "已取消"
                activityReference.get()?.AsyncTaskLoadingProgress?.setProgress(0)
            }
        }
    }

    class MyHandler internal constructor(context: ThreadActivity): Handler() {

        private val activityReference: WeakReference<ThreadActivity> = WeakReference(context)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what) {
                1 -> activityReference.get()?.HandlerTestState?.text = "执行了线程1的UI操作"
                2 -> activityReference.get()?.HandlerTestState?.text = "执行了线程2的UI操作"
            }
        }
    }
}