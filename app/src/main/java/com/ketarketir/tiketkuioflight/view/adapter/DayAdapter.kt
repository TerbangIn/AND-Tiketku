package com.ketarketir.tiketkuioflight.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketarketir.tiketkuioflight.databinding.ItemDayBinding
import com.ketarketir.tiketkuioflight.model.flight.Data
import com.ketarketir.tiketkuioflight.model.flight.Day

class DayAdapter(private var lisyDay: List<Day>) : RecyclerView.Adapter<DayAdapter.ViewHolder>() {

    var onClick : ((Day)->Unit)? = null
    var dateDeparture : String? = null

    class ViewHolder(var binding : ItemDayBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lisyDay.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = lisyDay[position]
        holder.binding.tvDay.text = list.day
        holder.binding.tvDate.text = list.date
        holder.binding.cvDay.setOnClickListener{
            onClick?.invoke(list)
        }
    }
}