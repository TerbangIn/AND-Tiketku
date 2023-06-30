package com.ketarketir.tiketkuioflight.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketarketir.tiketkuioflight.databinding.ItemResultTicketBinding
import com.ketarketir.tiketkuioflight.model.airport.DataAirport
import com.ketarketir.tiketkuioflight.model.flight.Data
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class FlightAdapter(var listFlight: List<Data>) : RecyclerView.Adapter<FlightAdapter.ViewHolder>() {
    var onClick : ((Data)->Unit)? = null
    class ViewHolder(var binding:ItemResultTicketBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemResultTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFlight.size
    }

    @SuppressLint("NewApi", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listFlight[position]
        val departureDate = list.departureDate
        val arrivalDate = list.arrivalDate
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")

        val departureTime = LocalDateTime.parse(departureDate, formatter)
        val formattedDepartureTime = departureTime.format(DateTimeFormatter.ofPattern("HH:mm"))

        val arrivalTime = LocalDateTime.parse(arrivalDate, formatter)
        val formattedArrivalTime = arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm"))

        val duration = Duration.between(departureTime, arrivalTime)
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60

        holder.binding.tvDeparture.text = formattedDepartureTime
        holder.binding.tvArrival.text = formattedArrivalTime
        holder.binding.tvPrice.text = list.economyClassPrice.toString()
        holder.binding.tvEstimate.text = "$hours h" + " " + "$minutes m"
        holder.binding.tvAircraftType.text = list.airline
        holder.binding.cvFlight.setOnClickListener{
            onClick?.invoke(list)
        }

    }

}