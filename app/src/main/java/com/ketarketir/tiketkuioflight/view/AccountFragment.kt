package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentAccountBinding
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private var _binding:FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    private lateinit var userManager: UserManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.VISIBLE)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userManager = UserManager.getInstance(requireContext())

        binding.clChangeProfile.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_profileFragment)
            Toast.makeText(requireContext(), "Account", Toast.LENGTH_SHORT).show()
        }

        binding.clSettingsAccount.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_accountSettingFragment)
        }


        binding.clLogout.setOnClickListener {
            GlobalScope.async {
                userManager.clearData()
            }
            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
            Toast.makeText(requireContext(), "Logout Berhasil, Anda telah Logout", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}