package com.sofrosyn.a6gnlsolarcalculator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.sofrosyn.a6gnlsolarcalculator.R
import com.sofrosyn.a6gnlsolarcalculator.adapter.NotificationAdapter.NotificationHolder
import com.sofrosyn.a6gnlsolarcalculator.modals.Notification
import java.util.*

class NotificationAdapter(var context: Context, private var notificationArrayList: ArrayList<Notification>) : RecyclerView.Adapter<NotificationHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_notification, parent, false)
        return NotificationHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        val notification = notificationArrayList[position]
        holder.subtitle.text = notification.subtitle
        holder.title.text = notification.title
        holder.cardView.setOnClickListener { Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show() }
    }

    override fun getItemCount(): Int {
        return notificationArrayList.size
    }

    inner class NotificationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: MaterialCardView = itemView.findViewById(R.id.notification_item_card)
        var title: TextView = itemView.findViewById(R.id.notification_item_title)
        var subtitle: TextView = itemView.findViewById(R.id.notification_item_subtitle)

    }
}