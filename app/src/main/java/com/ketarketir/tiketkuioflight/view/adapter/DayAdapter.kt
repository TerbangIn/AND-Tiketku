package com.ketarketir.tiketkuioflight.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.ItemDayBinding
import com.ketarketir.tiketkuioflight.model.flight.Data
import com.ketarketir.tiketkuioflight.model.flight.Day

class DayAdapter(private var lisyDay: List<Day>) : RecyclerView.Adapter<DayAdapter.ViewHolder>() {

    var onClick : ((Day)->Unit)? = null
    private var selectedItemPosition = RecyclerView.NO_POSITION
    class ViewHolder(var binding : ItemDayBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lisyDay.size
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val list = lisyDay[position]
        holder.binding.tvDay.text = list.day
        holder.binding.tvDate.text = list.date
        updateItemSelection(holder, position)
        holder.binding.cvDay.setOnClickListener{
            onClick?.invoke(list)
//            val previousSelectedItem = selectedItemPosition
//            selectedItemPosition = holder.adapterPosition
//            notifyItemChanged(previousSelectedItem)
//            notifyItemChanged(selectedItemPosition)
            val previousSelectedItem = selectedItemPosition
            selectedItemPosition = position

            notifyItemChanged(previousSelectedItem)
            notifyItemChanged(selectedItemPosition)
            updateItemSelection(holder, position)
        }
    }

    private fun updateItemSelection(holder: ViewHolder, position: Int) {
        if (selectedItemPosition == position) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.darkblue03))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.neutral01))
        }
    }
}