package com.ketarketir.tiketkuioflight.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentLoadingSearchFlightBinding
import com.ketarketir.tiketkuioflight.model.flight.Day
import com.ketarketir.tiketkuioflight.view.adapter.DayAdapter
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*


class LoadingSearchFlightFragment : Fragment() {
    private lateinit var binding:FragmentLoadingSearchFlightBinding
    private val homeViewModel:HomeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoadingSearchFlightBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            listDay()
            findNavController().navigate(R.id.action_loadingSearchFlightFragment_to_resultSearchFragment)
        }, 2000)
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

        val adapter = DayAdapter(daysList)
        binding.rvDay.adapter = adapter
        binding.rvDay.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        adapter.onClick={
            findNavController().navigate(R.id.action_resultSearchFragment_to_loadingSearchFlightFragment)
        }

    }
    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }

}