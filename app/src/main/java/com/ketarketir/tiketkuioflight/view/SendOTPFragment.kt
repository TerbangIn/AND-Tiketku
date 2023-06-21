package com.ketarketir.tiketkuioflight.view

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
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
    private var countDownTimer: CountDownTimer? = null
    private var isTimeRunning = false
    private val countDownTime = 60000L
    private val countDownInterval = 1000L
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

        val email = arguments?.getString("email")
        binding.tvNumber.text = email.toString()

        startCountDownTimer()
        binding.tvRequestVerifyEmail.setOnClickListener {
            startCountDownTimer()
            binding.tvRequestVerifyEmail.visibility = View.GONE
        }
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

    private fun startCountDownTimer(){
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(countDownTime, countDownInterval) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                binding.tvResendOtp.text = "Resensd OTP in" + "  " + seconds.toString() + "  " + "Seconds"
            }

            override fun onFinish() {
                binding.tvResendOtp.text = " "
                binding.tvRequestVerifyEmail.visibility = View.VISIBLE
            }
        }
        countDownTimer?.start()
        isTimeRunning = true
    }

    override fun onPause() {
        super.onPause()
        countDownTimer?.cancel()
        isTimeRunning = false
    }

    override fun onResume() {
        super.onResume()
        if (!isTimeRunning){
            startCountDownTimer()
        }
    }
}