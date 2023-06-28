package com.ketarketir.tiketkuioflight.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ketarketir.tiketkuioflight.MainActivity
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.databinding.FragmentLoginBinding
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    private lateinit var userManager: UserManager
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        binding.tvLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userManager = UserManager.getInstance(requireContext())

        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.tvForgotPassword.setOnClickListener {
            navigateToInputEmailFragment()
        }

        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.tvRegisterHere.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun login() {
        val inputEmail = binding.tieEmailNomorTelepon.text.toString()
        val inputPassword = binding.tiePassword.text.toString()

        if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
            Toast.makeText(requireActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
        } else {
            userViewModel.callApiPostUserLogin(inputEmail, inputPassword)
            userViewModel.token.observe(viewLifecycleOwner, { token ->
                if (token != null) {
                    val userToken = token
                    userViewModel.loginUsers.observe(viewLifecycleOwner, { user ->
                        val userId = user.id
                        val email = user.email
                        GlobalScope.async {
                            userManager.saveData(email, true, userToken, userId)
                            saveUserEmail(email)
                        }
                    })

                    Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    Toast.makeText(requireContext(), "Login Failed, Incorrect Email/Password", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun saveUserEmail(email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("userEmail", email)
        editor.apply()

        Log.d("LoginFragment", "Email saved: $email")
    }


    private fun navigateToInputEmailFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_inputEmailFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
