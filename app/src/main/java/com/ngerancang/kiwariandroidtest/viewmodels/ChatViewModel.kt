package com.ngerancang.kiwariandroidtest.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.ngerancang.kiwariandroidtest.App
import com.ngerancang.kiwariandroidtest.callback.ChatListener
import com.ngerancang.kiwariandroidtest.models.Message
import com.ngerancang.kiwariandroidtest.utilities.getDate

class ChatViewModel : ViewModel() {

    var chatListener : ChatListener ?= null

    fun fetchMessage(uid: String, uidFriend: String){

        val listMsg = mutableListOf<Message>()

        App.mDb.collection("room/${uid}/${uidFriend}")
            .addSnapshotListener { querySnapshot, exception ->

                if(exception != null){
                    println("Listen Failed ${exception.message}")
                    return@addSnapshotListener
                }

                if(querySnapshot != null){
                    listMsg.clear()
                    for(document in querySnapshot){
                        if(document.exists()){
                            val msg = document.toObject(Message::class.java)
                            val thisdate = msg.date
                            msg.date = getDate(thisdate!!.toLong())
                            listMsg.add(msg)
                        }
                    }
                    chatListener!!.onSuccess(listMsg)

                }else{
                    println("data null")
                }

            }
    }

    fun postMessage(uid: String, uidFriend: String, msg: String, date: String, name: String){
        val message = Message(msg, date, name)

        App.mDb.collection("room/${uid}/${uidFriend}")
            .add(message)
            .addOnSuccessListener { docOne ->
                App.mDb.collection("room/${uidFriend}/${uid}")
                    .add(message)
                    .addOnSuccessListener { docTwo ->
                        println("sukses add ${docTwo.id}")

                    }
                    .addOnFailureListener {
                        println("gagal 2 ${it.message}")
                    }
            }
            .addOnFailureListener {
                println("gagal 1 ${it.message}")
            }

    }



}