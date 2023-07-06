package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBiodataBookingBinding
import com.ketarketir.tiketkuioflight.databinding.FragmentBiodataPassengerBinding
import com.ketarketir.tiketkuioflight.viewmodel.BiodataPassengerViewModel
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel

class BiodataPassengerFragment : Fragment() {

    private var _binding: FragmentBiodataPassengerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BiodataPassengerViewModel
    private val homeViewModel:HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBiodataPassengerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_biodataPassengerFragment_to_biodataBookingFragment)
        }

        binding.btnChooseNextSeat.setOnClickListener {
            findNavController().navigate(R.id.action_biodataPassengerFragment_to_checkoutFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}