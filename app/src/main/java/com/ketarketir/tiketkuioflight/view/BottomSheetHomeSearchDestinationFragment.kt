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
import androidx.lifecycle.Observer
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBottomSheetHomeSearchDestinationBinding
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetHomeSearchDestinationViewModel
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BottomSheetHomeSearchDestinationFragment : Fragment() {

    private lateinit var binding: FragmentBottomSheetHomeSearchDestinationBinding
    private lateinit var viewModel: BottomSheetHomeSearchDestinationViewModel
    private lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetHomeSearchDestinationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomSheetHomeSearchDestinationViewModel::class.java)
        userManager = UserManager.getInstance(requireContext())

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getToken()
                viewModel.token.observe(viewLifecycleOwner, Observer {
                    val token = it
                    viewModel.callApiAirport(token)
                    viewModel.airport.observe(viewLifecycleOwner, Observer {
                        val data = it.toString()
                        if (it!= null){
                            if (data.contains(query.orEmpty(), ignoreCase = true)){
                                val adapter : ArrayAdapter<Char> = ArrayAdapter(
                                    requireContext(),
                                    android.R.layout.simple_list_item_1,
                                    data.toList()
                                )
                                adapter.filter.filter(query)
                            }
                        } else {
                            Toast.makeText(context, "Airport Tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                    })
                })
                return false
            }

            override fun onQueryTextChange(newQuery: String?): Boolean {
                viewModel.getToken()
                viewModel.token.observe(viewLifecycleOwner, Observer {
                    val token = it
                    viewModel.callApiAirport(token)
                    viewModel.airport.observe(viewLifecycleOwner, Observer {
                        val data = it
                        val namaAirport = data.name
                        if (it!= null){
                            if (namaAirport.contains(newQuery.orEmpty(), ignoreCase = true)){
                                val adapter : ArrayAdapter<Char> = ArrayAdapter(
                                    requireContext(),
                                    android.R.layout.simple_list_item_1,
//                                    data.toList()
                                )
                                adapter.filter.filter(newQuery)
                            }
                        } else {
                            Toast.makeText(context, "Airport Tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                    })
                })

                return false
            }

        })
    }

}