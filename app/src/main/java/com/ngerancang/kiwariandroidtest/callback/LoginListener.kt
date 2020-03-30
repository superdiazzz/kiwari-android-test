package com.ngerancang.kiwariandroidtest.callback

interface LoginListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(msg: String)

}