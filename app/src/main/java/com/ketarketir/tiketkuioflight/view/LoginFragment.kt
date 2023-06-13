package com.ketarketir.tiketkuioflight.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

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

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userManager = UserManager.getInstance(requireContext())

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
        val inputEmail = binding.tiEmailPhoneNumber.editText.toString()
        val inputPassword = binding.tiPassword.editText.toString()

        if (inputEmail.isEmpty() || inputPassword.isEmpty()){
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
        } else{
            userViewModel.callApiPostUserLogin(inputEmail, inputPassword)
            userViewModel.detailUser.observe(viewLifecycleOwner, Observer {
                if (inputEmail == it.email && inputPassword==it.password){
                    val userId = it.id
                    val email = it.email
                    userViewModel.token.observe(viewLifecycleOwner, Observer { token ->
                        val bearerToken = token.toString()
                        GlobalScope.async {
                            userManager.saveData(email, true, bearerToken, userId)
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