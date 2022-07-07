package com.elijah.androidlearning.thread

import android.os.*
import androidx.appcompat.app.AppCompatActivity
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
            R.id.UIHandlerTest -> uIHandlerTest()
            R.id.WorkHandlerTest -> workThreadHandlerTest()
            R.id.ThreadPoolTest -> threadPoolTest()
            R.id.CoroutineTest -> coroutineTest()
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
        findViewById<View>(R.id.UIHandlerTest).setOnClickListener(listener)
        findViewById<View>(R.id.WorkHandlerTest).setOnClickListener(listener)
        findViewById<View>(R.id.ThreadPoolTest).setOnClickListener(listener)
        findViewById<View>(R.id.CoroutineTest).setOnClickListener(listener)
    }

    private fun coroutineTest() {
        val ct = CoroutineTest(this)
        ct.showUserInfoByLaunch()
        //ct.showUserInfoByAsync()
    }

    private fun threadPoolTest() {
        val task = MyRunnable()
        ThreadPool.execute(task)
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

    private fun uIHandlerTest() {
        val myLooper = Looper.myLooper()
        val mUIHandler = MyUIHandler(this, Looper.getMainLooper())

        Log.d("DYJDebug", "CurrentThread is ${Thread.currentThread()}, Mylooper is $myLooper")

        Thread {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val msg = Message.obtain().apply {
                what = 1
                obj = "A"
            }

            mUIHandler.sendMessage(msg)
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

            mUIHandler.sendMessage(msg)
        }.start()
    }

    private fun workThreadHandlerTest() {
        Thread {
            Looper.prepare()
            val myLooper = Looper.myLooper()
            Log.d("DYJDebug", "CurrentThread is ${Thread.currentThread()}, Mylooper is $myLooper")

            val mWorkHandler = MyWorkHandler(myLooper!!)

            Thread {
                Log.d("DYJDebug", "CurrentThread is ${Thread.currentThread()}")
                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                val msg = Message.obtain().apply {
                    what = 1
                    obj = "work complete"
                }

                mWorkHandler.sendMessage(msg)
            }.start()
        }.start()
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

        class MyUIHandler internal constructor(context: ThreadActivity, looper: Looper): Handler(looper) {

            private val activityReference: WeakReference<ThreadActivity> = WeakReference(context)

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when(msg.what) {
                    1 -> activityReference.get()?.HandlerTestState?.text = "执行了线程1的UI操作"
                    2 -> activityReference.get()?.HandlerTestState?.text = "执行了线程2的UI操作"
                }
            }
        }

        class MyWorkHandler internal constructor(looper: Looper): Handler(looper) {

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.d("DYJDebug", "Handler msg")
                when(msg.what) {
                    1 -> Log.d("DYJDebug", "WorkThread received the msg ${msg.obj}")
                }
            }
        }
    }
}