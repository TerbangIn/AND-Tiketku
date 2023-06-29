package com.ketarketir.tiketkuioflight.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentAccountSettingBinding
import com.ketarketir.tiketkuioflight.viewmodel.AccountSettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountSettingFragment : Fragment() {
    private var _binding: FragmentAccountSettingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AccountSettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_accountSettingFragment_to_accountFragment)
        }

        binding.tvChooseLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        binding.tvComingSoon.setOnClickListener {
            Toast.makeText(requireContext(), "Coming Soon!", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}