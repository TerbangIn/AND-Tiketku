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
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.databinding.FragmentNotificationBinding
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.viewmodel.NotificationViewModel
import com.ketarketir.tiketkuioflight.model.notifications.Data
import com.ketarketir.tiketkuioflight.view.adapter.NotificationAdapter
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotificationFragment : Fragment() {

    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var userManager: UserManager
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
        (activity as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
        notificationViewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
        userManager = UserManager.getInstance(requireContext())
        GlobalScope.launch {
            val token = userManager.getToken()
            val userid = userManager.getUserId()
            notificationViewModel.getNotifications(token)

        }
//        setupRecyclerView()
//        setupViewModels()

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//        userViewModel.token.observe(viewLifecycleOwner, { token ->
//            if (token != null) {
//                val bearerToken = token
//                val id = userViewModel.getUserId()
//                notificationViewModel.getNotifications(bearerToken, id)
//            } else {
//                handleTokenError()
//            }
//        })
        notificationViewModel.notifications.observe(viewLifecycleOwner, Observer {
            notificationAdapter = NotificationAdapter(it)
            binding.rvNotification.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = notificationAdapter
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

    private fun setupViewModels() {
        notificationViewModel.notifications.observe(viewLifecycleOwner, { notifications ->
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