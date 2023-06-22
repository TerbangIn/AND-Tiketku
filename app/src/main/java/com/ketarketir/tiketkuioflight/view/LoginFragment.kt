package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.Settings.Global
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentLoginBinding
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel
//import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

//@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userManager: UserManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.tvRegisterHere.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment)
        }
        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_resetPasswordFragment)
        }
    }

    private fun login(){
        val inputEmail = binding.tieEmailNomorTelepon.text.toString()
        val inputPassword = binding.tiePassword.text.toString()

        if (inputEmail.isEmpty() || inputPassword.isEmpty()){
            Toast.makeText(requireActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
        } else{
            userViewModel.callApiPostUserLogin(inputEmail, inputPassword)
            userViewModel.token.observe(viewLifecycleOwner, Observer {token->
                if (token!=null){
                    val userToken = token
                    userViewModel.loginUsers.observe(viewLifecycleOwner, Observer {
                        val userId = it.id
                        val email = it.email
                        GlobalScope.async {
                            userManager.saveData(email, true, userToken, userId)
                        }
                    })

                    Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment2_to_homeFragment)
                } else{
                    Toast.makeText(requireContext(), "Login Failed, Incorrect Email/Password", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }


}