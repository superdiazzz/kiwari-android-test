package com.ngerancang.kiwariandroidtest.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ngerancang.kiwariandroidtest.AppViewModelFactory
import com.ngerancang.kiwariandroidtest.DataPreference
import com.ngerancang.kiwariandroidtest.R
import com.ngerancang.kiwariandroidtest.adapters.FriendsAdapter
import com.ngerancang.kiwariandroidtest.callback.ListFriendListener
import com.ngerancang.kiwariandroidtest.models.User
import com.ngerancang.kiwariandroidtest.utilities.toast
import com.ngerancang.kiwariandroidtest.viewmodels.ListFriendViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_friend.*

class ListFriendActivity : AppCompatActivity(), ListFriendListener {

    private var viewModel : ListFriendViewModel ?= null
    private var adapter : FriendsAdapter ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_friend)

        setSupportActionBar(toolbar)

        Picasso.get().load(DataPreference.getImage()).into(profile_img)
        user_name.text = DataPreference.getName()

        viewModel = createViewModel()
        viewModel!!.listFriendListener = this
        viewModel!!.getAllFriends()
        adapter = FriendsAdapter(this)
        list_friends.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.logout_menu -> {
                DataPreference.remove()
                viewModel!!.logoutAction()
                startActivity(Intent(this, LoginActivity::class.java))
                toast("Kamu sudah Logout dari aplikasi")
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    private fun createViewModel(): ListFriendViewModel? {
        val factory = AppViewModelFactory()
        return ViewModelProviders.of(this, factory).get(ListFriendViewModel::class.java)

    }

    override fun onStarted() {

    }

    override fun onSuccess(listFriends: MutableList<User>) {
        adapter!!.addFriends(listFriends)
    }

    override fun onFailure(msg: String) {
        toast(msg)
    }
}
