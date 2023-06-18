package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBottomSheetHomeSearchDestinationBinding
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetHomeSearchDestinationViewModel
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel

class BottomSheetHomeSearchDestinationFragment : Fragment() {

    private lateinit var binding: FragmentBottomSheetHomeSearchDestinationBinding
    private lateinit var viewModel: BottomSheetHomeSearchDestinationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetHomeSearchDestinationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomSheetHomeSearchDestinationViewModel::class.java)

    }

    private fun searchAirport(query:String){

    }
}