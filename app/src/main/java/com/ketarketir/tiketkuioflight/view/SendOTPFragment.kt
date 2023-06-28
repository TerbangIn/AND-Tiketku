package com.ketarketir.tiketkuioflight.view

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentSendOTPBinding
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.viewmodel.SendOTPViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendOTPFragment : Fragment() {
    private var _binding:FragmentSendOTPBinding? = null
    private val binding get() = _binding!!
    private lateinit var sendOTPViewModel: SendOTPViewModel
    private var countDownTimer: CountDownTimer? = null
    private var isTimeRunning = false
    private val countDownTime = 60000L
    private val countDownInterval = 1000L
    private lateinit var userManager: UserManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendOTPBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        sendOTPViewModel = ViewModelProvider(this).get(SendOTPViewModel::class.java)
        userManager = UserManager.getInstance(requireContext())

        val email = arguments?.getString("email")
        binding.tvNumber.text = email.toString()

        startCountDownTimer()
        binding.tvRequestVerifyEmail.setOnClickListener {
            startCountDownTimer()
            binding.tvRequestVerifyEmail.visibility = View.GONE
        }

        binding.tvRequestVerifyEmail.setOnClickListener {
            generateOtp()
        }

        binding.etKode6.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun afterTextChanged(p0: Editable?) {
                verifyOtp()
            }

        })
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
                Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_sendOTPFragment_to_loginFragment)
            } else{
                Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun generateOtp(){
        val email = arguments?.getString("email")
        sendOTPViewModel.callApiPostGenerateOtp(email.toString())
        sendOTPViewModel.statusGenerate.observe(viewLifecycleOwner, Observer {
            if (it!= null){
                Toast.makeText(context, "Send OTP Berhasil! Silahkan cek email anda !", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(context, "Send OTP Gagal!", Toast.LENGTH_SHORT).show()
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