package com.ngerancang.kiwariandroidtest.callback

import com.ngerancang.kiwariandroidtest.models.User

interface ListFriendListener {

    fun onStarted()
    fun onSuccess(listFriends : MutableList<User>)
    fun onFailure(msg: String)
}