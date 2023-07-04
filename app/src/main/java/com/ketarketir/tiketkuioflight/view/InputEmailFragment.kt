package com.ketarketir.tiketkuioflight.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentInputEmailBinding
import com.ketarketir.tiketkuioflight.viewmodel.SendOTPViewModel
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel

class InputEmailFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    private val sendOTPViewModel: SendOTPViewModel by activityViewModels()

    private var _binding: FragmentInputEmailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentInputEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        binding.btnNext.setOnClickListener {
            navigateToSendOTPFragment()
        }

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_inputEmailFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToSendOTPFragment() {
        val navController = findNavController()
        val email = getEmailFromInput()
        userViewModel.setEmail(email)

        sendOTPViewModel.statusGenerate.observe(viewLifecycleOwner, { response ->
            if (response != null) {
                navController.navigate(R.id.action_inputEmailFragment_to_sendOTPResetPassword, Bundle().apply {
                    putString("email", email)
                })
            } else {
                // Tampilkan pesan error jika pemanggilan API gagal
            }
        })

        sendOTPViewModel.callApiPostGenerateOtp(email)
    }

    private fun getEmailFromInput(): String {
        return binding.tieMasukanEmail.text.toString().trim()
    }
}
