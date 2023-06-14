package com.ketarketir.tiketkuioflight.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.model.notifications.Data

class NotificationAdapter(var notifications: List<Data>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.bind(notification)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(notification: Data) {
            itemView.findViewById<TextView>(R.id.tv_title).text = notification.title
            itemView.findViewById<TextView>(R.id.tv_date).text = notification.createdAt
            itemView.findViewById<TextView>(R.id.tv_promotion_alert).text = notification.tag
            itemView.findViewById<TextView>(R.id.tv_desc).text = notification.desc
        }
    }
}
