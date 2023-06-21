package com.ketarketir.tiketkuioflight.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        val getName = sharedPreferences.getString("name", "")
        binding.tieFullName.setText(getName)

        val getEmail = sharedPreferences.getString("email", "")
        binding.tieEmail.setText(getEmail)

        val getPhoneNumber = sharedPreferences.getString("phone_number", "")
        binding.tiePhoneNumber.setText(getPhoneNumber)

        binding.btnUpdate.setOnClickListener {
            val getNamaLengkap = binding.tieFullName.text.toString()
            val getEmail = binding.tieEmail.text.toString()
            val getPhoneNumber = binding.tiePhoneNumber.text.toString()
            val addUser = sharedPreferences.edit()
            addUser.putString("name", getNamaLengkap)
            addUser.putString("email", getEmail)
            addUser.putString("phone_number", getPhoneNumber)
            addUser.apply()

            Toast.makeText(context, "Update Berhasil", Toast.LENGTH_SHORT).show()

        }

        binding.btnLogout.setOnClickListener {

            val sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
            val addUser = sharedPreferences.edit()
            addUser.remove("name")
            addUser.remove("email")
            addUser.remove("phone_number")
            addUser.apply()

            Toast.makeText(requireContext(), "Logout Berhasil", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment2)
        }


    }



}

