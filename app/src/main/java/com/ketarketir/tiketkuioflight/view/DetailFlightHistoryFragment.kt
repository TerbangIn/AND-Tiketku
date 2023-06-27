package com.ketarketir.tiketkuioflight.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.databinding.FragmentDetailFlightHistoryBinding
import com.ketarketir.tiketkuioflight.viewmodel.DetailFlightHistoryViewModel

class DetailFlightHistoryFragment : Fragment() {

    private var _binding: FragmentDetailFlightHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailFlightHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFlightHistoryBinding.inflate(inflater, container, false)
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