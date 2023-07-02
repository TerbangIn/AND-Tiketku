package com.ketarketir.tiketkuioflight.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBottomSheetHomeSearchDestinationBinding
import com.ketarketir.tiketkuioflight.databinding.FragmentHomeSearchAirportFromBinding
import com.ketarketir.tiketkuioflight.view.adapter.AirportAdapter
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetHomeSearchDestinationViewModel
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeSearchAirportFrom : Fragment() {
    private lateinit var binding: FragmentHomeSearchAirportFromBinding
    private lateinit var viewModel: BottomSheetHomeSearchDestinationViewModel
    private val homeViewModel : HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeSearchAirportFromBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(BottomSheetHomeSearchDestinationViewModel::class.java)
        showAirport()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchAirportByCity(query.toString())
                return true
            }

            override fun onQueryTextChange(newQuery: String?): Boolean {
                searchAirportByCity(newQuery.toString())
                return true
            }
        })
    }

    private fun searchAirportByCity(queryCity:String){
        viewModel.getToken()
        viewModel.token.observe(viewLifecycleOwner, Observer {
            val token = it
            viewModel.callApiSearchAirportbyCity(token, queryCity)
            viewModel.listAirportSearch.observe(viewLifecycleOwner, Observer {
                if (it!= null){
                    val adapter = AirportAdapter(it)
                    binding.rvRecentSearch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.rvRecentSearch.adapter = adapter
                } else{
                    Toast.makeText(context, "kosong", Toast.LENGTH_SHORT).show()
                }
            })
        })
    }

    fun showAirport(){
        viewModel.getToken()
        viewModel.token.observe(viewLifecycleOwner, Observer {
            val token = it
            viewModel.callApiListAirport(token)
            viewModel.listAirport.observe(viewLifecycleOwner, Observer {
                if (it!= null){
                    val adapter = AirportAdapter(it)
                    binding.rvRecentSearch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.rvRecentSearch.adapter = adapter
                    adapter.onClick = {
                        val bundle = Bundle()
                        val city = it.city
                        val code = it.code
                        bundle.putString("city_from", city)
                        bundle.putString("code_from", code)
                        homeViewModel.postSelectedAirportFrom(city)
                        findNavController().navigate(R.id.action_homeSearchAirportFrom_to_homeFragment, bundle)
                    }
                } else{
                    Toast.makeText(context, "null", Toast.LENGTH_SHORT).show()
                }
            })
        })
    }

    private fun performSearch(query: String) {
        viewModel.getToken()
        viewModel.token.observe(viewLifecycleOwner, Observer {
            val token = it
            viewModel.callApiListAirport(token)
            viewModel.listAirport.observe(viewLifecycleOwner, Observer {
                if (it!= null){
                    val adapter = AirportAdapter(it)
                    adapter.filterAirportList(query)
                } else{
                    Toast.makeText(context, "null", Toast.LENGTH_SHORT).show()
                }
            })
        })

    }

}



