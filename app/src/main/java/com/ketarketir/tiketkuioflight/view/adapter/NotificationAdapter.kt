package com.ketarketir.tiketkuioflight.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketarketir.tiketkuioflight.databinding.ItemNotificationBinding
import com.ketarketir.tiketkuioflight.model.notifications.Data

class NotificationAdapter(var notifications: List<Data>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationBinding.inflate(inflater, parent, false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.bind(notification)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    inner class NotificationViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notification: Data) {
            binding.tvTitle.text = notification.title
            binding.tvDate.text = notification.createdAt
            binding.tvPromotionAlert.text = notification.tag
            binding.tvDesc.text = notification.desc
        }
    }
}