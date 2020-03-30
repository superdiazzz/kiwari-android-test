package com.ngerancang.kiwariandroidtest.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ngerancang.kiwariandroidtest.AppViewModelFactory
import com.ngerancang.kiwariandroidtest.DataPreference
import com.ngerancang.kiwariandroidtest.R
import com.ngerancang.kiwariandroidtest.adapters.ChatAdapter
import com.ngerancang.kiwariandroidtest.callback.ChatListener
import com.ngerancang.kiwariandroidtest.models.Message
import com.ngerancang.kiwariandroidtest.utilities.Constant.Companion.EXTRA_NAME_FRIEND
import com.ngerancang.kiwariandroidtest.utilities.Constant.Companion.EXTRA_UID_FRIEND
import com.ngerancang.kiwariandroidtest.utilities.toast
import com.ngerancang.kiwariandroidtest.viewmodels.ChatViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*

class ChatActivity : AppCompatActivity(), ChatListener {

    private var viewModel : ChatViewModel ?= null
    private var chatAdapter : ChatAdapter ?= null

    var uidFriend : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        viewModel = createViewModel()
        chatAdapter = ChatAdapter()
        recyclerview_chat.adapter = chatAdapter

        val nameFriend = intent.getStringExtra(EXTRA_NAME_FRIEND)
        uidFriend = intent.getStringExtra(EXTRA_UID_FRIEND)


        viewModel!!.chatListener = this
        viewModel!!.fetchMessage(DataPreference.getUid(), uidFriend!!)


        friend_name.text = nameFriend
        Picasso.get().load("https://api.adorable.io/avatars/160/jarjit@mail.com.png").into(profile_img)

        send_btn.setOnClickListener {

            val msgText = input_message.text.toString()

            if(msgText.isNotEmpty()){
                val id = DataPreference.getUid()
                val nameUser = DataPreference.getName()
                val date = Date().time.toString()
                //val msg = Message(id, uidFriend, nameUser, msgText, date)

                viewModel!!.postMessage(id, uidFriend!!, msgText, date, nameUser)
                hideKeyboard()
            }

        }
    }




    private fun createViewModel(): ChatViewModel? {
        val factory = AppViewModelFactory()
        return ViewModelProviders.of(this, factory).get(ChatViewModel::class.java)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input_message.windowToken, 0)
        input_message.setText("")
    }

    override fun onStarted() {

    }

    override fun onSuccess(lsMsg: MutableList<Message>) {

        chatAdapter!!.addMessage(lsMsg)
        recyclerview_chat.scrollToPosition(lsMsg.size-1)
    }

    override fun updateMessage(msg: Message) {
    }

    override fun onFailure(msg: String) {
        toast(msg)
    }

    override fun onRefresh() {
        viewModel!!.fetchMessage(DataPreference.getUid(), uidFriend!!)
    }
}
