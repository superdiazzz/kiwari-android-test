package com.ngerancang.kiwariandroidtest.models

data class User(var uid: String? = null,
                var name : String? = null,
                var email: String? = null,
                var avatar: String? = null) {

    override fun toString(): String {
        return "User(uid=$uid, name=$name, email=$email, avatar=$avatar)"
    }
}
