package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.viewmodel.BiodataPassengerViewModel

class BiodataPassengerFragment : Fragment() {

    companion object {
        fun newInstance() = BiodataPassengerFragment()
    }

    private lateinit var viewModel: BiodataPassengerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_biodata_passenger, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BiodataPassengerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}