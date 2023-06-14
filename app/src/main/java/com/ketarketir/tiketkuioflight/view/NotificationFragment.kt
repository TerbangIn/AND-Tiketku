package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketarketir.tiketkuioflight.databinding.FragmentNotificationBinding
import com.ketarketir.tiketkuioflight.viewmodel.NotificationViewModel
import com.ketarketir.tiketkuioflight.model.notifications.Data
import com.ketarketir.tiketkuioflight.view.adapter.NotificationAdapter

class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var notificationViewModel: NotificationViewModel

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
        // Observe the notifications LiveData
        notificationViewModel.notifications.observe(viewLifecycleOwner, Observer { notifications ->
            updateNotifications(notifications)
        })

        val bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJzYXlhYWRtaW5AZ21haWwuY29tIiwicm9sZSI6ImFkbWluIiwiaWF0IjoxNjg2NzM1NTM0fQ.zBE5PV_XM_SBuiqgZMreK7Oj03M3EffD-S8Y7fn_voA"
        val id = 1
        notificationViewModel.loadNotifications(bearerToken, id)
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
