package com.ketarketir.tiketkuioflight.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentResultSearchBinding
import com.ketarketir.tiketkuioflight.databinding.FragmentSendOTPBinding
import com.ketarketir.tiketkuioflight.view.adapter.AirportAdapter
import com.ketarketir.tiketkuioflight.view.adapter.FlightAdapter
import com.ketarketir.tiketkuioflight.viewmodel.ResultSearchViewModel

class ResultSearchFragment : Fragment() {

    private lateinit var binding: FragmentResultSearchBinding

    private val resultSearchViewModel: ResultSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        showFlight()
    }

    fun showFlight(){
        resultSearchViewModel.getToken()
        resultSearchViewModel.token.observe(viewLifecycleOwner, Observer {
            val token = it
            resultSearchViewModel.callApiGetListFlight(token)
            resultSearchViewModel.listFligh.observe(viewLifecycleOwner, Observer {
                if (it!= null){
                    val adapter = FlightAdapter(it)
                    binding.rvFlight.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.rvFlight.adapter = adapter
                } else{
                    Toast.makeText(context, "null", Toast.LENGTH_SHORT).show()
                }
            })
        })
    }
}