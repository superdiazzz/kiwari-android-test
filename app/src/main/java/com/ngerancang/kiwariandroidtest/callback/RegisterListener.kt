package com.ngerancang.kiwariandroidtest.callback

interface RegisterListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(msg: String)

}