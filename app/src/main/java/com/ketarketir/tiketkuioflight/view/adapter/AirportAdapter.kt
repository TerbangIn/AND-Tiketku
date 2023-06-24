package com.ketarketir.tiketkuioflight.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.model.airport.DataResponseAirport

class AirportAdapter (context: Context, private val airportList: List<DataResponseAirport>) :
    ArrayAdapter<DataResponseAirport>(context, 0, airportList) {



    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}