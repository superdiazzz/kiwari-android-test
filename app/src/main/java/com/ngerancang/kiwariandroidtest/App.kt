package com.ngerancang.kiwariandroidtest

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.orhanobut.hawk.Hawk

class App : MultiDexApplication() {

    companion object{
        lateinit var mAuth : FirebaseAuth
        lateinit var mReference : DatabaseReference
        lateinit var mDb : FirebaseFirestore
    }


    override fun onCreate() {
        super.onCreate()

        // init hawk
        Hawk.init(this).build()

        mAuth = FirebaseAuth.getInstance()

        mReference = FirebaseDatabase.getInstance().reference

        mDb = FirebaseFirestore.getInstance()

    }
}