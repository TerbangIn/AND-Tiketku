package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetHomeSearchDestinationViewModel

class BottomSheetHomeSearchDestinationFragment : Fragment() {

    companion object {
        fun newInstance() = BottomSheetHomeSearchDestinationFragment()
    }

    private lateinit var viewModel: BottomSheetHomeSearchDestinationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_home_search_destination, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomSheetHomeSearchDestinationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}