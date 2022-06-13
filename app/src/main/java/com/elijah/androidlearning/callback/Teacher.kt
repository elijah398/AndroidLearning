package com.elijah.androidlearning.callback

import android.util.Log

class Teacher constructor(student: Student): CallBack {
    private val student = student

    fun askStudent() {
        student.resolveQuestion(this)
    }

    override fun tellAnswer(answer: String) {
        super.tellAnswer(answer)
        Log.d("DYJDebug", "Answer is $answer")
    }
}