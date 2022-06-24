package com.elijah.androidlearning.thread

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object ThreadPool {
    private const val CORE_POOL_SIZE = 10
    private const val MAXIMUM_POOL_SIZE = 20
    private const val KEEP_ALIVE = 120L
    private val sPoolWorkQueue = LinkedBlockingQueue<Runnable>()

    private val mThreadPool = ThreadPoolExecutor(CORE_POOL_SIZE,
                                MAXIMUM_POOL_SIZE,
                                KEEP_ALIVE,
                                TimeUnit.SECONDS,
                                sPoolWorkQueue);

    fun execute(task: Runnable) {
        try {
            mThreadPool.execute(task)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}