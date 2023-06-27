package com.ketarketir.tiketkuioflight.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.databinding.FragmentProfileBinding
import com.ketarketir.tiketkuioflight.model.user.DataResponseUserUpdate
import com.ketarketir.tiketkuioflight.model.user.DataUserUpdate
import com.ketarketir.tiketkuioflight.networking.ApiClient


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userManager: UserManager
    private val apiService = ApiClient.instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        userManager = UserManager.getInstance(requireContext())

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
            val isSaved = addUser.commit()

            if (isSaved) {
                Toast.makeText(context, "Update Berhasil", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Gagal memperbarui data", Toast.LENGTH_SHORT).show()
            }
        }


//        binding.btnUpdate.setOnClickListener {
//            val getNamaLengkap = binding.tieFullName.text.toString()
//            val getEmail = binding.tieEmail.text.toString()
//            val getPhoneNumber = binding.tiePhoneNumber.text.toString()
//
//            viewLifecycleOwner.lifecycleScope.launch {
//                val bearerToken = userManager.getToken()
//                val userId = userManager.getUserId()
//
//                val request = DataUserUpdate(getNamaLengkap, getEmail, getPhoneNumber)
//
//                apiService.updateUser(bearerToken, userId, request).enqueue(object :
//                    Callback<DataResponseUserUpdate> {
//                    override fun onResponse(
//                        call: Call<DataResponseUserUpdate>,
//                        response: Response<DataResponseUserUpdate>
//                    ) {
//                        if (response.isSuccessful) {
//                            val dataResponse = response.body()
//                            if (dataResponse != null) {
//                                val successMessage = dataResponse.message
//                                Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show()
//                            }
//                        } else {
//                            Toast.makeText(context, "Gagal memperbarui data", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<DataResponseUserUpdate>, t: Throwable) {
//                        Toast.makeText(context, "Gagal komunikasi dengan server", Toast.LENGTH_SHORT).show()
//                    }
//                })
//            }
//        }


        binding.btnLogout.setOnClickListener {
            val addUser = sharedPreferences.edit()
            addUser.remove("name")
            addUser.remove("email")
            addUser.remove("phone_number")
            addUser.apply()

            Toast.makeText(requireContext(), "Logout Berhasil", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment2)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
