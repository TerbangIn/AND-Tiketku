package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentSendOTPBinding
import com.ketarketir.tiketkuioflight.viewmodel.SendOTPViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendOTPFragment : Fragment() {
    private lateinit var binding:FragmentSendOTPBinding
    private lateinit var sendOTPViewModel: SendOTPViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSendOTPBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendOTPViewModel = ViewModelProvider(this).get(SendOTPViewModel::class.java)
    }

    private fun verifyOtp(){
        val digit1 = binding.etKode1.text.toString()
        val digit2 = binding.etKode2.text.toString()
        val digit3 = binding.etKode3.text.toString()
        val digit4 = binding.etKode4.text.toString()
        val digit5 = binding.etKode5.text.toString()
        val digit6 = binding.etKode6.text.toString()

        val inputOtp = digit1 + digit2 + digit3 + digit4 + digit5 + digit6
        val email = arguments?.getString("email")

        sendOTPViewModel.callApiVerifyUser(email.toString(), inputOtp)
        sendOTPViewModel.statusVerify.observe(viewLifecycleOwner, Observer {
            if (it!= null){
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()
            }
        })


    }

}