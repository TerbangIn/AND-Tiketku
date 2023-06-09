package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ketarketir.tiketkuioflight.R

class RiwayatPesananNonLoginFragment : Fragment() {

    companion object {
        fun newInstance() = RiwayatPesananNonLoginFragment()
    }

    private lateinit var viewModel: RiwayatPesananNonLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_riwayat_pesanan_non_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RiwayatPesananNonLoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}