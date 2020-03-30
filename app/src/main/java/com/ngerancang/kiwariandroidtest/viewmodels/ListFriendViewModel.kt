package com.ngerancang.kiwariandroidtest.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.ngerancang.kiwariandroidtest.App
import com.ngerancang.kiwariandroidtest.DataPreference
import com.ngerancang.kiwariandroidtest.callback.ListFriendListener
import com.ngerancang.kiwariandroidtest.models.User

class ListFriendViewModel : ViewModel() {

    var listFriendListener : ListFriendListener ?= null


    fun getAllFriends(){
        val mutableList : MutableList<User> = mutableListOf()
        App.mReference.child("users").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                listFriendListener!!.onFailure("Gagal karena ${p0.message}")

            }

            override fun onDataChange(data: DataSnapshot) {
                val list = data.children
                list.forEach {
                    val key = it.key
                    println(key)
                    if(key != DataPreference.getUid()){
                        val user = it.getValue(User::class.java)
                        user!!.uid = key
                        println(user.toString())
                        mutableList.add(user)
                    }
                }
                listFriendListener!!.onSuccess(mutableList)
            }

        })
    }

    fun logoutAction(){
        App.mAuth.signOut()
    }
}