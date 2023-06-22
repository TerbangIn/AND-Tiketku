package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBottomSheetClassSeatBinding
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetClassSeatViewModel

class BottomSheetClassSeatFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: BottomSheetClassSeatViewModel
    private lateinit var binding: FragmentBottomSheetClassSeatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetClassSeatBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_bottom_sheet_class_seat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomSheetClassSeatViewModel::class.java)

        binding.cvBusiness.setOnClickListener {
            selectSeat("Business")
        }
        binding.cvFirstClass.setOnClickListener {
            selectSeat("First Class")
        }
        binding.cvEconomy.setOnClickListener {
            selectSeat("Economy")
        }
        binding.cvPremiumEconomy.setOnClickListener {
            selectSeat("Premium Economy")
        }



    }
    private fun selectSeat(seatClass: String) {
        Toast.makeText(context, "Selected seat class: $seatClass", Toast.LENGTH_SHORT).show()
        dismiss()
    }



}