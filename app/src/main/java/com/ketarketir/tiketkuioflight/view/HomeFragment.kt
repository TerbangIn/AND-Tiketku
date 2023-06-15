package com.ketarketir.tiketkuioflight.view

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentHomeBinding
import com.ketarketir.tiketkuioflight.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {



//    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDepartureDate.setOnClickListener {
            showDateRangePickerDialog()
        }
        binding.tvReturnDate.setOnClickListener {
            showDateRangePickerDialog()
        }


        binding.btnBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
//                R.id.riwayat -> {
//                    findNavController().navigate(R.id.action_homeFragment_to_bookingHistoryNonLoginFragment)
//                    true
//                }
                R.id.notifikasi -> {
                    findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
                    true
                }
                R.id.akun -> {
                    findNavController().navigate(R.id.action_homeFragment_to_accountFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun showDateRangePickerDialog() {
        var startDate = binding.tvDepartureDate
        var endDate = binding.tvReturnDate
        val calendar = Calendar.getInstance()
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        val picker = builder.build()

        picker.addOnPositiveButtonClickListener { selection ->
            val startDateMillis = selection.first ?: 0L
            val endDateMillis = selection.second ?: 0L

            if (startDateMillis > 0 && endDateMillis > 0) {
                calendar.timeInMillis = startDateMillis
                startDate.text = formatDate(calendar.time)

                calendar.timeInMillis = endDateMillis
                endDate.text = formatDate(calendar.time)
            }
        }

        picker.show(fragmentManager!!, picker.toString())
    }

    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }



//    private fun showDateRangePickerDialog() {
//        var startDate = binding.tvDepartureDate
//        var endDate = binding.tvReturnDate
//        val calendar = Calendar.getInstance()
//        val year = calendar.get(Calendar.YEAR)
//        val month = calendar.get(Calendar.MONTH)
//        val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//        val datePickerDialog = DatePickerDialog(
//            requireContext(),
//            { _, pickedYear, pickedMonth, pickedDay ->
//                val formattedDate = formatDate(pickedDay, pickedMonth, pickedYear)
//                if (startDate.text.isEmpty()) {
//                    startDate.text = formattedDate
//                    calendar.set(pickedYear, pickedMonth, pickedDay)
//                    showDatePickerDialog()
//                } else {
//                    val selectedStartDate = calendar.time
//                    val selectedEndDate = formatDate(pickedDay, pickedMonth, pickedYear)
//
//                    if (selectedStartDate.before(calendar.time)) {
//                        endDate.text = selectedEndDate
//                    } else {
//                        startDate.text = selectedEndDate
//                        endDate.text = formattedDate
//                    }
//                }
//            },
//            year, month, day
//        )
//
//        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
//
//        datePickerDialog.show()
//    }
//
//    private fun showDatePickerDialog() {
//        var startDate = binding.tvDepartureDate
//        var endDate = binding.tvReturnDate
//        val calendar = Calendar.getInstance()
//        val year = calendar.get(Calendar.YEAR)
//        val month = calendar.get(Calendar.MONTH)
//        val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//        val datePickerDialog = DatePickerDialog(
//            requireContext(),
//            { _, pickedYear, pickedMonth, pickedDay ->
//                val formattedDate = formatDate(pickedDay, pickedMonth, pickedYear)
//                endDate.text = formattedDate
//            },
//            year, month, day
//        )
//
//        datePickerDialog.datePicker.minDate = calendar.timeInMillis
//
//        datePickerDialog.show()
//    }
//
//    private fun formatDate(day: Int, month: Int, year: Int): String {
//        val calendar = Calendar.getInstance()
//        calendar.set(year, month, day)
//
//        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//        return dateFormat.format(calendar.time)





//    private fun showDatePickerDialog(){
//        val calendar = Calendar.getInstance()
//        val year = calendar.get(Calendar.YEAR)
//        val month = calendar.get(Calendar.MONTH)
//        val day = calendar.get(Calendar.DAY_OF_MONTH)
//
////        val datePickerDialog = DatePickerDialog(
////            requireContext(),
////            { _, pickedYear, pickedMonth, pickedDay ->
////                val formattedDate = formatDate(pickedDay, pickedMonth, pickedYear)
////                textView.text = formattedDate
////
////            },
////            year, month, day
////        )
////
////        datePickerDialog.show()
//
//        var startDate = binding.tvDepartureDate.text
//        var endDatee = binding.tvReturnDate.text
//        val datePickerDialog = DatePickerDialog(
//            requireContext(),
//            { _, pickedYear, pickedMonth, pickedDay ->
//                if (startDate.isEmpty()) {
//                    // Jika startDate belum dipilih, set startDate
//                    val formattedStartDate = formatDate(pickedDay, pickedMonth, pickedYear)
//                    startDate = formattedStartDate
//                    calendar.set(pickedYear, pickedMonth, pickedDay)
//                } else {
//                    // Jika startDate sudah dipilih, set endDate
//                    val formattedEndDate = formatDate(pickedDay, pickedMonth, pickedYear)
//                    endDatee = formattedEndDate
//                    calendar.set(pickedYear, pickedMonth, pickedDay)
//                }
//            },
//            year, month, day
//        )
//
//        // Batasi rentang tanggal yang dapat dipilih
//        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
//
//        datePickerDialog.show()
//    }
//
//    private fun formatDate(day: Int, month: Int, year: Int): String {
//        val calendar = Calendar.getInstance()
//        calendar.set(year, month, day)
//
//        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//        return dateFormat.format(calendar.time)



}