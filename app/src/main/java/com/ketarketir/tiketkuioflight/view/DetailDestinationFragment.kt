package com.ketarketir.tiketkuioflight.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ketarketir.tiketkuioflight.databinding.FragmentDetailDestinationBinding
import com.ketarketir.tiketkuioflight.model.destination.ListDataDestination

class DetailDestinationFragment : Fragment() {
    private lateinit var binding: FragmentDetailDestinationBinding // Ganti dengan kelas binding yang digunakan Anda

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailDestinationBinding.inflate(inflater, container, false) // Ganti dengan inisialisasi binding yang sesuai
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val destination = requireArguments().getParcelable<ListDataDestination>("destination")
        destination?.let {
            binding.ivPlace.setImageResource(it.imageResId)
            binding.tvDeparture.text = it.departure
            binding.tvArrival.text = it.arrival
            binding.tvPlaneName.text = it.planeName
            binding.tvDate.text = it.date
            binding.tvPriceRange.text = it.priceRange
            binding.tvOverview.text= it.overview
        }
    }
}
