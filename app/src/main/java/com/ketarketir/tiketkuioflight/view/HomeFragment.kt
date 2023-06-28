package com.ketarketir.tiketkuioflight.view


import DestinationViewModel
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentHomeBinding
import com.ketarketir.tiketkuioflight.model.destination.ListDataDestination
import com.ketarketir.tiketkuioflight.view.adapter.DestinationAdapter
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetClassSeatViewModel
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var destinationViewModel: DestinationViewModel
    private lateinit var destinationAdapter: DestinationAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bottomSheetClassSeatViewModel: BottomSheetClassSeatViewModel


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
        bottomSheetClassSeatViewModel = ViewModelProvider(this).get(BottomSheetClassSeatViewModel::class.java)
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
        destinationAdapter = DestinationAdapter { destination ->
            navigateToDetailDestination(destination)
        }
        setupRecyclerView()
        observeDestinations()
        showChooseSeatClass()

        binding.txtSeatClass.setOnClickListener {
            BottomSheetClassSeatFragment().show(requireActivity().supportFragmentManager, "BottomSheetClassSeatFragment")
        }
        binding.tvPassenger.setOnClickListener {
            BottomSheetSetPassengerFragment().show(requireActivity().supportFragmentManager, "BottomSheetSetPassengerFragment")
        }

    }



    private fun setupRecyclerView() {
        binding.rvDestination.layoutManager = GridLayoutManager(requireContext(), 1, LinearLayoutManager.HORIZONTAL, false)
        binding.rvDestination.adapter = destinationAdapter
    }


    private fun observeDestinations() {
        destinationViewModel.destinations.observe(viewLifecycleOwner) { destinations ->
            destinationAdapter.setData(destinations)
        }
    }

    private fun navigateToDetailDestination(destination: ListDataDestination) {
        val bundle = bundleOf("destination" to destination)
        view?.findNavController()?.navigate(R.id.detailDestinationFragment, bundle)
    }

    private fun showDateRangePickerDialog() {
        if (binding.smSwitch.isChecked) {

            val builder = MaterialDatePicker.Builder.dateRangePicker()
            val picker = builder.build()

            picker.addOnPositiveButtonClickListener { selection ->
                val startDateMillis = selection.first ?: 0L
                val endDateMillis = selection.second ?: 0L

                if (startDateMillis > 0 && endDateMillis > 0) {
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = startDateMillis
                    val startDate = calendar.time

                    calendar.timeInMillis = endDateMillis
                    val endDate = calendar.time

                    homeViewModel.postSelectedStartDate(startDate)
                    homeViewModel.postSelectedEndDate(endDate)

                    updateSelectedDatesInView()
                }
            }

            picker.show(fragmentManager!!, picker.toString())
        } else {

            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()

            picker.addOnPositiveButtonClickListener { selection ->
                val dateMillis = selection ?: 0L

                if (dateMillis > 0) {
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = dateMillis
                    val startDate = calendar.time

                    homeViewModel.postSelectedStartDate(startDate)
                    homeViewModel.postSelectedEndDate(null)

                    updateSelectedDatesInView()
                }
            }

            picker.show(fragmentManager!!, picker.toString())
        }
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
            if (it!=null){
                val endDateText = formatDate(it)
                endDate.text = endDateText
            } else {
                endDate.text = "Return Date"
            }

        })

    }

    fun showChooseSeatClass(){
        val seat = arguments?.getString("seat")
        binding.tvSeatClass.setText(seat)
    }




}