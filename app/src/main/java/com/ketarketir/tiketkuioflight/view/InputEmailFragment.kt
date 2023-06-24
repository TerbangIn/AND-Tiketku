package com.ketarketir.tiketkuioflight.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentInputEmailBinding
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel

class InputEmailFragment : Fragment() {
    private var _binding: FragmentInputEmailBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        binding.btnNext.setOnClickListener {
            val email = binding.tieMasukanEmail.text.toString()

            userViewModel.resetPassword(email, "", "","").observe(viewLifecycleOwner, Observer { response ->
                if (response != null) {
                    if (response.isSuccessful) {
                        navigateToOtpFragment()
                    } else {

                    }
                } else {

                }
            })
        }
    }

    private fun navigateToOtpFragment() {
        findNavController().navigate(R.id.action_inputEmailFragment_to_sendOTPFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
