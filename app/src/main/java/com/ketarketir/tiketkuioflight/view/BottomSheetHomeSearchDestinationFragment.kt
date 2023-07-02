package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBottomSheetHomeSearchDestinationBinding
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.view.adapter.AirportAdapter
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetHomeSearchDestinationViewModel
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BottomSheetHomeSearchDestinationFragment : Fragment() {

    private lateinit var binding: FragmentBottomSheetHomeSearchDestinationBinding
    private lateinit var viewModel: BottomSheetHomeSearchDestinationViewModel
    private lateinit var userManager: UserManager
    private val homeViewModel: HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetHomeSearchDestinationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(BottomSheetHomeSearchDestinationViewModel::class.java)
        userManager = UserManager.getInstance(requireContext())

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
                        bundle.putString("city", city)
                        bundle.putString("code", code)
                        homeViewModel.postSelectedAirportTo(city)
                        findNavController().navigate(R.id.action_bottomSheetHomeSearchDestinationFragment_to_homeFragment, bundle)
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