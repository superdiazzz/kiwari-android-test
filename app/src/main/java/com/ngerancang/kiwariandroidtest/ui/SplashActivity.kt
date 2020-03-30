package com.ngerancang.kiwariandroidtest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.ngerancang.kiwariandroidtest.DataPreference
import com.ngerancang.kiwariandroidtest.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        retrieveTokenForMessaging()

        Handler().postDelayed({

            if(DataPreference.getUid() != DataPreference.DATA_NONE){
                startActivity(Intent(this, ListFriendActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }, 3000)


    }

    private fun retrieveTokenForMessaging() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    println("failed ${task.exception?.message}")
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                // Log and toast
                //val msg = getString(R.string.msg_token_fmt, token)
                println("==> ${token}")
                //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })
    }
}
