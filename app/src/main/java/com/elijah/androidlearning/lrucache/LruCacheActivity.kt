package com.elijah.androidlearning.lrucache

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.LruCache
import com.elijah.androidlearning.R

class LruCacheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lru_cache)
    }

    private fun initLruCache() {
    }
}