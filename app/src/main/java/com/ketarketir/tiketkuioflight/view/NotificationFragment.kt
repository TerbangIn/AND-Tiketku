package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketarketir.tiketkuioflight.databinding.FragmentNotificationBinding
import com.ketarketir.tiketkuioflight.viewmodel.NotificationViewModel
import com.ketarketir.tiketkuioflight.model.notifications.Data
import com.ketarketir.tiketkuioflight.view.adapter.NotificationAdapter
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel

class NotificationFragment : Fragment() {

    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var notificationViewModel: NotificationViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupViewModel()

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.token.observe(viewLifecycleOwner, Observer { token ->
            if (token != null) {
                val bearerToken = "Bearer $token"
                val id = userViewModel.getUserId() // Menggunakan fungsi getUserId() dari UserViewModel
                notificationViewModel.loadNotifications(bearerToken, id)
            } else {
                handleTokenError()
            }
        })
    }

    private fun handleTokenError() {
        Toast.makeText(requireContext(), "Failed to obtain token", Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        notificationAdapter = NotificationAdapter(emptyList())
        binding.rvNotification.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = notificationAdapter
        }
    }

    private fun setupViewModel() {
        notificationViewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
        notificationViewModel.notifications.observe(viewLifecycleOwner, Observer { notifications ->
            updateNotifications(notifications)
        })
    }

    private fun updateNotifications(notifications: List<Data>) {
        notificationAdapter.notifications = notifications
        notificationAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
