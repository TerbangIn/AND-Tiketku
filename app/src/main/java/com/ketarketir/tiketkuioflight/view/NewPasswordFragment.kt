package com.ketarketir.tiketkuioflight.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentNewPasswordBinding
import com.ketarketir.tiketkuioflight.viewmodel.NewPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPasswordFragment : Fragment() {

    private lateinit var binding: FragmentNewPasswordBinding
    private lateinit var newPasswordViewModel: NewPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newPasswordViewModel = ViewModelProvider(this).get(NewPasswordViewModel::class.java)

        binding.btnSimpan.setOnClickListener {
            val email = arguments?.getString("email")
            val otp = arguments?.getString("otp")
            val newPassword = binding.tieEnterNerPassword.text.toString()
            val confirmPassword = binding.tieRepeatNewPassword.text.toString()

            if (newPassword.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (newPassword == confirmPassword) {
                    newPasswordViewModel.resetPassword(email!!, otp!!, newPassword, confirmPassword)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Password and confirm password do not match",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        newPasswordViewModel.statusResetPassword.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Toast.makeText(requireContext(), "Password reset successful", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_newPasswordFragment_to_loginFragment2)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Failed to reset password. Please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
