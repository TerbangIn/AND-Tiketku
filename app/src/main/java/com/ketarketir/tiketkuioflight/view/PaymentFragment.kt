package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentPaymentBinding
import com.ketarketir.tiketkuioflight.databinding.FragmentResultSearchBinding
import com.ketarketir.tiketkuioflight.viewmodel.PaymentViewModel

class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PaymentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_paymentFragment_to_checkoutFragment)
        }

        binding.btnOnlinePay.setOnClickListener {
            findNavController().navigate(R.id.action_paymentFragment_to_bottomSheetPaymentSuccessFragment)
        }
    }

}