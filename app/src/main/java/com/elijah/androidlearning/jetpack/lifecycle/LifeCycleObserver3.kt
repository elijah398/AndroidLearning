package com.elijah.androidlearning.jetpack.lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class LifeCycleObserver3 : DefaultLifecycleObserver {
    private val TAG = "LifeCycleObserver3"

    override fun onCreate(owner: LifecycleOwner) {
        println("$TAG, ON_CREATE")
    }

    override fun onStart(owner: LifecycleOwner) {
        println("$TAG, ON_START")
    }

    override fun onResume(owner: LifecycleOwner) {
        println("$TAG, ON_RESUME")
    }

    override fun onPause(owner: LifecycleOwner) {
        println("$TAG, ON_PAUSE")
    }

    override fun onStop(owner: LifecycleOwner) {
        println("$TAG, ON_STOP")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        println("$TAG, ON_DESTROY")
    }
}