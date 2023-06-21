package com.ketarketir.tiketkuioflight.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentRegisterBinding
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel
//import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding:FragmentRegisterBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var sharedRegis: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        sharedRegis = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            register()
        }
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment22)
        }

        binding.tiePhoneNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun afterTextChanged(p0: Editable?) {
                val phoneNumber = p0.toString()
                val isPhoneNumberValid = validatePhoneNumber(phoneNumber)
                if (isPhoneNumberValid){
                    binding.tiPhoneNumber.error = null
                    binding.tiPhoneNumber.endIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_check)
                } else{
                    binding.tiPhoneNumber.error = "Phone Number tidak sesuai"
                    binding.tiPhoneNumber.endIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_error)
                }
            }

        })

//        binding.tieName.addTextChangedListener(object  : TextWatcher{
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                binding.tiName.isEndIconVisible = false
//                binding.tiName.endIconMode = com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                binding.tiName.isEndIconVisible = false
//                binding.tiName.endIconMode = com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                binding.tiName.isEndIconVisible = true
//                binding.tiName.endIconMode = com.google.android.material.textfield.TextInputLayout.END_ICON_CUSTOM
//            }
//
//        })

        binding.tiePassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Tidak diperlukan pada tahap ini
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Tidak diperlukan pada tahap ini
            }

            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                val isPasswordValid = validatePassword(password)
                if (isPasswordValid) {
                    binding.tiPassword.error = null
                    binding.tiPassword.endIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_check)
                    binding.tiPassword.requestFocus()
                } else {
                    binding.tiPassword.error = "Password harus mengandung setidaknya 1 huruf besar, 1 angka, dan 1 simbol"
                    binding.tiPassword.endIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_error)
                }
            }
        })

    }


    fun validatePhoneNumber(phoneNumber: String): Boolean {
        val isLengthValid = phoneNumber.length >= 11

        return isLengthValid
    }


    fun validatePassword(password: String) : Boolean {
        val uppercasePattern = Regex("[A-Z]")
        val digitPattern = Regex("[0-9]")
        val symbolPattern = Regex("[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]")

        val isUppercasePresent = uppercasePattern.containsMatchIn(password)
        val isDigitPresent = digitPattern.containsMatchIn(password)
        val isSymbolPresent = symbolPattern.containsMatchIn(password)
        val isLengthValid = password.length >= 8

        return isUppercasePresent && isDigitPresent && isSymbolPresent && isLengthValid

    }


    private fun register(){
        val inputName = binding.tieName.text.toString()
        val inputEmail = binding.tieEmail.text.toString()
        val phoneNumber = binding.tiePhoneNumber.text.toString()
        val password = binding.tiePassword.text.toString()
        val confirmPassword = binding.tieConfirmPassword.text.toString()

        val addUser = sharedRegis.edit()
        addUser.putString("name", inputName)

        val addEmail = sharedRegis.edit()
        addEmail.putString("email", inputEmail)

        val addPhoneNumber = sharedRegis.edit()
        addPhoneNumber.putString("phone_number", phoneNumber)

        if (inputName.isEmpty() || inputEmail.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()){
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
        } else{
            validatePassword(password)
            if (password!= confirmPassword){
                binding.tieConfirmPassword.error = "Confirm password tidak sama dengan password"
                binding.tieConfirmPassword.requestFocus()
            } else{
                userViewModel.callApiPostRegisterUser(inputEmail, password, inputName, phoneNumber)
                userViewModel.registerUser.observe(viewLifecycleOwner, Observer {
                    if (it!= null){
                        val bundle = Bundle()
                        bundle.putString("email", it.email)
                        Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registerFragment_to_sendOTPFragment, bundle)
                    } else{
                        Toast.makeText(requireContext(), "Register Failed", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

}