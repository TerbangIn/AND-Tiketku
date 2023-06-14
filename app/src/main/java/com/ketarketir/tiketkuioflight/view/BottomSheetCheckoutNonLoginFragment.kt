package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetCheckoutNonLoginViewModel

class BottomSheetCheckoutNonLoginFragment : Fragment() {

    companion object {
        fun newInstance() = BottomSheetCheckoutNonLoginFragment()
    }

    private lateinit var viewModel: BottomSheetCheckoutNonLoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_checkout_non_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomSheetCheckoutNonLoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}