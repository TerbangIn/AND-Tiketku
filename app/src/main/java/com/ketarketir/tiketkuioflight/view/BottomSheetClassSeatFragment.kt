package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBottomSheetClassSeatBinding
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetClassSeatViewModel

class BottomSheetClassSeatFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetClassSeatBinding
    private lateinit var viewModel: BottomSheetClassSeatViewModel


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
        viewModel = ViewModelProvider(this).get(BottomSheetClassSeatViewModel::class.java)

        binding.cvBusiness.setOnClickListener {
            saveSeat("Business")
            selectSeat("Business")
        }
        binding.cvFirstClass.setOnClickListener {
            saveSeat("First Class")
            selectSeat("First Class")
        }
        binding.cvEconomy.setOnClickListener {
            saveSeat("Economy")
            selectSeat("Economy")
        }
        binding.cvPremiumEconomy.setOnClickListener {
            saveSeat("Premium Economy")
            selectSeat("Premium Economy")
        }


    }
    private fun selectSeat(seatClass: String) {
        Toast.makeText(context, "Selected seat class: $seatClass", Toast.LENGTH_SHORT).show()
    }
    private fun saveSeat(seatClass: String){
        viewModel.setSeatClass(seatClass)
        viewModel.seatClass.observe(viewLifecycleOwner, Observer {
            if (it!= null){
                val seat = it
                val bundle = Bundle()
                bundle.putString("seat", seat)
                findNavController().navigate(R.id.action_bottomSheetClassSeatFragment_to_homeFragment, bundle)
            } else{
                Toast.makeText(context, "Seat class null", Toast.LENGTH_SHORT).show()
            }
        })

    }



}