package com.ketarketir.tiketkuioflight.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.ItemAirportBinding
import com.ketarketir.tiketkuioflight.model.airport.DataAirport
import com.ketarketir.tiketkuioflight.model.airport.DataResponseAirport
import java.util.*

class AirportAdapter (var listAirport:List<DataAirport>) : RecyclerView.Adapter<AirportAdapter.ViewHolder>()
{
    private var filteredAirportList: List<DataAirport> = listAirport.toList()
    var onClick : ((DataAirport)->Unit)? = null
    class ViewHolder(var binding: ItemAirportBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportAdapter.ViewHolder {
        val view = ItemAirportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AirportAdapter.ViewHolder, position: Int) {
        val list = listAirport[position]
        holder.binding.textViewAirportCity.text = list.city
        holder.binding.textViewAirportCode.text = list.code
        holder.binding.clickDetail.setOnClickListener{
            onClick?.invoke(list)
        }
    }

    override fun getItemCount(): Int {
        return listAirport.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterAirportList(query: String) {
        filteredAirportList = if (query.isNotEmpty()) {
            val searchQuery = query.toLowerCase(Locale.getDefault())
            listAirport.filter { airport ->
                airport.city.toLowerCase(Locale.getDefault()).contains(searchQuery) ||
                        airport.code.toLowerCase(Locale.getDefault()).contains(searchQuery)
            }
        } else {
            listAirport.toList()
        }
        notifyDataSetChanged()
    }


}