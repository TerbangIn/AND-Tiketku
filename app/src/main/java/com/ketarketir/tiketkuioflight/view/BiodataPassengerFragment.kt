package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentBiodataBookingBinding
import com.ketarketir.tiketkuioflight.databinding.FragmentBiodataPassengerBinding
import com.ketarketir.tiketkuioflight.viewmodel.BiodataPassengerViewModel
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

class BiodataPassengerFragment : Fragment() {

    private var _binding: FragmentBiodataPassengerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BiodataPassengerViewModel
    private val homeViewModel:HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBiodataPassengerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_biodataPassengerFragment_to_biodataBookingFragment)
        }



    }

    private fun biodataPassenger(){
        val titleOptions = arrayOf("Mr.", "Mrs.")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, titleOptions)
        binding.autoCompleteTextView.setAdapter(adapter)
        binding.autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedTitle = titleOptions[position]
            Toast.makeText(requireContext(), "Selected title: $selectedTitle", Toast.LENGTH_SHORT).show()
        }
        val name = binding.tieName.text.toString()
        binding.msFamily.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.tieFamily.visibility = View.VISIBLE
            } else {
                binding.tieFamily.visibility = View.GONE
            }
        }
        val family = binding.tieFamily.text.toString()
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()

        picker.addOnPositiveButtonClickListener { selection ->
            val dateMillis = selection ?: 0L

            if (dateMillis > 0) {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = dateMillis
                val birthDate = calendar.time
                val formatted = formatDate(birthDate)
//                homeViewModel.postSelectedStartDate(startDate)
//                homeViewModel.postSelectedEndDate(null)

                binding.tieBirthDate.setText(formatted)
            }
        }

        picker.show(fragmentManager!!, picker.toString())

        val citizenship = binding.tieCitizenship.text.toString()
        val passport = binding.tieCardPassport.text.toString()
        val country = binding.tiePublication.text.toString()

        if (passport.isEmpty()){
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
        } else{
            binding.btnChooseNextSeat.setOnClickListener {
                findNavController().navigate(R.id.action_biodataPassengerFragment_to_checkoutFragment)
            }
        }
    }

    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}