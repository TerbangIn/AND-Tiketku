package com.ketarketir.tiketkuioflight.view

import DestinationAdapter
import DestinationViewModel
import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBottomSheetHomeSearchDestinationBinding
import com.ketarketir.tiketkuioflight.databinding.FragmentHomeBinding
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var destinationViewModel: DestinationViewModel
    private val destinationAdapter: DestinationAdapter by lazy { DestinationAdapter() }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        binding.tvDestinationFrom.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bottomSheetHomeSearchDestinationFragment)
        }
        binding.tvDestinationTo.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bottomSheetHomeSearchDestinationFragment)
        }

        binding.tvDepartureDate.setOnClickListener {
            showDateRangePickerDialog()
        }
        binding.tvReturnDate.setOnClickListener {
            showDateRangePickerDialog()
        }

        destinationViewModel = ViewModelProvider(this).get(DestinationViewModel::class.java)
        setupRecyclerView()
        observeDestinations()


        binding.btnBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
//                R.id.riwayat -> {
//                    findNavController().navigate(R.id.action_homeFragment_to_bookingHistoryNonLoginFragment)
//                    true
//                }
                R.id.notifikasi -> {
                    findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
                    true
                }
                R.id.akun -> {
                    findNavController().navigate(R.id.action_homeFragment_to_accountFragment)
                    true
                }
                else -> false
            }
        }


    }

    private fun setupRecyclerView() {
        binding.rvDestination.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvDestination.adapter = destinationAdapter
    }

    private fun observeDestinations() {
        destinationViewModel.destinations.observe(viewLifecycleOwner) { destinations ->
            destinationAdapter.setData(destinations)
        }
    }

    private fun showDateRangePickerDialog() {
        var startDate = binding.tvDepartureDate
        var endDate = binding.tvReturnDate
        val calendar = Calendar.getInstance()
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        val picker = builder.build()

        picker.addOnPositiveButtonClickListener { selection ->
            val startDateMillis = selection.first ?: 0L
            val endDateMillis = selection.second ?: 0L

            if (startDateMillis > 0 && endDateMillis > 0) {
                calendar.timeInMillis = startDateMillis
//                startDate.text = formatDate(calendar.time)
//                homeViewModel.selectedStartDate = calendar.time
                homeViewModel.postSelectedStartDate(calendar.time)

                calendar.timeInMillis = endDateMillis
//                endDate.text = formatDate(calendar.time)
//                homeViewModel.selectedEndDate = calendar.time
                homeViewModel.postSelectedEndDate(calendar.time)

                updateSelectedDatesInView()
            }
        }

        picker.show(fragmentManager!!, picker.toString())
    }

    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun updateSelectedDatesInView() {
        var startDate = binding.tvDepartureDate
        var endDate = binding.tvReturnDate
//        val startDateText = homeViewModel.selectedStartDate?.let { formatDate(it) } ?: "-"
//        val endDateText = homeViewModel.selectedEndDate?.let { formatDate(it) } ?: "-"

        homeViewModel.selectedStartDate.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            val startDateText = formatDate(it)
            startDate.text = startDateText
        })
        homeViewModel.selectedEndDate.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            val endDateText = formatDate(it)
            endDate.text = endDateText
        })

    }

}