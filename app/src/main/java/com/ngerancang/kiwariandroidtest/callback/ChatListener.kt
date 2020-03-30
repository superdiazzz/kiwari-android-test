package com.ngerancang.kiwariandroidtest.callback

import com.ngerancang.kiwariandroidtest.models.Message

interface ChatListener {

    fun onStarted()
    fun onSuccess(lsMsg : MutableList<Message>)
    fun updateMessage(msg: Message)
    fun onFailure(msg: String)
    fun onRefresh()
}