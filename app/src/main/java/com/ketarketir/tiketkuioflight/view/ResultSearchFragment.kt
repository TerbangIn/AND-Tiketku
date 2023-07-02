package com.ketarketir.tiketkuioflight.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentResultSearchBinding
import com.ketarketir.tiketkuioflight.databinding.FragmentSendOTPBinding
import com.ketarketir.tiketkuioflight.model.flight.Day
import com.ketarketir.tiketkuioflight.view.adapter.AirportAdapter
import com.ketarketir.tiketkuioflight.view.adapter.DayAdapter
import com.ketarketir.tiketkuioflight.view.adapter.FlightAdapter
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel
import com.ketarketir.tiketkuioflight.viewmodel.ResultSearchViewModel
import java.text.SimpleDateFormat
import java.util.*

class ResultSearchFragment : Fragment() {

    private lateinit var binding: FragmentResultSearchBinding

    private val resultSearchViewModel: ResultSearchViewModel by activityViewModels()
    private val homeViewModel:HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        listDay()
        showFlight()
    }

    fun showFlight(){
        resultSearchViewModel.getToken()
        resultSearchViewModel.token.observe(viewLifecycleOwner, Observer {
            val token = it
            resultSearchViewModel.callApiGetListFlight(token)
            resultSearchViewModel.listFligh.observe(viewLifecycleOwner, Observer {
                if (it!= null){
                    val adapter = FlightAdapter(it)
                    binding.rvFlight.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.rvFlight.adapter = adapter
                    adapter.onClick = {
                        findNavController().navigate(R.id.action_resultSearchFragment_to_biodataBookingFragment)
                    }
                } else{
                    Toast.makeText(context, "null", Toast.LENGTH_SHORT).show()
                }
            })
        })
    }

    fun listDay(){
        val date = homeViewModel.selectedStartDate.value
        val selectedDate = formatDate(date!!)

        val dateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance()

        calendar.time = dateFormat.parse(selectedDate) ?: Date()

        val daysList = mutableListOf<Day>()

        for (i in 1..7) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            val currentDate = calendar.time
            val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(currentDate)
            val date = dateFormat.format(currentDate)

            daysList.add(Day(dayOfWeek, date))
        }

        binding.rvDay.adapter = DayAdapter(daysList)
        binding.rvDay.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

//        homeViewModel.selectedStartDate.observe(viewLifecycleOwner, Observer {
//            val date = it
//            val selectedDate = formatDate(date!!)
//
//            val dateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
//            val calendar = Calendar.getInstance()
//
//            calendar.time = dateFormat.parse(selectedDate) ?: Date()
//
//            val daysList = mutableListOf<Day>()
//
//            for (i in -7..7) {
//                calendar.add(Calendar.DAY_OF_MONTH, i)
//                val currentDate = calendar.time
//                val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(currentDate)
//                val date = dateFormat.format(currentDate)
//
//                daysList.add(Day(dayOfWeek, date))
//            }
//
//            binding.rvDay.adapter = DayAdapter(daysList)
//            binding.rvDay.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        })
    }
    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }
}