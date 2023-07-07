package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentAccountSettingBinding
import com.ketarketir.tiketkuioflight.databinding.FragmentBiodataBookingBinding
import com.ketarketir.tiketkuioflight.viewmodel.BiodataBookingViewModel

class BiodataBookingFragment : Fragment() {

    private var _binding: FragmentBiodataBookingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BiodataBookingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBiodataBookingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)


        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_biodataBookingFragment_to_resultSearchFragment)
        }

        inputBooking()


    }

    private fun inputBooking(){
        val fullname = binding.tieName.text.toString()
        val familyName = binding.tieFamily.text.toString()
        val phoneNumber = binding.tieNumberPhone.text.toString()
        val email = binding.tieNumberPhone.text.toString()

        binding.msFamily.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.tieFamily.visibility = View.VISIBLE
            } else {
                binding.tieFamily.visibility = View.GONE
            }
        }

        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_biodataBookingFragment_to_biodataPassengerFragment)

        }
    }

}