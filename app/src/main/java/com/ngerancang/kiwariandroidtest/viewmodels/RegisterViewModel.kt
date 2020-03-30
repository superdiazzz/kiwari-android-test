package com.ngerancang.kiwariandroidtest.viewmodels

import androidx.lifecycle.ViewModel
import com.ngerancang.kiwariandroidtest.App
import com.ngerancang.kiwariandroidtest.DataPreference
import com.ngerancang.kiwariandroidtest.callback.RegisterListener

class RegisterViewModel : ViewModel() {

    var registerListener: RegisterListener? = null

    fun register(username : String, email: String, password: String){

        registerListener?.onStarted()
        App.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                createUserData(App.mAuth.currentUser!!.uid, username, email)
            }else{
                registerListener!!.onFailure("Gagal karena ${it.exception!!.message}")
            }
        }
    }

    private fun createUserData(idUser: String, username: String, email: String) {

        val image = "https://api.adorable.io/avatars/160/jarjit@mail.com.png"
        val hashData = HashMap<String, String>()
        hashData["avatar"] = image
        hashData["email"] = email
        hashData["name"] = username

        App.mReference.child("users").child(idUser).setValue(hashData).addOnCompleteListener {
            if(it.isSuccessful){
                DataPreference.save(idUser, username, image)
                registerListener!!.onSuccess()
                // callback success
            }else{
                registerListener!!.onFailure("Gagal menambah user karena ${it.exception!!.message}")
                // callback failed
            }
        }

    }

}