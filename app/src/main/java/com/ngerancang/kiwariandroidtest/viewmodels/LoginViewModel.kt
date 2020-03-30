package com.ngerancang.kiwariandroidtest.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.ngerancang.kiwariandroidtest.App
import com.ngerancang.kiwariandroidtest.DataPreference
import com.ngerancang.kiwariandroidtest.callback.LoginListener
import com.ngerancang.kiwariandroidtest.models.User
import com.ngerancang.kiwariandroidtest.repositories.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(){

    var loginListener : LoginListener ?= null


    fun loginAction(email: String, password: String){

        App.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            loginListener!!.onStarted()
            if(it.isSuccessful){
                saveDataToPref(App.mAuth.currentUser!!.uid)
            }else{
                loginListener!!.onFailure("Gagal login karena")
            }
        }

    }

    private fun saveDataToPref(uid: String) {
        App.mReference.child("users").child(uid).addValueEventListener(object : ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {
                loginListener!!.onFailure("Error karena ${p0.message}")
            }

            override fun onDataChange(data: DataSnapshot) {

                val user = data.getValue(User::class.java)
                user?.let {
                    DataPreference.save(uid, user.name!!, user.avatar!!)
                    loginListener!!.onSuccess()
                }


            }

        })

    }


}