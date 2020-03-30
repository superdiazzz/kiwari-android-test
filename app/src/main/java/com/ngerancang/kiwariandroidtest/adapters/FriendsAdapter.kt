package com.ngerancang.kiwariandroidtest.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ngerancang.kiwariandroidtest.R
import com.ngerancang.kiwariandroidtest.models.User
import com.ngerancang.kiwariandroidtest.ui.ChatActivity
import com.ngerancang.kiwariandroidtest.utilities.Constant.Companion.EXTRA_NAME_FRIEND
import com.ngerancang.kiwariandroidtest.utilities.Constant.Companion.EXTRA_UID_FRIEND
import com.ngerancang.kiwariandroidtest.utilities.toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.friend_row.view.*

class FriendsAdapter(private val context: Context) : RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {


    private var listFriend : MutableList<User> ?= null


    init {
        listFriend = mutableListOf()
    }

    fun addFriends(listFriend: MutableList<User>){
        this.listFriend = listFriend
        notifyDataSetChanged()
    }

    class FriendsViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

        fun bindData(user: User){
            val imageFriend = itemView.profile_friend
            Picasso.get().load(user.avatar).into(imageFriend)

            itemView.name_friend.text = user.name

            itemView.setOnClickListener {
                //context.toast("Your friend is ${user.name}")
                val intent = Intent(context, ChatActivity::class.java).apply {
                    this.putExtra(EXTRA_NAME_FRIEND, user.name)
                    this.putExtra(EXTRA_UID_FRIEND, user.uid)
                }
                context.startActivity(intent)

            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.friend_row, parent, false)
        return FriendsViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return if(listFriend == null){
            0
        }else{
            listFriend!!.size
        }
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {

        listFriend?.let {
            val myFriend = it[position]
            holder.bindData(myFriend)
        }
    }
}