package com.elijah.androidlearning.jetpack.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class LifeCycleObserver2 : LifecycleEventObserver {
    private val TAG = "LifeCycleObserver2"

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> println("$TAG, ON_CREATE")
            Lifecycle.Event.ON_START -> println("$TAG, ON_START")
            Lifecycle.Event.ON_RESUME -> println("$TAG, ON_RESUME")
            Lifecycle.Event.ON_PAUSE -> println("$TAG, ON_PAUSE")
            Lifecycle.Event.ON_STOP -> println("$TAG, ON_STOP")
            Lifecycle.Event.ON_DESTROY -> println("$TAG, ON_DESTROY")
            Lifecycle.Event.ON_ANY -> println("$TAG, ON_ANY")
        }
    }
}