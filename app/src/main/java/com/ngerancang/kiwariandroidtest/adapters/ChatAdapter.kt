package com.ngerancang.kiwariandroidtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ngerancang.kiwariandroidtest.R
import com.ngerancang.kiwariandroidtest.models.Message
import kotlinx.android.synthetic.main.chat_row.view.*

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {


    private var lsMessage: MutableList<Message> = mutableListOf()

    fun addMessage(messages: MutableList<Message>){
        this.lsMessage = messages
        notifyDataSetChanged()
    }

//    fun addSingleMessage(msg: Message){
//        this.lsMessage.add(msg)
//        notifyDataSetChanged()
//    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(msg: Message){
            itemView.msg_chat.text = msg.message
            itemView.date_chat.text = msg.date
            itemView.name_chat.text = msg.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.chat_row, parent, false)
        return ChatViewHolder(v)
    }

    override fun getItemCount(): Int = lsMessage.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        val msg = lsMessage[position]
        holder.bindItem(msg)

    }
}