package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBottomSheetClassSeatBinding
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetClassSeatViewModel
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel

class BottomSheetClassSeatFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetClassSeatBinding
    private val homeViewModel: HomeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetClassSeatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        binding.cvBusiness.setOnClickListener {
            saveSeat("Business")
            selectSeat("Business")
            dismiss()
        }
        binding.cvFirstClass.setOnClickListener {
            saveSeat("First Class")
            selectSeat("First Class")
            dismiss()
        }
        binding.cvEconomy.setOnClickListener {
            saveSeat("Economy")
            selectSeat("Economy")
            dismiss()
        }
        binding.cvPremiumEconomy.setOnClickListener {
            saveSeat("Premium Economy")
            selectSeat("Premium Economy")
            dismiss()
        }


    }
    private fun selectSeat(seatClass: String) {
        Toast.makeText(context, "Selected seat class: $seatClass", Toast.LENGTH_SHORT).show()

    }
    private fun saveSeat(seatClass: String){
        homeViewModel.setSeatClass(seatClass)
    }



}