package com.elijah.androidlearning.lrucache

import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache

object LruCacheUtils {
    fun memoryCache()  {
        //获取当前进程的可用内存，单位是KB；
        val maxMemory = (Runtime.getRuntime().totalMemory() / 1024).toInt()
        Log.d("DYJDebug", "Runtime Memory is $maxMemory}")
        val cacheSize = maxMemory / 8
        val mMemoryCache = LruCache<String, Bitmap>(cacheSize)
    }
}