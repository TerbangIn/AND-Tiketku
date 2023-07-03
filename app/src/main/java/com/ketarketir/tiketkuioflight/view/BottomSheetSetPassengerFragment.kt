package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBottomSheetSetPassengerBinding
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetSetPassengerViewModel
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetSetPassengerFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetSetPassengerBinding
    private lateinit var viewModel: BottomSheetSetPassengerViewModel
    private val homeViewModel: HomeViewModel by activityViewModels()
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

        binding.btnPlusAdult.setOnClickListener {
            homeViewModel.incrementPassengerCount()
            homeViewModel.passengerCount.observe(viewLifecycleOwner, Observer {
                binding.etAdult.text = it.toString()
                homeViewModel.updateTotalPassenger()
            })
        }
        binding.btnMinusAdult.setOnClickListener {
            homeViewModel.decrementPassengerCount()
            homeViewModel.passengerCount.observe(viewLifecycleOwner, Observer {
                binding.etAdult.text = it.toString()
                homeViewModel.updateTotalPassenger()
            })
        }
        binding.btnPlusChild.setOnClickListener {
            homeViewModel.incrementPassengerCountChild()
            homeViewModel.passengerCountChild.observe(viewLifecycleOwner, Observer {
                binding.etChild.text = it.toString()
                homeViewModel.updateTotalPassenger()
            })
        }
        binding.btnMinusChild.setOnClickListener {
            homeViewModel.decrementPassengerCountChild()
            homeViewModel.passengerCountChild.observe(viewLifecycleOwner, Observer {
                binding.etChild.text = it.toString()
                homeViewModel.updateTotalPassenger()
            })
        }
        binding.btnPlusBaby.setOnClickListener {
            homeViewModel.incrementPassengerCountBaby()
            homeViewModel.passengerCountBaby.observe(viewLifecycleOwner, Observer {
                binding.etBaby.text = it.toString()
                homeViewModel.updateTotalPassenger()
            })
        }
        binding.btnMinusBaby.setOnClickListener {
            homeViewModel.decrementPassengerCountBaby()
            homeViewModel.passengerCountBaby.observe(viewLifecycleOwner, Observer {
                binding.etBaby.text = it.toString()
                homeViewModel.updateTotalPassenger()
            })
        }


        binding.btnChoose.setOnClickListener {
            homeViewModel.updateTotalPassenger()
            dismiss()
        }

    }

}