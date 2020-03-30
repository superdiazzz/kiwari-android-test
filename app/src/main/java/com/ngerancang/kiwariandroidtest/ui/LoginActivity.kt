package com.ngerancang.kiwariandroidtest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.ngerancang.kiwariandroidtest.AppViewModelFactory
import com.ngerancang.kiwariandroidtest.R
import com.ngerancang.kiwariandroidtest.callback.LoginListener
import com.ngerancang.kiwariandroidtest.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginListener {


    private var viewModel : LoginViewModel ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = createViewModel()
        viewModel!!.loginListener = this

        login_btn.setOnClickListener {

            // check validation
            if(valid()){
                viewModel!!.loginAction(
                    user_name.text.toString(),
                    user_password.text.toString()
                )
            }
        }

        register_btn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }




    }

    private fun createViewModel(): LoginViewModel? {
        val factory = AppViewModelFactory()
        return ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)

    }

    private fun valid(): Boolean {
        return when{
            user_name.text.toString().isNotEmpty() &&
                    user_password.text.toString().isNotEmpty() -> true
            else -> false
        }

    }

    override fun onStarted() {
        progress.visibility = View.VISIBLE

    }

    override fun onSuccess() {
        progress.visibility = View.GONE
        startActivity(Intent(this, ListFriendActivity::class.java))
        finish()


    }

    override fun onFailure(msg: String) {
        progress.visibility = View.GONE

    }
}
