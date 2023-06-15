package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentRegisterBinding
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel

class RegisterFragment : Fragment() {

    private lateinit var binding:FragmentRegisterBinding
    private lateinit var userViewModel: UserViewModel


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

        binding.btnRegister.setOnClickListener {
            register()
        }
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment22)
        }
    }

    private fun register(){
        val inputName = binding.tieName.text.toString()
        val inputEmail = binding.tieEmail.text.toString()
        val phoneNumber = binding.tiePhoneNumber.text.toString()
        val password = binding.tiePassword.text.toString()
        val confirmPassword = binding.tieConfirmPassword.text.toString()

        if (inputName.isEmpty() || inputEmail.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()){
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
        } else{
            if (password!= confirmPassword){
                binding.tieConfirmPassword.error = "Confirm password tidak sama dengan password"
                binding.tieConfirmPassword.requestFocus()
            } else{
                userViewModel.callApiPostRegisterUser(inputName, inputEmail, phoneNumber, password)
                userViewModel.registerUser.observe(viewLifecycleOwner, Observer {
                    if (it!= null){
                        Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment22)
                    } else{

                    }
                })
            }
        }
    }

}