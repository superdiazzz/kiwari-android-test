package com.ngerancang.kiwariandroidtest.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProviders
import com.ngerancang.kiwariandroidtest.AppViewModelFactory
import com.ngerancang.kiwariandroidtest.R
import com.ngerancang.kiwariandroidtest.callback.RegisterListener
import com.ngerancang.kiwariandroidtest.utilities.toast
import com.ngerancang.kiwariandroidtest.viewmodels.LoginViewModel
import com.ngerancang.kiwariandroidtest.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.register_btn

class RegisterActivity : AppCompatActivity(), RegisterListener {


    var viewModel: RegisterViewModel ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = createViewModel()
        viewModel!!.registerListener = this


        register_btn.setOnClickListener {
            if(valid()){
                closeKeyboard()
                viewModel!!.register(create_user_name.text.toString(),
                    create_user_email.text.toString(),
                    create_user_password.text.toString()
                )
            }
        }

    }

    private fun closeKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(create_user_name.windowToken, 0)
        imm.hideSoftInputFromWindow(create_user_email.windowToken, 0)
        imm.hideSoftInputFromWindow(create_user_password.windowToken, 0)
    }

    private fun createViewModel(): RegisterViewModel? {
        val factory = AppViewModelFactory()
        return ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java)

    }

    private fun valid(): Boolean {
        return when{
            create_user_name.text.toString().isNotEmpty() &&
                    create_user_email.text.toString().isNotEmpty() &&
            create_user_password.text.toString().isNotEmpty() -> true
            else -> false
        }

    }

    override fun onStarted() {
        progress_register.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progress_register.visibility = View.GONE
        startActivity(Intent(this, ListFriendActivity::class.java))
        finish()
    }

    override fun onFailure(msg: String) {
        progress_register.visibility = View.GONE
        this.toast(msg)
    }
}
