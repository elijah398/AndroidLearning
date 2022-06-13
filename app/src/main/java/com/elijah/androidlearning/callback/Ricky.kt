package com.elijah.androidlearning.callback

class Ricky: Student {

    override fun resolveQuestion(callback: CallBack) {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        callback.tellAnswer("嘻嘻")
    }
}