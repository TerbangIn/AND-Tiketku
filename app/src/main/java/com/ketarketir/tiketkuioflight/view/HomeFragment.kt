package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentHomeBinding
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.riwayat -> {
                    findNavController().navigate(R.id.action_homeFragment_to_bookingHistoryNonLoginFragment)
                    true
                }
                R.id.notifikasi -> {
                    findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
                    true
                }
                R.id.akun -> {
                    findNavController().navigate(R.id.action_homeFragment_to_accountFragment)
                    true
                }
                else -> false
            }
        }
    }


}