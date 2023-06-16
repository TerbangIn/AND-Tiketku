package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.viewmodel.BottomSheetDialogUpdateAccountViewModel

class BottomSheetDialogUpdateAccountFragment : Fragment() {

    companion object {
        fun newInstance() = BottomSheetDialogUpdateAccountFragment()
    }

    private lateinit var viewModel: BottomSheetDialogUpdateAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_bottom_sheet_dialog_update_account,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomSheetDialogUpdateAccountViewModel::class.java)
        // TODO: Use the ViewModel
    }

}