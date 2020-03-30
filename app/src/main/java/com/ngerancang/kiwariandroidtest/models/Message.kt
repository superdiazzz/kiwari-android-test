package com.ngerancang.kiwariandroidtest.models

data class Message(
    var message: String ?= null,
    var date: String ?= null,
    var name: String ?= null
) {
    override fun toString(): String {
        return "Message(message=$message, date=$date, name=$name)"
    }
}