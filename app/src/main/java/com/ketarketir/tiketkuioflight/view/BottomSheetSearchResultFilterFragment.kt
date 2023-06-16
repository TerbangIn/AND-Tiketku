package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetSearchResultFilterViewModel

class BottomSheetSearchResultFilterFragment : Fragment() {

    companion object {
        fun newInstance() = BottomSheetSearchResultFilterFragment()
    }

    private lateinit var viewModel: BottomSheetSearchResultFilterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_bottom_sheet_search_result_filter,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomSheetSearchResultFilterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}