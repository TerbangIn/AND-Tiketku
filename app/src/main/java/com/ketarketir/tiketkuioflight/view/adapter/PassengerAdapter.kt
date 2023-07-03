package com.ketarketir.tiketkuioflight.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.ItemBiodataPassengerBinding
import com.ketarketir.tiketkuioflight.databinding.ItemResultTicketBinding
import com.ketarketir.tiketkuioflight.model.passenger.Data

class PassengerAdapter(private var listPassenger:List<Data>, val context: Context) :RecyclerView.Adapter<PassengerAdapter.ViewHolder>() {


    class ViewHolder(var binding:ItemBiodataPassengerBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemBiodataPassengerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PassengerAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPassenger.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}