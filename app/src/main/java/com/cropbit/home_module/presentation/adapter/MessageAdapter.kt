package com.cropbit.home_module.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cropbit.databinding.ItemChatBotMessagingBinding
import com.cropbit.utils.models.bot_bit_feature.Message

class MessageAdapter(private val messageList: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemChatBotMessagingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val message = messageList[position]
        if (message.sentBy == Message.SENT_BY_ME) {
            holder.binding.leftChatView.isVisible = false
            holder.binding.rightChatView.isVisible = true
            holder.binding.botImage.isVisible = false
            holder.binding.userImage.isVisible = true
            holder.binding.rightChatTextView.text = message.message
        } else {
            holder.binding.leftChatView.isVisible = true
            holder.binding.rightChatView.isVisible = false
            holder.binding.botImage.isVisible = true
            holder.binding.userImage.isVisible = false
            holder.binding.leftChatTextView.text = message.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemChatBotMessagingBinding.bind(itemView)
    }
}
