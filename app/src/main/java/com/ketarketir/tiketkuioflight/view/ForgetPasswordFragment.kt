package com.ketarketir.tiketkuioflight.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentForgetPasswordBinding
import com.ketarketir.tiketkuioflight.viewmodel.ForgetPasswordViewModel

class ForgetPasswordFragment : Fragment() {
    private var _binding: FragmentForgetPasswordBinding? = null
    private val binding get() = _binding!!

    private val forgotPasswordViewModel: ForgetPasswordViewModel by viewModels {
        ViewModelProvider.NewInstanceFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendOtpButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            forgotPasswordViewModel.sendOTP(email)
        }

        binding.verifyOtpButton.setOnClickListener {
            val otp = binding.otpEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            forgotPasswordViewModel.verifyOTP(email, otp)
        }

        binding.savePasswordButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val otp = binding.otpEditText.text.toString()
            val newPassword = binding.newPasswordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()
            forgotPasswordViewModel.resetPassword(email, otp, newPassword, confirmPassword)
        }

        forgotPasswordViewModel.otpSent.observe(viewLifecycleOwner, { otpSent ->
            if (otpSent) {
                showNextStep()
                showToast("OTP sent successfully")
            } else {
                showError("Failed to send OTP")
            }
        })

        forgotPasswordViewModel.otpVerified.observe(viewLifecycleOwner, { otpVerified ->
            if (otpVerified) {
                enableNextStep()
                showToast("OTP verification successful")
            } else {
                showError("OTP verification failed")
            }
        })

        forgotPasswordViewModel.passwordSaved.observe(viewLifecycleOwner, { passwordSaved ->
            if (passwordSaved) {
                showSuccess("New password saved successfully")
//                navigateToLoginScreen()
            } else {
                showError("Failed to save new password")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showNextStep() {
        // Perform necessary actions to show the next step
    }

    private fun enableNextStep() {
        // Perform necessary actions to enable the next step
    }

    private fun showError(message: String) {
        // Display an error message or perform error handling
    }

    private fun showSuccess(message: String) {
        // Display a success message or perform necessary actions for success
    }

//    private fun navigateToLoginScreen() {
//        findNavController().navigate(R.id.action_forgetPasswordFragment_to_loginFragment2)
//    }
}
