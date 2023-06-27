package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        _binding = FragmentBiodataBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}