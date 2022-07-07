package com.elijah.androidlearning.thread

import android.util.Log
import kotlinx.android.synthetic.main.activity_thread.*
import kotlinx.coroutines.*
import java.lang.Thread.sleep

class CoroutineTest constructor (val context: ThreadActivity) {
    private val mScope = MainScope();

    companion object {
        const val TAG = "CoroutineTest"
    }


    fun showUserInfoByLaunch() {
        val job = mScope.launch(CoroutineName("showUserInfoByLaunch"), CoroutineStart.LAZY) {
            Log.d("DYJDebug $TAG", "showUserInfoByLaunch mScope.launch CoroutineName is ${this.coroutineContext[CoroutineName]}")
            Log.d("DYJDebug $TAG", "showUserInfoByLaunch mScope.launch CurrentThread is ${Thread.currentThread().name}")
            val userInfo  = getUserInfo()
            context.CoroutineTestInfo.text = userInfo
        }
        Log.d("DYJDebug $TAG", "showUserInfoByLaunch CurrentThread is ${Thread.currentThread().name}")
        sleep(1000)
        try {
            job.cancel("just cancel the job")
        } catch (e: CancellationException) {
            e.printStackTrace()
        }
    }

    fun showUserInfoByAsync() {
        val job = mScope.launch {
            Log.d("DYJDebug $TAG", "showUserInfo mScope.launc CurrentThread is ${Thread.currentThread().name}")
            val userInfo  = async(Dispatchers.IO) {
                Log.d("DYJDebug $TAG", "showUserInfoByAsync async CurrentThread is ${Thread.currentThread().name}")
                delay(2000)
                "Kotlin-async"
            }
            context.CoroutineTestInfo.text = userInfo.await()
        }
    }

    private suspend fun getUserInfo(): String {
        return withContext(Dispatchers.IO + CoroutineName("getUserInfo")) {
            delay(2000)
            Log.d("DYJDebug $TAG", "getUserInfo CoroutineName is ${this.coroutineContext[CoroutineName]}")
            Log.d("DYJDebug $TAG", "getUserInfo CurrentThread is ${Thread.currentThread().name}")
            "Kotlin-launch"
        }
    }
}