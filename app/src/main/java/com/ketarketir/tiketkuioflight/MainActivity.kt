package com.ketarketir.tiketkuioflight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ketarketir.tiketkuioflight.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navContainer) as NavHostFragment
        navController = navHostFragment.findNavController()

        binding.btnBottomNavigation.setupWithNavController(navController)

    }
    fun setBottomNavigationVisibility(visibility: Int) {
        binding.btnBottomNavigation.visibility = visibility
    }
}