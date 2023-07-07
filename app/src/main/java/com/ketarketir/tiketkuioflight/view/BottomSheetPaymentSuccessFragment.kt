package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBottomSheetPaymentSuccessBinding
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetPaymentSuccessViewModel

class BottomSheetPaymentSuccessFragment : Fragment() {
    private lateinit var binding:FragmentBottomSheetPaymentSuccessBinding

    companion object {
        fun newInstance() = BottomSheetPaymentSuccessFragment()
    }

    private lateinit var viewModel: BottomSheetPaymentSuccessViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetPaymentSuccessBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomSheetPaymentSuccessViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icClose.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetPaymentSuccessFragment_to_homeFragment)
        }
    }

}