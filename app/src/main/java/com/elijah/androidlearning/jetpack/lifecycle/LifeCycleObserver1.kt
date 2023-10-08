package com.elijah.androidlearning.jetpack.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifeCycleObserver1 : LifecycleObserver {
    private val TAG = "LifeCycleObserver1"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() {
        println("$TAG, ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        println("$TAG, ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        println("$TAG, ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        println("$TAG, ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        println("$TAG, ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        println("$TAG, ON_DESTROY")
    }
}