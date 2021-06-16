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
import com.sofrosyn.a6gnlsolarcalculator.adapter.TroubleshootAdapter.TroubleshootHolder
import com.sofrosyn.a6gnlsolarcalculator.modals.Troubleshoot
import java.util.*

class TroubleshootAdapter(var context: Context, private var troubleshootArrayList: ArrayList<Troubleshoot>) : RecyclerView.Adapter<TroubleshootHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TroubleshootHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_troubleshoot, parent, false)
        return TroubleshootHolder(view)
    }

    override fun onBindViewHolder(holder: TroubleshootHolder, position: Int) {
        val troubleshoot = troubleshootArrayList[position]
        holder.subtitle.text = troubleshoot.subtitle
        holder.title.text = troubleshoot.title
        holder.cardView.setOnClickListener { v: View? -> Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show() }
    }

    override fun getItemCount(): Int {
        return troubleshootArrayList.size
    }

    inner class TroubleshootHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: MaterialCardView = itemView.findViewById(R.id.item_troubleshoot_cardview)
        var title: TextView = itemView.findViewById(R.id.item_troubleshoot_title)
        var subtitle: TextView = itemView.findViewById(R.id.item_troubleshoot_subtitle)

    }
}