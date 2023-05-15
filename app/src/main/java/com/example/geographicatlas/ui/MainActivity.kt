package com.example.geographicatlas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy(LAZY_THREAD_SAFETY_MODE_NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val navController: NavController by lazy(LAZY_THREAD_SAFETY_MODE_NONE) {
        findNavController(R.id.nav_host)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
       private val LAZY_THREAD_SAFETY_MODE_NONE = LazyThreadSafetyMode.NONE
    }
}