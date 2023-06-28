package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBottomSheetSetPassengerBinding
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetSetPassengerViewModel

class BottomSheetSetPassengerFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetSetPassengerBinding
    private lateinit var viewModel: BottomSheetSetPassengerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetSetPassengerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomSheetSetPassengerViewModel::class.java)


    }

}